package beans;

import java.util.Date;

public class Record implements Comparable<Record> {
    private Date date;
    private int guardId;
    private String action;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGuardId() {
        return guardId;
    }

    public void setGuardId(int guardId) {
        this.guardId = guardId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public int compareTo(Record o) {
        return getDate().compareTo(o.date) ;
    }
}
