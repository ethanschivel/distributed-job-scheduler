
public class JobTest {

    public static void main(String[] args) {

        Job job = new Job("Send Email");

        // Test initial values
        check(job.getName().equals("Send Email"), "Job name");
        check(job.getStatus().equals("PENDING"), "Initial status");
        check(job.getAttempts() == 0, "Initial attempts");

        // Test status updates
        job.setStatus("RUNNING");
        check(job.getStatus().equals("RUNNING"), "Status update");

        // Test attempt counter
        job.incrementAttempts();
        job.incrementAttempts();
        check(job.getAttempts() == 2, "Attempt counter");

        System.out.println("All Job tests passed!");
    }

    public static void check(boolean condition, String testName) {
        if (!condition) {
            throw new AssertionError("FAILED: " + testName);
        }

        System.out.println("PASSED: " + testName);
    }
}
