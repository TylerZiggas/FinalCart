// Author: Tyler Ziggas
// Date: April 2021

package data;

import java.sql.*;

import business.User;

public class UserDB { // Class that deals with the database for the users
    public static int insert(User user) { // Insert for the user table
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO User (Email, FirstName, LastName, Password) " + "VALUES (?, ?, ?, ?)"; // Base statement for inserting
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail()); // Insert the parts of the user
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps); // Always free connection
            pool.freeConnection(connection);
        }
    }

    public static int update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE User SET " + "FirstName = ?, " + "LastName = ?, " + "Password = ? " + "WHERE Email = ?"; // Base statement for updating
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName()); // Update all parts of the user
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps); // Always free connection
            pool.freeConnection(connection);
        }
    }

    public static int delete(User user) { // Deleting a user
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM User " + "WHERE Email = ?"; // Base statement for deleting
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail()); // Find that email to make sure

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally { 
            DBUtil.closePreparedStatement(ps); // Always free connection
            pool.freeConnection(connection);
        }
    }

    public static boolean emailExists(String email) { // Check if the email exists, need for registration mainly
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Email FROM User " + "WHERE Email = ?"; // Base for finding email
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email); // Grab the email for the statement
            rs = ps.executeQuery();
            return rs.next(); // Returm whether or not it exists
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps); // Always free connection
            pool.freeConnection(connection);
        }
    }

    public static boolean passwordMatch(String email, String password) { // Check if password matches, need for signing in
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean match = false;
            
        String query = "SELECT Password FROM User " + "WHERE Email = ?"; // Statement for searching for password in database
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email); // Set the email
            rs = ps.executeQuery(); // Execute the query
            if (rs.next()) { // If we found something
                String result = rs.getString("Password");
                if (result.equals(password)) { // Check if what came back matches
                    match = true;
                } else {
                    match = false;
                }
            }
            return match;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps); // Always free connection
            pool.freeConnection(connection);
        }
    }
    
    public static User selectUser(String email) { // Selecting the user
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User " + "WHERE Email = ?"; // Base statement for selecting
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email); // Using the email given
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) { // While next is true
                user = new User(); // Establish the user and all parts
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
            }
            return user; // return the user 
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps); // Always free connection
            pool.freeConnection(connection);
        }
    }
}