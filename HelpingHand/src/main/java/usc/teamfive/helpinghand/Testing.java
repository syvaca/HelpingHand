package usc.teamfive.helpinghand;

import usc.teamfive.helpinghand.datastructures.DatabaseLogin;
import usc.teamfive.helpinghand.datastructures.Job;
import usc.teamfive.helpinghand.drivers.JobDriver;

public class Testing {
	
    public static void main(String[] args) {
    	
        // make a database login and connect
    	String databaseUrl = "jdbc:mysql://localhost/";
    	DatabaseLogin login = new DatabaseLogin(databaseUrl, "Project", "root", "mySQLPassword");
    	JobDriver driver = new JobDriver(login);
    	
		// quickly populate the database with a few examples
		Job[] testJobs = new Job[10];
		for (int i = 0; i < testJobs.length; i++) {
			testJobs[i] = new Job(i + 10, "This is a job, adding from an array at position " + i + ".", 1, "3857381929", Math.random() < 0.5, Math.random() < 0.5, Math.random() < 0.5, Math.random() < 0.5, Math.random() < 0.5, Math.random() < 0.5, Math.random() < 0.5, Math.random() < 0.5, Math.random() < 0.5, Math.random() < 0.5, Math.random() < 0.5);
			driver.addJob(testJobs[i]);
		}
    	
    }

}
