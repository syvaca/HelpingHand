package usc.teamfive.helpinghand.servlets;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import usc.teamfive.helpinghand.Utilities;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// set up connection
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		// differentiate between signup.jsp -> sign up & a form submit
		try {
			// this will error if it is not the sign up as it does not exist 
			String isSubmit = request.getParameter("action").toString();
			// so now we know that it is a sign up, so do the sign up stuff
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String sid = request.getParameter("sid");
			// debugging
			Utilities.log("New signup! The info is.. " + fname + " " + lname + " " + email + " " + password + " " + phone + " " + sid);
			// make an entry in the database
			Connection conn = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project?useSSL=false","root","mySQLPassword");
				PreparedStatement check = conn.prepareStatement("select * from user where usc_email = ?");
				check.setString(1, email);
				ResultSet rs = check.executeQuery();
				if(rs.next()) {
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("signup.jsp");
				}else {
					PreparedStatement pst = conn.prepareStatement("insert into user(sid, fname, lname, usc_email, pword, phone_number, validated) values (?, ?, ?, ?, ?, ?, ?)");
					pst.setString(2, fname);
					pst.setString(3, lname);
					pst.setString(4, email);
					pst.setString(5, password);
					pst.setString(6, phone);
					pst.setString(1, sid);
					pst.setInt(7, 1); // this is "validated" which we ended up not using
					int rowCount = pst.executeUpdate();
					if(rowCount>0) {
						request.setAttribute("status", "success");
					}
					else {
						request.setAttribute("status", "failed");
					}		
				}
				// redirect to login
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			finally {
				try {
					// close the connection
					conn.close();
				} catch (SQLException e) {
					// error, probably not even open
					Utilities.log("Failed to close the database connection. Is it even open?");
				}
			}
		} catch (Exception e) {
			// loading page, just redirect to sign up
			dispatcher = request.getRequestDispatcher("signup.jsp");       
			dispatcher.forward(request, response);
		}

		
		
	}



}