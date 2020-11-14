package logger.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import logger.core.VisitLog;
import logger.json.VisitLogModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VisitLogController.class)
class VisitLogControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  void getVisitLog() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.get("");
    MvcResult result = mvc.perform(request)
        .andExpect(status().isOk())
        .andReturn();
    VisitLog logRes = new ObjectMapper().registerModule(new VisitLogModule())
        .readValue(result.getResponse().getContentAsString(), VisitLog.class);
    assertEquals(3, logRes.getLog().size());
  }
}