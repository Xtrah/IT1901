package logger.fxui.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import logger.core.Visit;
import logger.core.VisitLog;
import logger.json.VisitLogModule;

public class RemoteVisitLogDataAccess implements VisitLogDataAccess {

  private final URI endpointUri;
  private ObjectMapper objectMapper;

  private VisitLog visitLog;

  public RemoteVisitLogDataAccess(URI endpointUri) {
    this.endpointUri = endpointUri;
    this.objectMapper = new ObjectMapper().registerModule(new VisitLogModule());
  }

  /**
   * Gets visitlog
   *
   * Send http get request to remote server
   *
   * @return the VisitLog
   */
  @Override
  public VisitLog getVisitLog() {
    if (visitLog == null) {
      try {
        final HttpRequest req = HttpRequest.newBuilder(endpointUri)
            .header("Accept", "application/json")
            .GET()
            .build();
        final HttpResponse<String> res =
            HttpClient
                .newBuilder()
                .build()
                .send(req, HttpResponse.BodyHandlers.ofString());
        this.visitLog = objectMapper.readValue(res.body(), VisitLog.class);
      } catch(IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return visitLog;
  }

  /**
   * Add visit to visitlog
   *
   * Send http post request to remote server
   *
   * @param visit
   */
  @Override
  public void addVisit(Visit visit) {
    try {
      String jsonVisit = objectMapper.writeValueAsString(visit);
      final HttpRequest req = HttpRequest
          .newBuilder(endpointUri)
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(jsonVisit))
          .build();
      final HttpResponse<String> res =
          HttpClient
              .newBuilder()
              .build()
              .send(req, HttpResponse.BodyHandlers.ofString());
      Boolean successfullyAdded = objectMapper.readValue(res.body(), Boolean.class);
      if (successfullyAdded != null) {
        visitLog.addVisit(visit);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Deletes visit based on given id
   *
   * Send http delete request to remote server
   *
   * @param id visit id
   */
  @Override
  public void deleteVisit(String id) {
    try {
      final HttpRequest req = HttpRequest
          .newBuilder(endpointUri)
          .header("Accept", "application/json")
          .DELETE()
          .build();
      final HttpResponse<String> res =
          HttpClient
              .newBuilder()
              .build().send(req, HttpResponse.BodyHandlers.ofString());
      Boolean successfullyRemoved = objectMapper.readValue(res.body(), Boolean.class);
      if (successfullyRemoved != null) {
        visitLog.removeVisit(id);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
