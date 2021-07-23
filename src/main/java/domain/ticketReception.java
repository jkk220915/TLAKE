package domain;

import java.sql.Timestamp;
import java.util.Date;

public class ticketReception {

    private Date tineData;
    private Timestamp timeClock;
    private int ticketID;
    private String scenicProjects;
    private String employees;
    private boolean remark;

    public ticketReception() {
    }

    public ticketReception(Date tineData, Timestamp timeClock, int ticketID, String scenicProjects, String employees, boolean remark) {
        this.tineData = tineData;
        this.timeClock = timeClock;
        this.ticketID = ticketID;
        this.scenicProjects = scenicProjects;
        this.employees = employees;
        this.remark = remark;
    }

    public Date getTineData() {
        return tineData;
    }

    public void setTineData(Date tineData) {
        this.tineData = tineData;
    }

    public Timestamp getTimeClock() {
        return timeClock;
    }

    public void setTimeClock(Timestamp timeClock) {
        this.timeClock = timeClock;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getScenicProjects() {
        return scenicProjects;
    }

    public void setScenicProjects(String scenicProjects) {
        this.scenicProjects = scenicProjects;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public boolean isRemark() {
        return remark;
    }

    public void setRemark(boolean remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ticketReception{" +
                "tineData=" + tineData +
                ", timeClock=" + timeClock +
                ", ticketID=" + ticketID +
                ", scenicProjects='" + scenicProjects + '\'' +
                ", employees='" + employees + '\'' +
                ", remark=" + remark +
                '}';
    }
}
