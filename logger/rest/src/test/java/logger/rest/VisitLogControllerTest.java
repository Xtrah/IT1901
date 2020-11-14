package logger.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import logger.core.VisitLog;
import logger.json.VisitLogModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

  @Test
  void getVisitLog() throws Exception {
    given(service.getVisitLog()).willReturn(new VisitLog());
    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/logger"))
        .andExpect(status().isOk())
        .andReturn();
    VisitLog logRes = new ObjectMapper().registerModule(new VisitLogModule())
        .readValue(result.getResponse().getContentAsString(), VisitLog.class);
    Assertions.assertNotNull(logRes);
  }
}