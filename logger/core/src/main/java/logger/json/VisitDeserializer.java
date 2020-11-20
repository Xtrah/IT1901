package logger.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.LocalDateTime;
import logger.core.Visit;

public class VisitDeserializer extends JsonDeserializer<Visit> {

  /**
   * This method accepts json and returns a visit.
   *
   * @param jp   is a json-tree
   * @param ctxt is the deserialization context
   * @return a visit
   * @throws IOException if reading value goes wrong.
   */
  @Override
  public Visit deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return deserialize(jp.getCodec().readTree(jp));
  }

  /**
   * Deserializes a JsonNode into a Visit.
   *
   * @param node JsonNode node to be deserialized
   * @return Visit
   */
  public Visit deserialize(JsonNode node) {
    String id = node.get("id").asText();
    String name = node.get("name").asText();
    String phone = node.get("phone").asText();
    String building = node.get("building").asText();
    String room = node.get("room").asText();
    LocalDateTime from = LocalDateTime.parse(node.get("from").asText());
    LocalDateTime to = LocalDateTime.parse(node.get("to").asText());
    return new Visit(id, name, phone, building, room, from, to);
  }
}
