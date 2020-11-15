package logger.rest;

import com.fasterxml.jackson.databind.Module;
import logger.json.VisitLogModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VisitLogApplication {

  /**
   * Starts the application
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    SpringApplication.run(VisitLogApplication.class, args);
  }

  /**
   * Assign our jackson module to springboot
   *
   * @return instane of VisitLogModule
   */
  @Bean
  protected Module objectMapperModule() {
    return new VisitLogModule();
  }
}
