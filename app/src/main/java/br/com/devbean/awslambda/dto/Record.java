package br.com.devbean.awslambda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Record {

    @JsonProperty("body")
    private String body;

    @JsonProperty("messageId")
    private String messageId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    public NotificationDataDTO getNotificationRequestDTO() {
        try {
            return new ObjectMapper().readValue(body, NotificationDataDTO.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}