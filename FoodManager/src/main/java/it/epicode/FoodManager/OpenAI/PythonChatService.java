package it.epicode.FoodManager.OpenAI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PythonChatService {

    @Value("${python.service.url}")
    private String pythonServiceUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(PythonChatService.class);

    public PythonChatService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public String getResponse(String userMessage) {
        try {
            logger.info("Sending message to Python service: {}", userMessage);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            String body = objectMapper.createObjectNode()
                    .put("message", userMessage)
                    .toString();

            logger.info("Request body: {}", body);

            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(pythonServiceUrl + "/chat", HttpMethod.POST, requestEntity, String.class);

            logger.info("Response status: {}", responseEntity.getStatusCode());
            logger.info("Response body: {}", responseEntity.getBody());

            JsonNode responseJson = objectMapper.readTree(responseEntity.getBody());
            return responseJson.get("message").asText();
        } catch (Exception e) {
            logger.error("Error communicating with Python service", e);
            return "Errore nella comunicazione con il servizio Python";
        }
    }
}