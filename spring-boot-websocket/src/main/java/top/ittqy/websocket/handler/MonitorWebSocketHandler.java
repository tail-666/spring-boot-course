package top.ittqy.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.platform.windows.WindowsHardwareAbstractionLayer;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: 27258
 * @Date: 2025/10/10
 */
@Component
@Slf4j
public class MonitorWebSocketHandler implements WebSocketHandler {
    private final HardwareAbstractionLayer hardware;
    // 格式化保留2位小数
    private final DecimalFormat df = new DecimalFormat("#.00");

    @Value("${server.monitor.cpu-threshold}")
    private double cpuThreshold;

    @Value("${server.monitor.memory-threshold}")
    private double memoryThreshold;

    /**
     * 初始化服务器硬件信息
     */
    public MonitorWebSocketHandler() {
        // Oshi：初始化硬件抽象层（获取CPU/内存信息）
        // Windows 用：WindowsHardwareAbstractionLayer，Linux用：LinuxHardwareAbstractionLayer
        this.hardware = new WindowsHardwareAbstractionLayer();
    }

    private static final Map<String, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SESSIONS.put(session.getId(), session);
        log.info("新的监控连接建立，会话ID: {}, 当前连接数: {}", session.getId(), SESSIONS.size());

        // 欢迎消息
        String welcome = """
            🎉 欢迎连接设备监控服务！
            🔔 将定时推送：
              - 设备状态（CPU/内存/磁盘）
            """;
        sendMsg(session, welcome);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString().trim();
        log.info("客户端消息: {}, 会话ID: {}", payload, session.getId());

        if ("ping".equalsIgnoreCase(payload)) {
            sendMsg(session, "pong");
        } else if ("status".equalsIgnoreCase(payload)) {
            // 发送status。开始获取设备信息
            String status = getDeviceStatus();
            sendMsg(session, status);
        } else {
            sendMsg(session, "收到: " + payload + "\n可发送 'ping' 或 'status' 测试");
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("传输错误，会话ID: {}", session.getId(), exception);
        SESSIONS.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        SESSIONS.remove(session.getId());
        log.info("连接关闭，会话ID: {}, 状态: {}, 剩余连接数: {}", session.getId(), closeStatus, SESSIONS.size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 定时任务：每5秒推送一次（设备状态）
     */
    @Scheduled(fixedRate = 5000)
    public void sendPeriodicUpdate() {
        if (SESSIONS.isEmpty()) {
            log.debug("无活跃连接，跳过推送");
            return;
        }
        log.info("推送监控数据，连接数: {}", SESSIONS.size());
        String fullMessage = getDeviceStatus();
        // 遍历并清理失效会话
        SESSIONS.values().removeIf(session -> {
            try {
                if (session.isOpen()) {
                    sendMsg(session, fullMessage);
                    return false;
                } else {
                    log.warn("移除已关闭会话: {}", session.getId());
                    return true;
                }
            } catch (Exception e) {
                log.error("推送失败，移除会话: {}", session.getId(), e);
                return true;
            }
        });
    }
    /**
     * 生成设备状态信息
     *
     * @return 设备状态信息
     */
    private String getDeviceStatus() {
        // 1. 获取CPU使用率（%）
        double cpuUsage = getCpuUsage();
        // 2. 获取内存使用率（%）
        double memoryUsage = getMemoryUsage();

        return String.format("【服务器监控】时间：%s，CPU使用率：%s%%，内存使用率：%s%%", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), df.format(cpuUsage), df.format(memoryUsage));
    }
    /**
     * 获取CPU使用率（Oshi工具）
     *
     * @return CPU使用率（%）
     */
    private double getCpuUsage() {
        // 获取CPU信息
        CentralProcessor processor = hardware.getProcessor();
        // 获取CPU使用率，delay的作用是获取CPU使用率时，CPU空闲时间间隔
        double systemCpuLoad = processor.getSystemCpuLoad(1000);
        // 返回CPU使用率（%）
        return systemCpuLoad * 100;
    }

    /**
     * 获取内存使用率（Oshi工具）
     *
     * @return 内存使用率（%）
     */
    private double getMemoryUsage() {
        // 获取内存信息
        GlobalMemory memory = hardware.getMemory();
        // 总内存（字节）
        long totalMemory = memory.getTotal();
        // 已使用内存（字节）
        long usedMemory = totalMemory - memory.getAvailable();
        // 计算使用率（%）
        return (double) usedMemory / totalMemory * 100;
    }
    /**
     * 发送消息封装
     */
    private void sendMsg(WebSocketSession session, String message) {
        try {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        } catch (Exception e) {
            log.error("发送消息失败: {}", session.getId(), e);
        }
    }
}