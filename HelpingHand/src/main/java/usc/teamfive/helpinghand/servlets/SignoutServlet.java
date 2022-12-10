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

/**
 * Servlet implementation class Login
 */
@WebServlet("/signout")
public class SignoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// set up connection
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		PrintWriter out = response.getWriter();
		
		// clear attributes of session
		session.removeAttribute("email");
		session.removeAttribute("name");
		session.removeAttribute("phone");
		session.removeAttribute("sid");

		// redirect to login page
		dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
		
	}


}