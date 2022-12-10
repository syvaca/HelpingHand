package usc.teamfive.helpinghand;

import java.text.SimpleDateFormat;
import java.util.Date;
import usc.teamfive.helpinghand.datastructures.Job;

public class Utilities {

    public static void log(String message) {
        // log a message to the console
        System.out.println("[" + timestamp() + "] " + message);
    }

    public static String timestamp() {
        // pretty print a time stamp
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss.SSS");
        return sdf.format(date);
    }

    public static Job[] addJob(Job[] jobs, Job job) {
        // add a job to a fixed array size
        Job[] newJobs = new Job[jobs.length + 1];
        for (int i = 0; i < jobs.length; i++) newJobs[i] = jobs[i];
        newJobs[jobs.length] = job;
        return newJobs;
    }

    public static Job[] addJobs(Job[] jobs, Job[] newJobs) {
        // add a job to a fixed array size
        Job[] newJobs2 = new Job[jobs.length + newJobs.length];
        for (int i = 0; i < jobs.length; i++) newJobs2[i] = jobs[i];
        for (int i = 0; i < newJobs.length; i++) newJobs2[jobs.length + i] = newJobs[i];
        return newJobs2;
    }

    public static int sqlBoolean(boolean bool) {
        // convert a boolean to an int for sql
        if (bool) return 1;
        else return 0;
    }

}
