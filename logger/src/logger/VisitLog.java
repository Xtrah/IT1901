package logger;

import java.util.Collection;

public class VisitLog {

    private Collection<Visit> log;

    public VisitLog(Collection<Visit> log) {
        this.log = log;
    }
    public VisitLog(){

    }

    public Collection<Visit> getLog() {
        return log;
    }

    public void addVisit(Visit visit) {
        log.add(visit);
    }
}
