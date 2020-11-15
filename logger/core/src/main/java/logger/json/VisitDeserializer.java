package logger.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import logger.core.Visit;

import java.io.IOException;
import java.time.LocalDateTime;

public class VisitDeserializer extends JsonDeserializer<Visit> {

    @Override
    public Visit deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return deserialize(jp.getCodec().readTree(jp));
    }

    public Visit deserialize(JsonNode node) {
        String id = node.get("id").asText();
        String name = node.get("name").asText();
        String phone = node.get("phone").asText();
        String building = node.get("building").asText();
        String room = node.get("room").asText();
        LocalDateTime from = LocalDateTime.parse(node.get("from").asText());
        LocalDateTime to = LocalDateTime.parse(node.get("to").asText());
        return new Visit(id, name, phone, building, room, from, to);
    }
}
