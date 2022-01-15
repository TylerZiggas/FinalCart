// Author: Tyler Ziggas
// Date: April 2021

package business;

import java.io.Serializable;
import java.text.NumberFormat;

public class LineItem implements Serializable {

    private Product product; // Private product from product.java and the quantity
    private int quantity;

    public LineItem() {
    }

    public void setProduct(Product p) { // Setting the product
        product = p;
    }

    public Product getProduct() { // Getting the product
        return product;
    }

    public void setQuantity(int quantity) { // Setting the quantity
        this.quantity = quantity;
    }

    public int getQuantity() { // Getting the quantity
        return quantity;
    }

    public double getTotal() { // Getting the total amount 
        double total = product.getPrice() * quantity;
        return total;
    }

    public String getTotalCurrencyFormat() { // Putting that amount in currency format
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(this.getTotal());
    }
}