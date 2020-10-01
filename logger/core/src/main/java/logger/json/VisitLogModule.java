package logger.json;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import logger.core.Visit;
import logger.core.VisitLog;

public class VisitLogModule extends SimpleModule {

    private static final String NAME = "VisitLogModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil() {};


    public VisitLogModule() {
        super(NAME, VERSION_UTIL.version());
        addSerializer(Visit.class, new VisitSerializer());
        addDeserializer(Visit.class, new VisitDeserializer());

        addSerializer(VisitLog.class, new VisitLogSerializer());
        addDeserializer(VisitLog.class, new VisitLogDeserializer());
    }
}
