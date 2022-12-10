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


@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
        try {
        	
        	// connect to the database
			Class.forName("com.mysql.jdbc.Driver");	
	        String databaseUrl = "jdbc:mysql://localhost/"; 
	        DatabaseLogin login = new DatabaseLogin(databaseUrl, "Project", "root", "mySQLPassword"); // replace last with ur password
	        JobDriver driver = new JobDriver(login);
	        
	        // get the tags
	        String tag = request.getParameter("tag");
	       
	        
	        // searchy by tags
            Job[] filtered = driver.getJobsByTag(tag);
            
            // if 0, add a message
            if (filtered == null) {
                Job insert = new Job(1, "There are no jobs posted yet that match these tag(s). You could be the first!", 0, "user", false, false, false, false, false, false, false, false, false, false, false);
                filtered = new Job[1];
                filtered[0] = insert;
            } else if (filtered.length == 0) {
                Job insert = new Job(1, "There are no jobs posted yet that match these tag(s). You could be the first!", 0, "user", false, false, false, false, false, false, false, false, false, false, false);
                filtered = new Job[1];
                filtered[0] = insert;
            }
            
            // close the connection now that we got the data
            driver.closeConnection();
            
            // redirect with the data
            request.setAttribute("posts", filtered);
            RequestDispatcher dispatcher = request.getRequestDispatcher("postings_search.jsp");       
            dispatcher.forward(request, response);      
        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	 
}
