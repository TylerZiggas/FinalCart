// Author: Tyler Ziggas
// Date: April 2021

package business;

import java.io.Serializable;
import java.text.NumberFormat;

public class Product implements Serializable {

    private String code; // Private variables that consist of necessary components from database
    private String title;
    private double price;
    private String image;

    public Product() { // Intialize all of the variables
        code = "";
        title = "";
        price = 0;
    }

    public void setCode(String code) { // Setting the product code
        this.code = code;
    }

    public String getCode() { // Getting the product code
        return code;
    }

    public void setTitle(String title) { // Setting the title
        this.title = title;
    }

    public String getTitle() { // Getting the title
        return title;
    }

    public void setPrice(double price) { // Setting the price
        this.price = price;
    }

    public double getPrice() { // Getting the price
        return price;
    }
    
    public void setImage(String image) { // Setting the image url
        this.image = image;
    }

    public String getImage() { // Getting the image url
        return image;
    }

    public String getPriceCurrencyFormat() { // Formatting the price for currency
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
}