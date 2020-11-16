package logger.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static logger.core.Validation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class ValidationTest {

  @Test
  void testRegexValidation() {
    if (isTimeString("34", "69")) {
      fail();
    }
  }

  @Test
  void testValidTimeAndDate() {
    if ((isValidTime(null, null)) ||
        isValidDate(null) ||
        isValidDate(LocalDate.of(2030, 12, 31)))
    {
      fail();
    } else {
      assertFalse(isValidTime(LocalTime.of(19, 29), LocalTime.of(19, 28)));
      assertTrue(isValidDate(LocalDate.now()));
    }
  }

  @Test
  void testFormatToLocalTime(){
    assertNull(formatToLocalTime("34", "69"));
    assertEquals(formatToLocalTime("23", "59"), LocalTime.of(23, 59));
  }
}
