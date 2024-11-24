package kr.kro.runleaf.controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import kr.kro.runleaf.domain.ChatMessage;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage") // 클라이언트가 보낸 메시지를 처리
    @SendTo("/topic/public") // 구독 중인 클라이언트에게 메시지 전송
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage; // 메시지를 그대로 반환
    }
}