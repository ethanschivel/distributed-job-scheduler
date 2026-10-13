
public class Job {

    private final String name;
    private String status;
    private int attempts;

    public Job(String name) {
        this.name = name;
        this.status = "PENDING";
        this.attempts = 0;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAttempts() {
        return attempts;
    }

    public void incrementAttempts() {
        attempts++;
    }
}
