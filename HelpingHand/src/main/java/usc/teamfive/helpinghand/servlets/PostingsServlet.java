package usc.teamfive.helpinghand.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import usc.teamfive.helpinghand.datastructures.DatabaseLogin;
import usc.teamfive.helpinghand.datastructures.Job;
import usc.teamfive.helpinghand.drivers.JobDriver;


@WebServlet("/posts")
public class PostingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		 try {
	        	
	        	// see if we need to search or load default
	        	boolean toSearch = true;
	        	try {
	        		String search = request.getParameter("search");
	        		if (search.isEmpty() || search == null || search.equals("null")) toSearch = false;
	        	} catch (Exception e) {
	        		toSearch = false;
	        	}
	        	
	        	if (toSearch == false) {
	        		
		        	// connect to the database
		        	Class.forName("com.mysql.jdbc.Driver");
					String databaseUrl = "jdbc:mysql://localhost/"; 
					DatabaseLogin login = new DatabaseLogin(databaseUrl, "Project", "root", "mySQLPassword"); // replace last with ur password
					JobDriver driver = new JobDriver(login);
					
					// get the latest jobs
					Job latest[] = driver.getRecentJobs(-1); // (i made -1 indicate infinite loading)
					
					// in case it is empty, build our own w/ a message
					if (latest.length == 0) {
						Job insert = new Job(1, "There are no jobs posted yet. You could be the first!", 0, "user", false, false, false, false, false, false, false, false, false, false, false);
						latest = new Job[1];
						latest[0] = insert;
					}
					
					// close the connection now that we got the data
					driver.closeConnection();
					
					// set the header with the array under the value of "posts" 
					// so that they can later be accessed via their getters and setters in the jsp
					request.setAttribute("posts", latest);
					
					// redirect with the data
					RequestDispatcher dispatcher = request.getRequestDispatcher("postings.jsp");       
					dispatcher.forward(request, response);
					
	        	} else {

	        		// moved to Java Script
	        		
	        	}
			
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	 
}
