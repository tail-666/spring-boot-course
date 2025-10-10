package top.ittqy.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import top.ittqy.websocket.handler.MonitorWebSocketHandler;
import top.ittqy.websocket.handler.SimpleTimeWebSocketHandler;
import top.ittqy.websocket.handler.TestWebSocketHandler;
import top.ittqy.websocket.handler.WordsWebSocketHandler;

/**
 * @Author: 27258
 * @Date: 2025/10/10
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 测试的 WebSocket 端点
        registry.addHandler(new TestWebSocketHandler(), "/ws/test").setAllowedOrigins("*");
        // 时间推送 WebSocket 端点
        registry.addHandler(new SimpleTimeWebSocketHandler(), "/ws/time").setAllowedOrigins("*");
        // 每日一言推送 WebSocket 端点
        registry.addHandler(new WordsWebSocketHandler(), "/ws/words").setAllowedOrigins("*");
        // 推送设备状态 WebSocket 端点
        registry.addHandler(new MonitorWebSocketHandler(), "/ws/monitor").setAllowedOrigins("*");
    }
}