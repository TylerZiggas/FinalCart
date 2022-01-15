// Author: Tyler Ziggas
// Date: April 2021

package data;

import java.sql.*;

public class DBUtil {
    public static void closeStatement(Statement s) { // Closing the statement
        try {
            if (s != null) { // Check if null
                s.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void closePreparedStatement(Statement ps) { // Closing the prepared statement
        try {
            if (ps != null) { // Check if null
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void closeResultSet(ResultSet rs) { // Closing the result set
        try {
            if (rs != null) { // Check if null
                rs.close(); 
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}