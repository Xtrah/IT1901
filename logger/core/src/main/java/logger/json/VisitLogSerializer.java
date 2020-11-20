package logger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import logger.core.Visit;
import logger.core.VisitLog;

public class VisitLogSerializer extends JsonSerializer<VisitLog> {

  /**
   * This method accepts a visit log and writes json to file.
   *
   * @param log to be written to file
   * @param jgen generates json
   * @param serializerProvider provides serializing functionality
   * @throws IOException if writing file goes wrong
   */
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
