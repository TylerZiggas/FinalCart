// Author: Tyler Ziggas
// Date: April 2021
// This is the servlet used for the landing page to show all of the books avaliable

package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import data.SQLUtil; 

public class SqlGatewayServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("type/html"); // Set content type since we need 
        
        String sqlStatement = "Select * From Book"; // Want to display everything
        String sqlResult = "";
        try {
            Class.forName("com.mysql.jdbc.Driver"); // load the driver
            
            String dbURL = "jdbc:mysql://localhost:3306/hw4";
            String username = "student";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password); // Establish a connection for the landing page

            Statement statement = connection.createStatement(); // create a statement

            sqlStatement = sqlStatement.trim(); // parse the SQL string just in case
            if (sqlStatement.length() >= 6) {
                String sqlType = sqlStatement.substring(0, 6);
                if (sqlType.equalsIgnoreCase("select")) {
                    ResultSet resultSet = statement.executeQuery(sqlStatement); // create the HTML for the result set
                    sqlResult = SQLUtil.getHtmlTable(resultSet);
                    resultSet.close();
                } else {
                    int i = statement.executeUpdate(sqlStatement);
                }
            }
            statement.close();
            connection.close(); // Make sure to close the connection
        } catch (ClassNotFoundException e) { // Exception in case of problem
            sqlResult = "<p>Error loading the databse driver: <br>"
                    + e.getMessage() + "</p>";
        } catch (SQLException e) { // Exception in case of problem
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
        }

        HttpSession session = request.getSession();
        session.setAttribute("sqlResult", sqlResult);
        session.setAttribute("sqlStatement", sqlStatement);

        String url = "/index.jsp"; // Set the url for where we are going
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}