package usc.teamfive.helpinghand.drivers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

//import com.mysql.cj.xdevapi.Statement;
import usc.teamfive.helpinghand.Utilities;
import usc.teamfive.helpinghand.datastructures.DatabaseLogin;
import usc.teamfive.helpinghand.datastructures.Job;

//need to be put into same package as job
public class DelRequest extends Thread{
	private Job job;
	private Connection conn;
	public DelRequest(Job job, Connection conn) {
		this.job = job;
		this.conn = conn;
	}
	public void run() {
		try {
            // make a new statement
            Statement stmt = conn.createStatement();
            // make a new query
            String query = "DELETE FROM " + "job_postings" + " ";
            query += "WHERE " + "id" + " = " + job.getId() + ";";
            // execute the query
            stmt.executeUpdate(query);
            // close the statement
            stmt.close();
            // return true
            //return true;
        } catch (SQLException e) {
            // log and throw
            Utilities.log("Failed to remove job from database (Job Name: " + job.getUser() + " with ID of " + job.getId() + ")");
            throw new RuntimeException(e);
            // return false; (unreachable)
        }
}
}