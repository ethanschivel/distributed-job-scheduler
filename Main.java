public class Main {

    public static void main(String[] args) {
        System.out.println("Job Scheduler Started!");

        String[] jobs = {
            "Process Video",
            "Send Email",
            "Generate Report",
            "Backup Database"
        };

        String[] statuses = {
            "PENDING",
            "PENDING",
            "PENDING",
            "PENDING"
        };

        for (int i = 0; i < jobs.length; i++) {

            if (statuses[i].equals("PENDING")) {
                statuses[i] = "RUNNING";

                startJob(jobs[i]);
            }

            if (statuses[i].equals("RUNNING")) {
                boolean success = false;
                int attempts = 0;

                while (!success && attempts < 3) {
                    attempts++;
                    System.out.println(" Attempt " + attempts + " for " + jobs[i]);
                    success = processJob(jobs[i], attempts);
                }

                if (success){
                    statuses[i] = "COMPLETED";
                }else{
                    statuses[i] = "FAILED" ;
                }
            }

            System.out.println("Job " + (i + 1) + ": " + jobs[i] + " - " + statuses[i]);
        }
    }

    // Method 1: Starts a job
    public static void startJob(String jobName) {
        System.out.println("Starting: " + jobName);
    }

    // Method 2: Simulates processing a job
    public static boolean processJob(String jobName , int attempt) {
        System.out.println("Processing: " + jobName);
        if (jobName.equals("Send Email") && attempt == 1 ) {
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