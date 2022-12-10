package usc.teamfive.helpinghand.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usc.teamfive.helpinghand.Utilities;
import usc.teamfive.helpinghand.datastructures.DatabaseLogin;
import usc.teamfive.helpinghand.datastructures.User;

public class UserDriver {
	
	// private variables
    private Connection conn;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    private DatabaseLogin login;
    
    // only editable in IDE (in case things change)
    private final String USER_TABLE_NAME = "user";
    private final String USER_VALUE_NAME = "fname";
    private final String USER_VALUE_EMAIL = "usc_email";
    private final String USER_VALUE_PHONE = "phone_number";
    private final String USER_VALUE_ID = "SID";
    
    // constructor
    public UserDriver(DatabaseLogin login) {
        // assign the variables
        this.login = login;
        // attempt to connect to the database
        try {
            conn = DriverManager.getConnection(this.login.getLogin());
            st = conn.createStatement();
        } catch (SQLException e) {
            conn = null;
            st = null;
            ps = null;
            rs = null;
            throw new RuntimeException(e);
        } finally {
            Utilities.log((conn == null) ? "Failed to connect to database.." : "Connected to database!");
        }
    }
    
    // get user by id
	public User getByID(String id) {
		User user = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_VALUE_ID + " = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString(USER_VALUE_NAME), rs.getString(USER_VALUE_EMAIL), rs.getString(USER_VALUE_PHONE), rs.getString(USER_VALUE_ID));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utilities.log((user == null) ? "Failed to get user by id.." : "Got user by id!");
		}
		return null;
	}
	
}
