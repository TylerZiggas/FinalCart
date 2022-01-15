// Author: Tyler Ziggas
// Date: April 2021

package business;

import java.io.Serializable;

public class User implements Serializable {
 
    private String email; // Private variables that consist of necessary components from database
    private String firstName;
    private String lastName;
    private String password;

    public User() { // Intialize all of the variables
        email = "";
        firstName = "";
        lastName = "";
        password = "";
    }

    public User(String firstName, String lastName, String email, String password) { // Getting the full user
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getEmail() { // Getting the email
        return email;
    }

    public void setEmail(String email) { // Setting the email
        this.email = email;
    }
    
    public String getFirstName() { // Getting the first name
        return firstName;
    }

    public void setFirstName(String firstName) { // Setting the first name
        this.firstName = firstName;
    }

    public String getLastName() { // Getting the last name
        return lastName;
    }

    public void setLastName(String lastName) { // Setting the last name
        this.lastName = lastName;
    }
    
    public String getPassword() { // Getting the password
        return password;
    }

    public void setPassword(String password) { // Setting the password
        this.password = password;
    }
}