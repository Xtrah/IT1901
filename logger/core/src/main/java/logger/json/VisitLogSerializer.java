package logger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import logger.core.Visit;
import logger.core.VisitLog;

public class VisitLogSerializer extends JsonSerializer<VisitLog> {

  @Override
  public void serialize(VisitLog log, JsonGenerator jgen, SerializerProvider serializerProvider)
      throws IOException {
    jgen.writeStartObject();
    jgen.writeArrayFieldStart("log");
    for (Visit v : log) {
      jgen.writeObject(v);
    }
    jgen.writeEndArray();
    jgen.writeEndObject();
  }
}
