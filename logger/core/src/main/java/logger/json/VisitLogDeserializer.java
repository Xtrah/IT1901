package logger.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import logger.core.VisitLog;

public class VisitLogDeserializer extends JsonDeserializer<VisitLog> {

  private final VisitDeserializer visitDeserializer = new VisitDeserializer();

  /**
   * This method accepts json and returns a VisitLog.
   *
   * @param jp is a json-tree
   * @param ctxt is the deserialization context
   * @return a visit log.
   * @throws IOException if reading goes wrong
   */
  @Override
  public VisitLog deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    VisitLog log = new VisitLog();
    node.get("log").forEach(n -> log.addVisit(visitDeserializer.deserialize(n)));
    return log;
  }
}
