package domain;

public class scenicProjects {

    private String scenicName;
    private boolean isOnService;

    public scenicProjects() {
    }

    public scenicProjects(String scenicName, boolean isOnService) {
        this.scenicName = scenicName;
        this.isOnService = isOnService;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public boolean isOnService() {
        return isOnService;
    }

    public void setOnService(boolean onService) {
        isOnService = onService;
    }

    @Override
    public String toString() {
        return "scenicProjects{" +
                "scenicName='" + scenicName + '\'' +
                ", isOnService=" + isOnService +
                '}';
    }
}
