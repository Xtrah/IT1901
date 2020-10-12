package logger.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VisitLog implements Iterable<Visit> {

    private static final String DEFAULT_FILEPATH = "logger/log.json";

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

    @Override
    public Iterator<Visit> iterator() {
        return log.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof VisitLog)) return false;

        VisitLog other = (VisitLog) obj;
        if (other.getLog() == null) return false;

        if (this.getLog().size() != other.getLog().size()) return false;

        Iterator<Visit> it = this.iterator();
        Iterator<Visit> oit = other.iterator();
        while (it.hasNext()) {
            Visit v1 = it.next();
            Visit v2 = oit.next();
            if (! v1.equals(v2)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
