// Author: Tyler Ziggas
// Date: April 2021
// This is the servlet that processes the user's information for signing in and registering

package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import business.User; // Needed files for the user
import data.UserDB;

public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/signin.jsp"; // Initial url 
        
        String action = request.getParameter("action"); // get current action
        if (action == null) {
            action = "join";  // default action
        }

        if (action.equals("join")) {
            url = "/signin.jsp";    // the "join" page
        } else if (action.equals("add")) {
            String firstName = request.getParameter("firstName"); // Get the parameters that make up a user
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            User user = new User(firstName, lastName, email, password);

            // validate the parameters
            String message = "";
            if (UserDB.emailExists(user.getEmail())) { // Check if the email exists
                url = "/signin.jsp";
                message = "The password does not match.  Please try again.<br>"; // Message used to display if password is wrong, assume it is at first
                if (UserDB.passwordMatch(user.getEmail(), user.getPassword())) { // If email exists, check if password exists
                    message = "";
                    user = UserDB.selectUser(user.getEmail()); // return the user
                    url = "/thankyou.jsp";
                }
            } else { // If the email does not exist, we are sending them to registration page
                message = "";
                url = "/registration.jsp";
            }
            request.setAttribute("user", user);
            request.setAttribute("message", message);
            
        } else if (action.equals("register")) {
            String firstName = request.getParameter("firstName"); // Get the parameters that make up a user
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // store data in User object
            User user = new User(firstName, lastName, email, password);

            // validate the parameters
            String message = "";
            if (UserDB.emailExists(user.getEmail())) { // Check if email exists, if so we can't make the new account
                message = "This email address already exists.<br>" +
                          "Please enter another email address.";
                url = "/registration.jsp";
            }
            else {
                message = "";
                url = "/thankyou.jsp";
                UserDB.insert(user); // Insert the user as a new one into the database
            }
            request.setAttribute("user", user);
            request.setAttribute("message", message);
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }    
}