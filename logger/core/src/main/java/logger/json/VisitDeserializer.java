package logger.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import logger.core.Visit;

import java.io.IOException;

public class VisitDeserializer extends JsonDeserializer<Visit> {

    @Override
    public Visit deserialize(JsonParser jsonParser,
                             DeserializationContext deserializationContext
    ) throws IOException, JsonProcessingException {
        return null;
    }
}
