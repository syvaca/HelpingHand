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
@WebServlet("/post")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try {
			// try and get the information of the post
	        String title = request.getParameter("title");
	        String description = request.getParameter("description");
	        String price = request.getParameter("price");
	        String tag = request.getParameter("tag").toLowerCase().replace(" ", "");
	        System.out.println(tag);
	        System.out.println(title + description + price + tag);
	        // add to the database
        	Class.forName("com.mysql.jdbc.Driver");
			String databaseUrl = "jdbc:mysql://localhost/"; 
			DatabaseLogin login = new DatabaseLogin(databaseUrl, "Project", "root", "mySQLPassword"); // replace last with ur password
			JobDriver driver = new JobDriver(login);
			// switch between tags
			boolean[] tags = new boolean[14];
			switch (tag) { // assembly will be default
				case "cleaning_tag":
					tags[1] = true;
					break;
				case "storage_tag":
					tags[2] = true;
					break;
				case "repair_tag":
					tags[3] = true;
					break;
				case "petcare_tag":
					tags[4] = true;
					break;
				case "childcare_tag":
					tags[5] = true;
					break;
				case "mealprep_tag":
					tags[6] = true;
					break;
				case "ithelp_tag":
					tags[7] = true;
					break;
				case "books_tag":
					tags[8] = true;
					break;
				case "sublets_tag":
					tags[9] = true;
					break;
				case "other_tag":
					tags[10] = true;
					break;
				default: // assume assembly? because its the default ig
					tags[0] = true;
					break;
			}
			// formatting the cost to input
			price = price.replaceAll("[^0-9]", "");
			int cost = Integer.parseInt(price);
			// make the job (id can be ignored it will auto-increment)
			HttpSession session = request.getSession();
			String thisSID = session.getAttribute("sid").toString();
			if (thisSID.isEmpty() || thisSID == null) {
				throw new Exception("No SID!");
			}
			Job thisJob = new Job(0, description, cost, thisSID, tags[0], tags[1], tags[2], tags[3], tags[4], tags[5], tags[6], tags[7], tags[8], tags[9], tags[10]);
			driver.addJob(thisJob);
			driver.closeConnection();
			// redirect
			RequestDispatcher dispatcher = request.getRequestDispatcher("/profile");       
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// could not find the right information, back to profile
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");       
			dispatcher.forward(request, response);
		}
		
	}
	

}
