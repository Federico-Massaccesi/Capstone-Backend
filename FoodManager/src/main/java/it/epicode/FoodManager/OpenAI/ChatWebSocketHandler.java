package it.epicode.FoodManager.OpenAI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final PythonChatService pythonChatService;
    private final ObjectMapper objectMapper;

    public ChatWebSocketHandler(PythonChatService pythonChatService) {
        this.pythonChatService = pythonChatService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        JsonNode jsonNode = objectMapper.readTree(payload);
        String userMessage = jsonNode.get("message").asText();

        String response = pythonChatService.getResponse(userMessage);

        JsonNode responseJson = objectMapper.createObjectNode().put("message", response);
        session.sendMessage(new TextMessage(responseJson.toString()));
    }
}
