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

  private final File file;
  private final ObjectMapper mapper;

  /**
   * Connects objectMapper, file and VisitLogModule.
   *
   * @param file File file to persist
   */
  public VisitLogPersistence(File file) {
    mapper = new ObjectMapper();
    mapper.registerModule(new VisitLogModule());
    this.file = file;
  }

  /**
   * Reads and deserializes a Json VisitLog file.
   *
   * @return newLog VisitLog newLog
   */
  public VisitLog readVisitLog() {
    try (Reader reader = new FileReader(file, StandardCharsets.UTF_8)) {
      return mapper.readValue(reader, VisitLog.class);
    } catch (IOException e) {
      System.out.printf("Couldn't find %s, creating new VisitLog...%n", file.getName());
      VisitLog newLog = new VisitLog();
      writeVisitLog(newLog);
      return newLog;
    }
  }

  /**
   * Writes visitLog to file.
   *
   * @param visitLog VisitLog visitLog to be written to file
   */
  public void writeVisitLog(VisitLog visitLog) {
    try (Writer writer = new FileWriter(file, StandardCharsets.UTF_8)) {
      mapper.writerWithDefaultPrettyPrinter().writeValue(writer, visitLog);
    } catch (IOException e) {
      System.err.println("Something went wrong when writing to file.");
      e.printStackTrace();
    }
  }
}
