package logger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import logger.core.Visit;

public class VisitSerializer extends JsonSerializer<Visit> {

  @Override
  public void serialize(Visit visit, JsonGenerator jgen, SerializerProvider serializerProvider)
      throws IOException {
    jgen.writeStartObject();
    jgen.writeStringField("id", visit.getId());
    jgen.writeStringField("name", visit.getName());
    jgen.writeStringField("phone", visit.getPhone());
    jgen.writeStringField("building", visit.getBuilding());
    jgen.writeStringField("room", visit.getRoom());
    jgen.writeStringField("from", visit.getFrom().toString());
    jgen.writeStringField("to", visit.getTo().toString());
    jgen.writeEndObject();
  }
}
