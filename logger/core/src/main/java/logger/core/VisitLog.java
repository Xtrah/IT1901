package logger.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VisitLog implements Iterable<Visit> {

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
