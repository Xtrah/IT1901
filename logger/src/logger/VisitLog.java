package logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisitLog {

    private static final String DEFAULT_FILEPATH = "logger/src/logger/log.json";

    private List<Visit> log;

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
        writeToFile();
    }

    public void writeToFile() {
        writeToFile(DEFAULT_FILEPATH);
    }

    public void writeToFile(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filepath);
        try {
            if (file.createNewFile()) {
                System.out.printf("Created new file %s", file.getName());
            }
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, log);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong while writing to file");
        }
    }

    public void readFromFile() {
        readFromFile(DEFAULT_FILEPATH);
    }

    public void readFromFile(String filepath) {
        try {
            File file = new File(filepath);
            if (file.exists()) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                log = new ArrayList<>(List.of(mapper.readValue(file, Visit[].class)));
            } else {
                log = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "VisitLog{" +
                "log=" + log +
                '}';
    }
}
