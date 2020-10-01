package logger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import logger.core.Visit;
import logger.core.VisitLog;

import java.io.IOException;
import java.time.LocalDateTime;

public class VisitLogSerializer extends JsonSerializer<VisitLog> {

    @Override
    public void serialize(VisitLog log, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeArrayFieldStart("log");
        for (Visit v : log) {
            jGen.writeObject(v);
        }
        jGen.writeEndArray();
        jGen.writeEndObject();
    }

}
