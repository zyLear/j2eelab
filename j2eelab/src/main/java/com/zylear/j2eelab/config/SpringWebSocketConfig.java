package com.zylear.j2eelab.config;

import com.zylear.j2eelab.websocket.WebSocketHandler;
import com.zylear.j2eelab.websocket.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by xiezongyu on 2018/7/5.
 */
@Configuration
@EnableWebSocket
public class SpringWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {


    @Bean
    public WebSocketHandler webSocketHandler() {
        return new WebSocketHandler();
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/websocket_url").
                addInterceptors(new WebSocketHandshakeInterceptor());
    }

}
