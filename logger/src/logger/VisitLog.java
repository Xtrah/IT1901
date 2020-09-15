package logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisitLog {

    private final List<Visit> log;

    public VisitLog() {
        this.log = new ArrayList<>();
    }

    public VisitLog(List<Visit> log) {
        this.log = log;
    }

    public List<Visit> getLog() {
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

    public static List<Visit> readFromFile(String filepath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return new ArrayList<>(List.of(mapper.readValue(new File(filepath), Visit[].class)));
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
