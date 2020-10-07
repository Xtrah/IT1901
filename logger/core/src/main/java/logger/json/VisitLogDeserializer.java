package logger.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import logger.core.VisitLog;

import java.io.IOException;

public class VisitLogDeserializer extends JsonDeserializer<VisitLog> {

    private final VisitDeserializer visitDeserializer = new VisitDeserializer();

    @Override
    public VisitLog deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        VisitLog log = new VisitLog();
        node.get("log").forEach(n -> log.addVisit(visitDeserializer.deserialize(n)));
        return log;
    }
}
