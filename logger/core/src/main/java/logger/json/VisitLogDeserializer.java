package logger.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import logger.core.VisitLog;

import java.io.IOException;

public class VisitLogDeserializer extends JsonDeserializer<VisitLog> {
    @Override
    public VisitLog deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return null;
    }
}
