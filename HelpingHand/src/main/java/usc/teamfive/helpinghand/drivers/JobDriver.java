package usc.teamfive.helpinghand.drivers;

import java.sql.*;

import usc.teamfive.helpinghand.Utilities;
import usc.teamfive.helpinghand.datastructures.DatabaseLogin;
import usc.teamfive.helpinghand.datastructures.Job;

// object representing a driver for i/o to the database for jobs
// the goal is to simplify all reading and writing of jobs in the mysql database
public class JobDriver {

    // private variables
    private Connection conn;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    private DatabaseLogin login;

    // only editable in IDE in case database structure changes
    private final String JOB_TABLE_NAME = "job_postings";
    private final String JOB_VALUE_ID = "id";
    private final String JOB_VALUE_DESCRIPTION = "descript";
    private final String JOB_VALUE_COST = "cost";
    private final String JOB_VALUE_USER = "SID";
    private final String JOB_VALUE_TAG_ASSEMBLY = "assembly_tag";
    private final String JOB_VALUE_TAG_CLEANING = "cleaning_tag";
    private final String JOB_VALUE_TAG_STORAGE = "storage_tag";
    private final String JOB_VALUE_TAG_REPAIR = "repair_tag";
    private final String JOB_VALUE_TAG_PET_CARE = "petCare_Tag";
    private final String JOB_VALUE_TAG_CHILD_CARE = "childCare_Tag";
    private final String JOB_VALUE_TAG_MEAL_PREP = "mealPrep_Tag";
    private final String JOB_VALUE_TAG_IT_HELP = "ITHelp_Tag";
    private final String JOB_VALUE_TAG_BOOKS = "books_tag";
    private final String JOB_VALUE_TAG_SUBLETS = "sublets_tag";
    private final String JOB_VALUE_TAG_OTHER = "other_tag";

    // constructor
    public JobDriver(DatabaseLogin login) {
        // assign the variables
        this.login = login;
        // attempt to connect to the database
        try {
            conn = DriverManager.getConnection(this.login.getLogin());
            st = conn.createStatement();
        } catch (SQLException e) {
            conn = null;
            st = null;
            ps = null;
            rs = null;
            throw new RuntimeException(e);
        } finally {
            Utilities.log((conn == null) ? "Failed to connect to database.." : "Connected to database!");
        }
    }

    public JobDriver() {
        // just in case an empty constructor is called
        Utilities.log("JobDriver constructor called with no login info, ignoring call.");
        conn = null;
        st = null;
        ps = null;
        rs = null;
        login = null;
        throw new RuntimeException("JobDriver constructor called with no login info, ignoring call.");
    }

    // functions
    public Job makeJob(int id, String description, int cost, String user, boolean tagAssembly, boolean tagCleaning,
                       boolean tagStorage, boolean tagRepair, boolean tagPetCare, boolean tagChildCare,
                       boolean tagMealPrep, boolean tagITHelp, boolean tagBooks, boolean tagSublets,
                       boolean tagOther) {
        // HELPER function to make a Job object, since this will be needed a lot
        return new Job(id, description, cost, user, tagAssembly, tagCleaning, tagStorage, tagRepair, tagPetCare,
                tagChildCare, tagMealPrep, tagITHelp, tagBooks, tagSublets, tagOther);
    }

    public Job[] getJobsByUserID(String userId) {
        // READ jobs from the database by user id
        Job[] jobs = new Job[0];
        // read all of the jobs from the database, and store the ones that match the user id
        try {
            rs = st.executeQuery("SELECT * FROM " + JOB_TABLE_NAME);
            while (rs.next()) {
                String id = rs.getString(JOB_VALUE_USER);
                if (id.equals(userId)) {
                    // make a job object from the data
                    Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                            rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                            rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                            rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                            rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                            rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                            rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                    // add the job to the array
                    jobs = Utilities.addJob(jobs, job);
                }
            }
        } catch (SQLException e) {
            Utilities.log("Failed to read jobs from database (by user id).");
            throw new RuntimeException(e);
        }
        // returning the jobs
        return jobs;
    }

    public Job[] getJobsByTag(String tag) {
        // READ jobs from the database by a single tag
        Job[] jobs = new Job[0];
        // read all of the jobs from the database, and store the ones that match the tag
        try {
            rs = st.executeQuery("SELECT * FROM " + JOB_TABLE_NAME);
            while (rs.next()) {
                switch (tag) {
                    case "assembly_tag":
                    case "assembly":
                        if (rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY)) {
                            // make a job object from the data
                            Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                                    rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                                    rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                                    rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                                    rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                                    rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                                    rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                            // add the job to the array
                            jobs = Utilities.addJob(jobs, job);
                        }
                        break;
                    case "cleaning_tag":
                    case "cleaning":
                        if (rs.getBoolean(JOB_VALUE_TAG_CLEANING)) {
                            // make a job object from the data
                            Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                                    rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                                    rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                                    rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                                    rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                                    rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                                    rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                            // add the job to the array
                            jobs = Utilities.addJob(jobs, job);
                        }
                        break;
                    case "storage_tag":
                    case "storage":
                        if (rs.getBoolean(JOB_VALUE_TAG_STORAGE)) {
                            // make a job object from the data
                            Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                                    rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                                    rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                                    rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                                    rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                                    rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                                    rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                            // add the job to the array
                            jobs = Utilities.addJob(jobs, job);
                        }
                        break;
                    case "repair_tag":
                    case "repair":
                        if (rs.getBoolean(JOB_VALUE_TAG_REPAIR)) {
                            // make a job object from the data
                            Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                                    rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                                    rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                                    rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                                    rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                                    rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                                    rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                            // add the job to the array
                            jobs = Utilities.addJob(jobs, job);
                        }
                        break;
                    case "pet_care_tag":
                    case "pet_care":
                        if (rs.getBoolean(JOB_VALUE_TAG_PET_CARE)) {
                            // make a job object from the data
                            Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                                    rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                                    rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                                    rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                                    rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                                    rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                                    rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                            // add the job to the array
                            jobs = Utilities.addJob(jobs, job);
                        }
                        break;
                    case "child_care_tag":
                    case "child_care":
                        if (rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE)) {
                            // make a job object from the data
                            Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                                    rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                                    rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                                    rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                                    rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                                    rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                                    rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                            // add the job to the array
                            jobs = Utilities.addJob(jobs, job);
                        }
                        break;
                    case "meal_prep_tag":
                    case "meal_prep":
                        if (rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP)) {
                            // make a job object from the data
                            Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                                    rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                                    rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                                    rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                                    rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                                    rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                                    rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                            // add the job to the array
                            jobs = Utilities.addJob(jobs, job);
                        }
                        break;
                    case "it_help_tag":
                    case "it_help":
                        if (rs.getBoolean(JOB_VALUE_TAG_IT_HELP)) {
                            // make a job object from the data
                            Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                                    rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                                    rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                                    rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                                    rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                                    rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                                    rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                            // add the job to the array
                            jobs = Utilities.addJob(jobs, job);
                        }
                        break;
                    case "books_tag":
                    case "books":
                        if (rs.getBoolean(JOB_VALUE_TAG_BOOKS)) {
                            // make a job object from the data
                            Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                                    rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                                    rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                                    rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                                    rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                                    rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                                    rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                            // add the job to the array
                            jobs = Utilities.addJob(jobs, job);
                        }
                        break;
                    case "sublets_tag":
                    case "sublets":
                        if (rs.getBoolean(JOB_VALUE_TAG_SUBLETS)) {
                            // make a job object from the data
                            Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                                    rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                                    rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                                    rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                                    rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                                    rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                                    rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                            // add the job to the array
                            jobs = Utilities.addJob(jobs, job);
                        }
                        break;
                    case "other_tag":
                    case "other":
                        if (rs.getBoolean(JOB_VALUE_TAG_OTHER)) {
                            // make a job object from the data
                            Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                                    rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                                    rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                                    rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                                    rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                                    rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                                    rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                            // add the job to the array
                            jobs = Utilities.addJob(jobs, job);
                        }
                        break;
                    default:
                        // nothing
                        break;
                }

            }
        } catch (SQLException e) {
            Utilities.log("Failed to read jobs from database (by tag).");
            throw new RuntimeException(e);
        }
        // returning
        return jobs;
    }

    public Job[] getJobsByTags(String[] tags) {
        // READ jobs from the database by multiple tags
        // make a new array of jobs
        Job[] jobs = new Job[0];
        // go through each tag
        for (String tag : tags) {
            // get the jobs for that tag
            Job[] jobsByTag = getJobsByTag(tag);
            // add the jobs to the array
            jobs = Utilities.addJobs(jobs, jobsByTag);
        }
        return jobs;
    }
    
    public Job[] getRecentJobs(int amount) {
        // READ jobs from the database by order
        // make a new array of jobs
        Job[] jobs = new Job[0];
        // go through each tag
        try {
            // make a statement
            Statement stmt = conn.createStatement();
            // sort the jobs by id
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + JOB_TABLE_NAME + " ORDER BY " + JOB_VALUE_ID + " DESC;");
            // go through each job
            int count = 0;
            while (rs.next()) {
                // make a job object from the data
                Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                        rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                        rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                        rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                        rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                        rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                        rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                // add the job to the array
                jobs = Utilities.addJob(jobs, job);
                // if limited by size, break out
                count++;
                if (count >= amount && amount != -1) {
                    break;
                }
            }
        } catch (SQLException e) {
            Utilities.log("Failed to read jobs from database (by date, youngest).");
            throw new RuntimeException(e);
        }
        // returning
        return jobs;
    }

    public Job[] getOldestJobs(int amount) {
        // READ jobs from the database by order
        // make a new array of jobs
        Job[] jobs = new Job[0];
        // go through each tag
        try {
            // make a statement
            Statement stmt = conn.createStatement();
            // sort the jobs by id
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + JOB_TABLE_NAME + " ORDER BY " + JOB_VALUE_ID + " ASC;");
            // go through each job
            int count = 0;
            while (rs.next()) {
                // make a job object from the data
                Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
                        rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
                        rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
                        rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
                        rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
                        rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
                        rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
                // add the job to the array
                jobs = Utilities.addJob(jobs, job);
                // if limited by size, break out
                count++;
                if (count >= amount && amount != -1) {
                    break;
                }
            }
        } catch (SQLException e) {
            Utilities.log("Failed to read jobs from database (by date, oldest).");
            throw new RuntimeException(e);
        }
        // returning
        return jobs;
    }

    public boolean addJob(Job job) {
        // WRITE a job to the database
        try {
            // make a new statement
    		Statement stmt = conn.createStatement();
    		String query =  "INSERT INTO " + JOB_TABLE_NAME + " ";
    		query += "(SID, descript, cost, assembly_tag, cleaning_tag, storage_tag, repair_tag, petCare_tag, childCare_tag, mealPrep_tag, IThelp_tag, books_tag, sublets_tag , other_tag) VALUES (";
    		query += "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    		ps = conn.prepareStatement(query);
    		ps.setString(1, job.getUser());
    		ps.setString(2, job.getDescription());
    		ps.setInt(3, job.getCost());
    		ps.setInt(4, Utilities.sqlBoolean(job.getTagAssembly()));
    		ps.setInt(5, Utilities.sqlBoolean(job.getTagCleaning()));
    		ps.setInt(6, Utilities.sqlBoolean(job.getTagStorage()));
    		ps.setInt(7, Utilities.sqlBoolean(job.getTagRepair()));
    		ps.setInt(8, Utilities.sqlBoolean(job.getTagPetCare()));
    		ps.setInt(9, Utilities.sqlBoolean(job.getTagChildCare()));
    		ps.setInt(10, Utilities.sqlBoolean(job.getTagMealPrep()));
    		ps.setInt(11, Utilities.sqlBoolean(job.getTagITHelp()));
    		ps.setInt(12, Utilities.sqlBoolean(job.getTagBooks()));
    		ps.setInt(13, Utilities.sqlBoolean(job.getTagSublets()));
    		ps.setInt(14, Utilities.sqlBoolean(job.getTagOther()));
    		ps.executeUpdate();
    		ps.close();
            // return true
            return true;
        } catch (SQLException e) {
            // log and throw
            Utilities.log("Failed to write job to database (Job Name: " + job.getUser() + " with ID of " + job.getId() + ")");
            throw new RuntimeException(e);
            // return false; (unreachable)
        }
    }
//    public boolean addJob(Job job) {
//        // WRITE a job to the database
//        PostingRequest pr = new PostingRequest(job,conn);
//        ThreadPool.pt.add(pr);
//        return true;
//    }

//    public boolean removeJob(Job job) {
//        // remove a job from the database
//        try {
//            // make a new statement
//            Statement stmt = conn.createStatement();
//            // make a new query
//            String query = "DELETE FROM " + JOB_TABLE_NAME + " ";
//            query += "WHERE " + JOB_VALUE_ID + " = " + job.getId() + ";";
//            // execute the query
//            stmt.executeUpdate(query);
//            // close the statement
//            stmt.close();
//            // return true
//            return true;
//        } catch (SQLException e) {
//            // log and throw
//            Utilities.log("Failed to remove job from database (Job Name: " + job.getUser() + " with ID of " + job.getId() + ")");
//            throw new RuntimeException(e);
//            // return false; (unreachable)
//        }
//    }
    public boolean removeJob(Job job) {
        // remove a job from the database
        DelRequest dr = new DelRequest(job,conn);
        ThreadPool.dt.add(dr);
        return true;
    }
    
    public Job findByID(int id) {
		// READ a job from the database by ID
		try {
			// make a new statement
			Statement stmt = conn.createStatement();
			// make a new query
			String query = "SELECT * FROM " + JOB_TABLE_NAME + " ";
			query += "WHERE " + JOB_VALUE_ID + " = " + id + ";";
			// execute the query
			ResultSet rs = stmt.executeQuery(query);
			// go through each job
			while (rs.next()) {
				// make a job object from the data
				Job job = makeJob(rs.getInt(JOB_VALUE_ID), rs.getString(JOB_VALUE_DESCRIPTION),
						rs.getInt(JOB_VALUE_COST), rs.getString(JOB_VALUE_USER), rs.getBoolean(JOB_VALUE_TAG_ASSEMBLY),
						rs.getBoolean(JOB_VALUE_TAG_CLEANING), rs.getBoolean(JOB_VALUE_TAG_STORAGE),
						rs.getBoolean(JOB_VALUE_TAG_REPAIR), rs.getBoolean(JOB_VALUE_TAG_PET_CARE),
						rs.getBoolean(JOB_VALUE_TAG_CHILD_CARE), rs.getBoolean(JOB_VALUE_TAG_MEAL_PREP),
						rs.getBoolean(JOB_VALUE_TAG_IT_HELP), rs.getBoolean(JOB_VALUE_TAG_BOOKS),
						rs.getBoolean(JOB_VALUE_TAG_SUBLETS), rs.getBoolean(JOB_VALUE_TAG_OTHER));
				// return the job
				return job;
			}
		} catch (SQLException e) {
			// log and throw
			Utilities.log("Failed to read job from database (Job ID: " + id + ")");
			throw new RuntimeException(e);
			// return null; (unreachable)
		}
		// return null
		return null;
	}
    
    public void closeConnection() {
    	try {
	    	conn.close();
	    	Utilities.log("Disconnected from the database safely!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}