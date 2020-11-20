package logger.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilterTest {

  private final List<Visit> allVisits = new ArrayList<>();
  private final Visit v1 = new Visit(
      "Ola Normann",
      "12345678",
      "Realfagbygget",
      "A4-100",
      LocalDateTime.of(2020, 10, 1, 14, 15),
      LocalDateTime.of(2020, 10, 1, 16, 15));
  private final Visit v2 = new Visit(
      "Kari Hansen",
      "87654321",
      "Stripa",
      "S4",
      LocalDateTime.of(2020, 10, 2, 17, 15),
      LocalDateTime.of(2020, 10, 2, 18, 15));

  @BeforeEach
  void setUp() {
    allVisits.add(v1);
    allVisits.add(v2);
  }

  @Test
  void filterByName() {
    List<Visit> filteredList = Filter.filterByName("Kari", allVisits);
    assertEquals(1, filteredList.size());
    assertEquals("Kari Hansen", filteredList.get(0).getName());

  }

  @Test
  void filterByPhone() {
    List<Visit> filteredList = Filter.filterByPhone("12345678", allVisits);
    assertEquals(1, filteredList.size());
    assertEquals("12345678", filteredList.get(0).getPhone());
  }

  @Test
  void filterByBuilding() {
    List<Visit> filteredList = Filter.filterByBuilding("Stripa", allVisits);
    assertEquals(1, filteredList.size());
    assertEquals("Stripa", filteredList.get(0).getBuilding());
  }

  @Test
  void filterByRoom() {
    List<Visit> filteredList = Filter.filterByRoom("A4-100", allVisits);
    assertEquals(1, filteredList.size());
    assertEquals("A4-100", filteredList.get(0).getRoom());
  }

  @Test
  void filterByDate() {
    List<Visit> filteredList = Filter.filterByDate(
        allVisits,
        LocalDate.of(2020, 10, 1),
        LocalDate.of(2020, 10, 1)
    );
    assertEquals(1, filteredList.size());
    assertEquals(v1, filteredList.get(0));
  }

  @AfterEach
  void cleanUp() {
    allVisits.clear();
  }
}