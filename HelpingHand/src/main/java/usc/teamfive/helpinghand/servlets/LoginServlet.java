package usc.teamfive.helpinghand.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import usc.teamfive.helpinghand.Utilities;
import usc.teamfive.helpinghand.datastructures.DatabaseLogin;
import usc.teamfive.helpinghand.drivers.ThreadPool;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// set up connection
		ThreadPool tp = new ThreadPool();
		tp.start();
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		PrintWriter out = response.getWriter();
		
		// differentiate between login.jsp -> login and a login request
		try {
			// this will error if it is not the sign up as it does not exist 
			String isSubmit = request.getParameter("login");
			// connect to the database
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email == null || password == null || email.equals("null") || password.equals("null")) {
				// just loading page, redirect
				dispatcher = request.getRequestDispatcher("login.jsp");       
				dispatcher.forward(request, response);
				return;
			}
			Connection conn = null;
			try {
				System.out.println(email + " " + password);
				Class.forName("com.mysql.cj.jdbc.Driver");
		        String databaseUrl = "jdbc:mysql://localhost/"; 
		        DatabaseLogin login = new DatabaseLogin(databaseUrl, "Project", "root", "mySQLPassword"); 
				conn = DriverManager.getConnection(login.getLogin());
				PreparedStatement check = conn.prepareStatement("SELECT * FROM user WHERE usc_email = ? AND pword = ?");
				// see if it is valid, and if not, alert the user
	            check.setString(1, email);
	            check.setString(2, password);
	            ResultSet rs = check.executeQuery();
	            if(rs.next()) {
	            	System.out.println("found!");
					// login successful
					session.setAttribute("email", email);
					session.setAttribute("name", rs.getString("fname"));
					session.setAttribute("phone", rs.getString("phone_number"));
					session.setAttribute("sid", rs.getString("SID"));
					// redirect to profile page with the session
					dispatcher = request.getRequestDispatcher("profile");
					dispatcher.forward(request, response);
	            }else {
	            	System.out.println("login failed");
	            	loginFailed(request, response, dispatcher);
	            }
			} catch (Exception e) {
				// error, alert the user 
				System.out.println(e.toString());
				loginFailed(request, response, dispatcher);
			} finally {
				try {
					// close the connection
					conn.close();
				} catch (Exception e) {
					// error, probably not open
					Utilities.log("Failed to close the database connection. Is it even open?");
				}
			}
		} catch (Exception e) {
			// loading page, just redirect to login
			System.out.println(e.toString());
			dispatcher = request.getRequestDispatcher("login.jsp");       
			dispatcher.forward(request, response);
		} 
		
	}
	
	public void loginFailed(HttpServletRequest request, HttpServletResponse response, RequestDispatcher dispatcher) throws ServletException, IOException {
		request.setAttribute("error", "Incorrect email or password. Please try again or make a new account!");
		dispatcher = request.getRequestDispatcher("login.jsp");       
		dispatcher.forward(request, response);
	}
	
	


}