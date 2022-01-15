// Author: Tyler Ziggas
// Date: April 2021

package business;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Cart implements Serializable {

    private ArrayList<LineItem> items; // Private array list of items in the cart
    private double totalAll;

    public Cart() { // Initialize it
        items = new ArrayList<LineItem>();
        totalAll = 0;
    }

    public ArrayList<LineItem> getItems() { // Getting the items if needed
        return items;
    }

    public int getCount() { // Getting hte item count if needed
        return items.size();
    }
    
    public void setTotal(double total) { // Setting the price
        this.totalAll = total;
    }
    
    public double getTotal() { // Getting hte item count if needed
        return totalAll;
    }

    public void addItem(LineItem item) { // Function for adding items
        String code = item.getProduct().getCode();
        int quantity = item.getQuantity();
        for (LineItem cartItem : items) {
            if (cartItem.getProduct().getCode().equals(code)) {
                cartItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }
    
    public void removeItem(LineItem item) { // Function for removing items
        String code = item.getProduct().getCode();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }
    
    public void clearCart() { // Function for clearing the cart after returning from thankyou.jsp
        int removeItem = 0;
        int initialSize = items.size();
        for (int i = 0; i < initialSize; i++) {
                items.remove(removeItem);
            }
        }
    
    public String getTotalAll() { // Calculate the total of the cart
        double total = 0; // Default should be 0 
        for (int i = 0; i < items.size(); i++) {
                LineItem lineItem = items.get(i);
                total = total + lineItem.getTotal(); // Get total of each item
        }
        totalAll = total;
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(totalAll); // Format and store
    }
}