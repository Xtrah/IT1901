package main.java.logger.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisitLog {

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
        writeToFile("logger/src/logger/log.json");
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

    public void readFromFile(String filepath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            log = new ArrayList<>(List.of(mapper.readValue(new File(filepath), Visit[].class)));
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
