
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        System.out.println("Job Scheduler Started!");

        // Create our Job objects
        Job[] jobs = {
            new Job("Process Video"),
            new Job("Send Email"),
            new Job("Generate Report"),
            new Job("Backup Database")
        };

        // Create 3 worker threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit each job to the workers
        for (Job job : jobs) {

            executor.submit(() -> {

                job.setStatus("RUNNING");
                System.out.println("Starting: " + job.getName());

                boolean success = false;

                // Retry up to 3 times
                while (!success && job.getAttempts() < 3) {

                    job.incrementAttempts();

                    System.out.println(
                        "Attempt " + job.getAttempts()
                        + " for " + job.getName()
                    );

                    success = processJob(
                        job.getName(),
                        job.getAttempts()
                    );
                }

                if (success) {
                    job.setStatus("COMPLETED");
                } else {
                    job.setStatus("FAILED");
                }

                System.out.println(
                    "Job: " + job.getName()
                    + " - " + job.getStatus()
                );
            });
        }

        // Stop accepting new jobs
        executor.shutdown();

        // Wait for jobs to finish
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("Some jobs are still running.");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Job Scheduler Finished!");
    }

    public static boolean processJob(String jobName, int attempt) {

        System.out.println("Processing: " + jobName);

        // Simulate Send Email failing on its first attempt
        if (jobName.equals("Send Email") && attempt == 1) {
            return false;
        }

        try {
            Thread.sleep(2000);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
