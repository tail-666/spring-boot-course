package top.ittqy.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import top.ittqy.websocket.service.WordsService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: 27258
 * @Date: 2025/10/10
 */
@Component
@Slf4j
public class WordsWebSocketHandler  implements WebSocketHandler {
    private static final Map<String, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();

    @Autowired
    private WordsService wordsService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SESSIONS.put(session.getId(), session);
        log.info("新的监控连接建立，会话ID: {}, 当前连接数: {}", session.getId(), SESSIONS.size());

        // 欢迎消息
        String welcome = """
            🎉 欢迎连接每日一言服务！
            🔔 将定时推送：每日一言
            """;
        sendMsg(session, welcome);
    }
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString().trim();
        log.info("客户端消息: {}, 会话ID: {}", payload, session.getId());

        if ("ping".equalsIgnoreCase(payload)) {
            sendMsg(session, "pong");
        } else if ("words".equalsIgnoreCase(payload)) {
            // 发送words。开启获取一言
            sendMsg(session, wordsService.getRandomHitokoto());
        }else {
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
     * 定时任务：每5秒推送一次（一言 + 设备状态）
     */
    @Scheduled(fixedRate = 5000)
    public void sendPeriodicUpdate() {
        if (SESSIONS.isEmpty()) {
            log.debug("无活跃连接，跳过推送");
            return;
        }

        String hitokoto = "💬 " + wordsService.getRandomHitokoto();

        log.info("推送监控数据，连接数: {}", SESSIONS.size());

        // 遍历并清理失效会话
        SESSIONS.values().removeIf(session -> {
            try {
                if (session.isOpen()) {
                    sendMsg(session, hitokoto);
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
