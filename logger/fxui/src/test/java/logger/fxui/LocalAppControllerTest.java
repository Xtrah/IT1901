package logger.fxui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LocalAppControllerTest {

  LocalAppController controller = new LocalAppController();

  @Test
  void setUpStorage() {
    assertNull(controller.dataAccess);
    controller.setUpStorage();
    assertNotNull(controller.dataAccess);
  }
}