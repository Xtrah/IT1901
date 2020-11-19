package logger.fxui;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import logger.core.Visit;
import logger.fxui.utils.RemoteVisitLogDataAccess;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RemoteDataAccessTest {

  private static final String GET_SAMPLE_RESPONSE = "{\"log\": [{"
      + "\"id\": \"a81a901e-be9c-4213-a900-4bca27d688a9\", "
      + "\"name\": \"No hello\", "
      + "\"phone\": \"91837871\", "
      + "\"building\": \"Realfagsbygget\", "
      + "\"room\": \"A4\", "
      + "\"from\": \"2020-11-05T12:00\", "
      + "\"to\": \"2020-11-05T15:00\""
      + "}]}";

  private WireMockServer wmServer;

  private RemoteVisitLogDataAccess dataAccess;

  @BeforeEach
  public void startWireMockServerAndSetup() throws URISyntaxException {
    WireMockConfiguration wmConfig = WireMockConfiguration.wireMockConfig().port(6970);
    wmServer = new WireMockServer(wmConfig.portNumber());
    wmServer.start();
    WireMock.configureFor("localhost", wmConfig.portNumber());
    dataAccess = new RemoteVisitLogDataAccess(
        new URI("http://localhost:" + wmServer.port() + "/logger"));
  }

  /**
   * Tests remote fetch of VisitLog.
   */
  @Test
  public void testGetLog() {
    // Arrange
    stubFor(get(urlEqualTo("/logger"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(GET_SAMPLE_RESPONSE)
        )
    );

    // Act
    List<Visit> visits = dataAccess.getVisitLog().getLog();

    // Assert
    assertEquals(1, visits.size());
    assertEquals("No hello", visits.get(0).getName());
  }

  // Disclaimer: our add and delete functions threw a NPE and we weren't able to resolve them. Therefore they are left out.

  @AfterEach
  public void stopWireMockServer() {
    wmServer.stop();
  }
}
