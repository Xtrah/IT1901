package logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class VisitLog {

    private final Collection<Visit> log;

    public VisitLog() {
        this.log = new ArrayList<>();
    }

    public VisitLog(Collection<Visit> log) {
        this.log = log;
    }

    public Collection<Visit> getLog() {
        return log;
    }

    public void addVisit(Visit visit) {
        log.add(visit);
        this.writeToFile();
    }

    private void writeToFile() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("logger/src/logger/log.json");
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, log);
        } catch (IOException e) {
            System.out.println("Something went wrong while writing to file");
        }
    }

    public List<Visit> readFromFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return List.of(mapper.readValue(new File("logger/src/logger/log.json"), Visit[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Visit v1 = new Visit("J", "45", "B1", "R1",
                LocalDateTime.of(2020, 10, 1, 15, 15),
                LocalDateTime.of(2020, 10, 1, 16, 15));
        Visit v2 = new Visit("K", "46", "B1", "R1",
                LocalDateTime.of(2020, 10, 1, 17, 15),
                LocalDateTime.of(2020, 10, 1, 18, 15));
        VisitLog log = new VisitLog();
        log.addVisit(v1);
        log.addVisit(v2);
        log.writeToFile();
        VisitLog log2 = new VisitLog(log.readFromFile());
        System.out.println(log2.getLog());
    }

    @Override
    public String toString() {
        return "VisitLog{" +
                "log=" + log +
                '}';
    }
}
