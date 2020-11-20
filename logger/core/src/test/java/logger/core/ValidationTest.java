package logger.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static logger.core.Validation.formatToLocalTime;
import static logger.core.Validation.fromIsBeforeTo;
import static logger.core.Validation.isEmptyString;
import static logger.core.Validation.isTimeString;
import static logger.core.Validation.isValidDate;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class ValidationTest {

  @Test
  void testIsEmptyString() {
    assertTrue(isEmptyString(null));
    assertTrue(isEmptyString(""));
    assertFalse(isEmptyString("Not empty"));
  }

  @Test
  void testRegexValidation() {
    if (isTimeString("34", "69")) {
      fail();
    }
  }

  @Test
  void testValidTimeAndDate() {
    if ((fromIsBeforeTo(null, null)) ||
        isValidDate(null) ||
        isValidDate(LocalDate.of(2030, 12, 31))) {
      fail();
    } else {
      assertFalse(fromIsBeforeTo(LocalTime.of(19, 29), LocalTime.of(19, 28)));
      assertTrue(isValidDate(LocalDate.now()));
    }
  }

  @Test
  void testFormatToLocalTime() {
    assertNull(formatToLocalTime("34", "69"));
    assertEquals(formatToLocalTime("23", "59"), LocalTime.of(23, 59));
  }
}
