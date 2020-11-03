package logger.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import logger.core.VisitLog;

public class VisitLogPersistence {
  private static final File DEFAULT_FILE = new File(System.getProperty("user.dir") + "/log.json");

  private ObjectMapper mapper;

  public VisitLogPersistence() {
    mapper = new ObjectMapper();
    mapper.registerModule(new VisitLogModule());
  }

  public VisitLog readVisitLog() {
    try (Reader reader = new FileReader(DEFAULT_FILE, StandardCharsets.UTF_8)) {
      return mapper.readValue(reader, VisitLog.class);
    } catch (IOException e) {
      System.out.println("Couldn't read data from file, creating empty VisitLog...");
      return new VisitLog();
    }
  }

  public void writeVisitLog(VisitLog todoModel) {
    try (Writer writer = new FileWriter(DEFAULT_FILE, StandardCharsets.UTF_8)) {
      mapper.writerWithDefaultPrettyPrinter().writeValue(writer, todoModel);
    } catch (IOException e) {
      System.err.println("Something went wrong when writing to file.");
      e.printStackTrace();
    }
  }
}
