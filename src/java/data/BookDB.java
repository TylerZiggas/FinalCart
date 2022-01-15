// Author: Tyler Ziggas
// Date: April 2021

package data;

import java.sql.*;

import business.Product;

public class BookDB { // Class that deals with the books of the database
    public static int insert(Product product) { // Insert statement for Books
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO Book (Cover, Title, Price) " + "VALUES (?, ?, ?)"; // Statement for inserting
        try {
            ps = connection.prepareStatement(query); 
            ps.setString(1, product.getImage()); // Inserting the book into the database
            ps.setString(2, product.getTitle());
            ps.setString(3, product.getPriceCurrencyFormat());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps); // Always close connection
            pool.freeConnection(connection);
        }
    }

    public static int update(Product product) { // Update statement for Books
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE Book SET " + "Cover = ?, " + "Title = ? " + "WHERE Price = ?"; // Statement for updating
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getImage()); // Setting all of the information we were just given
            ps.setString(2, product.getTitle());
            ps.setString(3, product.getPriceCurrencyFormat());
            
            return ps.executeUpdate(); // Executing the command
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps); // Always close connection
            pool.freeConnection(connection);
        }
    }

    public static int delete(Product product) { // Delete statement for Books
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM Book " + "WHERE Title = ?"; // Delete book by title
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getTitle()); // Getting the title to make sure

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps); // Always close connection
            pool.freeConnection(connection);
        }
    }
    
    public static Product selectBook(String BookID) { // Select statement for Books
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Book " + "WHERE BookID = ?"; // Statement for selecting book by ID
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, BookID);
            rs = ps.executeQuery();
            Product product = null;
            if (rs.next()) {
                product = new Product();
                product.setCode(rs.getString("BookID")); // Set the local product type with information from BookID
                product.setImage(rs.getString("Cover"));
                product.setTitle(rs.getString("Title"));
                product.setPrice(rs.getDouble("Price"));
            }
            return product; // return that product
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps); // Always close connection
            pool.freeConnection(connection);
        }
    }
}