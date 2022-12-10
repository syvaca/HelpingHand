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
@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try {
			// try and get the information of the post
	        String jobSID = request.getParameter("sid");
	        // connect to database
        	Class.forName("com.mysql.jdbc.Driver");
			String databaseUrl = "jdbc:mysql://localhost/"; 
			DatabaseLogin login = new DatabaseLogin(databaseUrl, "Project", "root", "mySQLPassword"); // replace last with ur password
			UserDriver user = new UserDriver(login);
			// get the job by id
			User userByID = user.getByID(jobSID);
			// contact email and phone number
			String phoneNumber = userByID.getPhone();
			String email = userByID.getEmail();
			// get the data from the job
			String alertMessage = "You can contact them at the following email: " + email + " or at the phone number " + phoneNumber + ".";
			// set the attribute to the alert message
			request.setAttribute("alert", alertMessage);
			// and redirect
			RequestDispatcher dispatcher = request.getRequestDispatcher("/contact.jsp");       
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// could not find the right information, back to profile
			RequestDispatcher dispatcher = request.getRequestDispatcher("posts.jsp");       
			dispatcher.forward(request, response);
		}
		
	}
	

}
