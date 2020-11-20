package logger.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

    List<Visit> allVisits = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Visit v1 =
                new Visit(
                        "Ola Normann",
                        "12345678",
                        "Realfagbygget",
                        "A4-100",
                        LocalDateTime.of(2020, 10, 1, 14, 15),
                        LocalDateTime.of(2020, 10, 1, 16, 15));
        Visit v2 =
                new Visit(
                        "Kari Hansen",
                        "87654321",
                        "Stripa",
                        "S4",
                        LocalDateTime.of(2020, 10, 1, 17, 15),
                        LocalDateTime.of(2020, 10, 1, 18, 15));
        allVisits.add(v1);
        allVisits.add(v2);


    }

    @Test
    void filterByName() {
        System.out.println(allVisits);
        List<Visit> filteredList = Filter.filterByName("Ola", allVisits);
        System.out.print(filteredList);
        assertEquals(1, filteredList.size());
        //assertEquals("Kari Hansen", filteredList.get(0).getName());

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
                LocalDate.parse("2020, 10, 1, 17, 15"),
                LocalDate.parse("2020, 10, 1, 18, 15")
                );
        assertEquals(1, filteredList.size());
        assertEquals("Kari Hansen", filteredList.get(0).getName());
    }
}