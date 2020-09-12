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
    }

    public void writeToFile(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filepath);
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, log);
        } catch (IOException e) {
            System.out.println("Something went wrong while writing to file");
        }
    }

    public static List<Visit> readFromFile(String filepath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return List.of(mapper.readValue(new File(filepath), Visit[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "VisitLog{" +
                "log=" + log +
                '}';
    }
}
