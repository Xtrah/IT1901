package logger.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import logger.core.Visit;
import logger.core.VisitLog;

public class VisitLogModule extends SimpleModule {

  private static final long serialVersionUID = 1L;
  private static final String NAME = "VisitLogModule";

  /**
   * Registers serializers and deserializers and connects them to the correct class.
   */
  public VisitLogModule() {
    super(NAME);
    addSerializer(Visit.class, new VisitSerializer());
    addDeserializer(Visit.class, new VisitDeserializer());

    addSerializer(VisitLog.class, new VisitLogSerializer());
    addDeserializer(VisitLog.class, new VisitLogDeserializer());
  }
}
