package logger.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import logger.core.Visit;
import logger.core.VisitLog;
import logger.json.VisitLogModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VisitLogController.class)
class VisitLogControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private VisitLogService service;

  @Autowired
  private ObjectMapper mapper;

  /**
   * Dispatches a GET request to get a VisitLog and tests if the request was successful.
   *
   * @throws Exception if something goes wrong with the MvcReqeusts.
   */
  @Test
  void getVisitLog() throws Exception {
    given(service.getVisitLog()).willReturn(VisitLogService.sampleVisitLog());
    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/logger"))
        .andExpect(status().isOk())
        .andReturn();
    VisitLog logRes = new ObjectMapper().registerModule(new VisitLogModule())
        .readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), VisitLog.class);
    assertEquals(3, logRes.getLog().size());
  }

  /**
   * Dispatches a POST request to add a Visit and tests if the request was successful.
   *
   * @throws Exception if something goes wrong with the MvcRequest.
   */
  @Test
  void addVisit() throws Exception {
    Visit v1 =
        new Visit(
            "Ola Normann",
            "12345678",
            "Realfagbygget",
            "A4-100",
            LocalDateTime.of(2020, 10, 1, 14, 15),
            LocalDateTime.of(2020, 10, 1, 16, 15));
    String json = mapper.writeValueAsString(v1);
    MvcResult result = mvc.perform(MockMvcRequestBuilders
        .post("/logger")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andReturn();
    assertTrue(Boolean.parseBoolean(result.getResponse().getContentAsString()));
  }

  /**
   * Dispatches a DELETE request to delete a Visit
   * with a given ID and tests if the request was successful.
   *
   * @throws Exception if something goes wrong with the MvcRequest
   */
  @Test
  void removeVisit() throws Exception {
    String id = "a81a901e-be9c-4213-a900-4bca27d688a9";
    MvcResult result = mvc.perform(MockMvcRequestBuilders
        .delete("/logger/" + id))
        .andExpect(status().isOk())
        .andReturn();
    assertTrue(Boolean.parseBoolean(result.getResponse().getContentAsString()));
  }
}