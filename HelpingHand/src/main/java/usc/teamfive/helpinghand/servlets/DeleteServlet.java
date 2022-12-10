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
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// set up connection
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		// get id from request
		String id = request.getParameter("id");
		// login
        String databaseUrl = "jdbc:mysql://localhost/"; 
        DatabaseLogin login = new DatabaseLogin(databaseUrl, "Project", "root", "mySQLPassword"); // replace last with ur password
        JobDriver driver = new JobDriver(login);
        // find and delete job
        try {
        	Job findJob = driver.findByID(Integer.parseInt(id));
        	driver.removeJob(findJob);
        } catch (Exception e) {
        	System.out.println("Failed to remove job: " + e.toString());
        }
        // redirect
		dispatcher = request.getRequestDispatcher("profile");       
		dispatcher.forward(request, response);
	}
	
}
		