// Author: Tyler Ziggas
// Date: April 2021
// This is a servlet that takes in the user inputs and checks whether the inputs are good 
// It also stores them in the JavaBean class to be used in the dynamic jsp page and routes you to said jsp file

package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import business.LineItem; // Include all of the necessary JavaBean classes
import business.Cart;
import business.Product;
import data.BookDB;

public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Only using doPost
        String url = "/index.jsp"; // Initial url just take us to the page for shopping
        ServletContext sc = getServletContext(); // Get the context
        

        String action = request.getParameter("action");
        if (action == null) { // Setting the default action
            action = "cart";  
        }

        if (action.equals("shop")) { // If shop, we want to go to where we can shop
            url = "/index.jsp";    
            
        } else if (action.equals("cart")) { // If cart, we are changing the cart
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) { // If cart is null, we create a new one
                cart = new Cart();
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) { // If quanitity is less than 0, just make it one
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) { // Or make 1 if letters are typed
                quantity = 1;
            }

            Product product = BookDB.selectBook(productCode); // Grab whichever product it is

            LineItem lineItem = new LineItem();
            lineItem.setProduct(product);
            lineItem.setQuantity(quantity);
            if (quantity > 0) { // The item gets added if the quanitity is now greater than 1
                cart.addItem(lineItem);
            } else if (quantity == 0) { // Remove the item if the quanitity is 0
                cart.removeItem(lineItem);
            }

            session.setAttribute("cart", cart);
            url = "/cart.jsp"; // Go to cart now
            
        } else if (action.equals("checkout")) { // If it was checkout, we want to thank them
            String message = "";
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null || cart.getCount() == 0) { // If cart is null, we create a new one
                message = "Please add an item to the cart before trying to checkout.<br>";
                url = "/cart.jsp";
            } else {
                message = "";
                url = "/signin.jsp";
            }
            request.setAttribute("message", message);
        } else if (action.equals("return")) { // If we are returning, we want to clean the cart go back to start
            url = "/index.jsp";
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            cart.clearCart();
        }

        sc.getRequestDispatcher(url).forward(request, response);
    }
}
