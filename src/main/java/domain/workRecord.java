package domain;

import java.util.Date;

public class workRecord {

    private int id;
    private Date timeDate;
    private String EmployeeId;
    private String auxiliaryIndex;
    private String name;
    private boolean punchIn;
    private boolean punchOff;
    private int receptionCount;
    private int fiveStarCount;
    private int fourStarCount;
    private int threeStarCount;
    private int twoStarCount;
    private int oneStarCount;
    private boolean isOnline;
    private int scenicID;

    public workRecord() {
    }

    public workRecord(int id, Date timeDate, String employeeId, String auxiliaryIndex, String name, boolean punchIn, boolean punchOff, int receptionCount, int fiveStarCount, int fourStarCount, int threeStarCount, int twoStarCount, int oneStarCount, boolean isOnline, int scenicID) {
        this.id = id;
        this.timeDate = timeDate;
        EmployeeId = employeeId;
        this.auxiliaryIndex = auxiliaryIndex;
        this.name = name;
        this.punchIn = punchIn;
        this.punchOff = punchOff;
        this.receptionCount = receptionCount;
        this.fiveStarCount = fiveStarCount;
        this.fourStarCount = fourStarCount;
        this.threeStarCount = threeStarCount;
        this.twoStarCount = twoStarCount;
        this.oneStarCount = oneStarCount;
        this.isOnline = isOnline;
        this.scenicID = scenicID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(Date timeDate) {
        this.timeDate = timeDate;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getAuxiliaryIndex() {
        return auxiliaryIndex;
    }

    public void setAuxiliaryIndex(String auxiliaryIndex) {
        this.auxiliaryIndex = auxiliaryIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPunchIn() {
        return punchIn;
    }

    public void setPunchIn(boolean punchIn) {
        this.punchIn = punchIn;
    }

    public boolean isPunchOff() {
        return punchOff;
    }

    public void setPunchOff(boolean punchOff) {
        this.punchOff = punchOff;
    }

    public int getReceptionCount() {
        return receptionCount;
    }

    public void setReceptionCount(int receptionCount) {
        this.receptionCount = receptionCount;
    }

    public int getFiveStarCount() {
        return fiveStarCount;
    }

    public void setFiveStarCount(int fiveStarCount) {
        this.fiveStarCount = fiveStarCount;
    }

    public int getFourStarCount() {
        return fourStarCount;
    }

    public void setFourStarCount(int fourStarCount) {
        this.fourStarCount = fourStarCount;
    }

    public int getThreeStarCount() {
        return threeStarCount;
    }

    public void setThreeStarCount(int threeStarCount) {
        this.threeStarCount = threeStarCount;
    }

    public int getTwoStarCount() {
        return twoStarCount;
    }

    public void setTwoStarCount(int twoStarCount) {
        this.twoStarCount = twoStarCount;
    }

    public int getOneStarCount() {
        return oneStarCount;
    }

    public void setOneStarCount(int oneStarCount) {
        this.oneStarCount = oneStarCount;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public int getScenicID() {
        return scenicID;
    }

    public void setScenicID(int scenicID) {
        this.scenicID = scenicID;
    }

    @Override
    public String toString() {
        return "workRecord{" +
                "id=" + id +
                ", timeDate=" + timeDate +
                ", EmployeeId='" + EmployeeId + '\'' +
                ", auxiliaryIndex='" + auxiliaryIndex + '\'' +
                ", name='" + name + '\'' +
                ", punchIn=" + punchIn +
                ", punchOff=" + punchOff +
                ", receptionCount=" + receptionCount +
                ", fiveStarCount=" + fiveStarCount +
                ", fourStarCount=" + fourStarCount +
                ", threeStarCount=" + threeStarCount +
                ", twoStarCount=" + twoStarCount +
                ", oneStarCount=" + oneStarCount +
                ", isOnline=" + isOnline +
                ", scenicID=" + scenicID +
                '}';
    }
}
