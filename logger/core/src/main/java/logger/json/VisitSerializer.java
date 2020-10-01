package logger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import logger.core.Visit;

import java.io.IOException;
import java.time.LocalDateTime;

public class VisitSerializer extends JsonSerializer<Visit> {

    @Override
    public void serialize(Visit visit,
                          JsonGenerator jGen,
                          SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("name", visit.getName());
        jGen.writeStringField("phone", visit.getPhone());
        jGen.writeStringField("building", visit.getBuilding());
        jGen.writeStringField("room", visit.getRoom());
        jGen.writeStringField("from", visit.getFrom().toString());
        jGen.writeStringField("to", visit.getTo().toString());
        jGen.writeEndObject();
    }

}
