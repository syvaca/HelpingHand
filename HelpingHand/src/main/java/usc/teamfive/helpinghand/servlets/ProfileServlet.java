package usc.teamfive.helpinghand.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import usc.teamfive.helpinghand.datastructures.DatabaseLogin;
import usc.teamfive.helpinghand.datastructures.Job;
import usc.teamfive.helpinghand.datastructures.User;
import usc.teamfive.helpinghand.drivers.JobDriver;
import usc.teamfive.helpinghand.drivers.UserDriver;

/**
 * Servlet implementation class Login
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// set up connection
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		// get if the user is logged in
		try {
			if (session.getAttribute("email").toString().isEmpty()	) {
				// not logged in, redirect to sign up!
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			} else {
				// the user is logged in, so retrieve the needed data
				String thisSID = session.getAttribute("sid").toString();
				System.out.println(thisSID);
				// get user by id
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		        String databaseUrl = "jdbc:mysql://localhost/"; 
		        DatabaseLogin login = new DatabaseLogin(databaseUrl, "Project", "root", "mySQLPassword"); // replace last with ur password
		        UserDriver userDriver = new UserDriver(login);
		        try {
		        	User thisUser = userDriver.getByID(thisSID);
		        	if (thisUser == null) {
		        		// redirect to login
						System.out.println("redirecting");
						dispatcher = request.getRequestDispatcher("login.jsp");       
						dispatcher.forward(request, response);
						return;
		        	}
		        	// get all jobs by this user
		        	System.out.println("made it here");
		        	JobDriver thisJobDriver = new JobDriver(login);
		        	Job[] allJobs = thisJobDriver.getJobsByUserID(thisSID);
		        	// post to page 
		            request.setAttribute("posts", allJobs);
		            System.out.println(allJobs.length);
		            dispatcher = request.getRequestDispatcher("profile_loaded.jsp");       
		            dispatcher.forward(request, response);  
		        } catch (Exception e) {
		        	// redirect to login
					dispatcher = request.getRequestDispatcher("login.jsp");       
					dispatcher.forward(request, response);
					return;
		        }
			}
		} catch (Exception e) {
        	// redirect to login
			dispatcher = request.getRequestDispatcher("login.jsp");       
			dispatcher.forward(request, response);
			return;
		}
		
	}

}