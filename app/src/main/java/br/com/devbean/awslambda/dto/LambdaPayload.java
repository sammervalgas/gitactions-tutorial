package br.com.devbean.awslambda.dto;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LambdaPayload {

    @JsonProperty("Records")
    private List<Record> records;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public static LambdaPayload of(InputStream inputStream, LambdaLogger logger) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(inputStream);
            logger.log("RECEIVED => Payload " + jsonNode.toString());
            return mapper.readValue(jsonNode.toString(), LambdaPayload.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}