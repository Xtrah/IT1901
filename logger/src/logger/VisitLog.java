package logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class VisitLog {

    private final Collection<Visit> log;

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
            mapper.writeValue(file, log);
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
