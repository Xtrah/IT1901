package logger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import logger.core.Visit;

public class VisitSerializer extends JsonSerializer<Visit> {

  @Override
  public void serialize(Visit visit, JsonGenerator jGen, SerializerProvider serializerProvider)
      throws IOException {
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
