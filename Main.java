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

                System.out.println("Starting: " + jobs[i]);
            }

            if (statuses[i].equals("RUNNING")) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                statuses[i] = "COMPLETED";
            }

            System.out.println("Job " + (i + 1) + ": " + jobs[i] + " - " + statuses[i]);
        }
    }

    // Our new method
    public static void startJob(String jobName) {
        System.out.println("Starting: " + jobName);
    }

}