// Author: Tyler Ziggas
// Date: April 2021

package data;

import java.sql.*;

public class SQLUtil {
    public static String getHtmlTable(ResultSet results) // Initial creation of our table
            throws SQLException {
        
        StringBuilder htmlTable = new StringBuilder(); // Ready to build our table fro mthe database
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();

        htmlTable.append("<table>"); // Start of table
        int itemID = 1; // Item will always start at 1
        htmlTable.append("<tr>"); // Beginning of row
        for (int i = 2; i <= columnCount; i++) {
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnName(i));
            htmlTable.append("</th>");
        }
        htmlTable.append("<th>");
        htmlTable.append("Add to Cart");
        htmlTable.append("</th>");
        htmlTable.append("</tr>"); // End of row

        // add all other rows
        while (results.next()) {
            htmlTable.append("<tr>"); // Beginning of row
            for (int i = 2; i <= columnCount; i++) {
                if (i == 2) { // The second column is an image
                    htmlTable.append("<td> <a href=\"");
                    htmlTable.append(results.getString(i));
                    htmlTable.append("\" target=\"_blank\">\n <img src=\" ");
                    htmlTable.append(results.getString(i));
                    htmlTable.append("\" width=\"100\" height=\"130\"</a></td>"); // Set up the size of the cover
                } else if (i == 4) { // The fourth column is the amount
                    htmlTable.append("<td>$");
                    htmlTable.append(results.getString(i));
                    htmlTable.append("</td>");
                }else {
                    htmlTable.append("<td>");
                    htmlTable.append(results.getString(i));
                    htmlTable.append("</td>");
                }
            }
            htmlTable.append("<td>");
            htmlTable.append("<form action=\"cart\" method=\"post\">\n <input type=\"hidden\" name=\"productCode\" value=\""); // Lastly add button
            htmlTable.append(itemID);
            htmlTable.append("\">\n <input type=\"submit\" value=\"Add To Cart\">\n </form>");
            htmlTable.append("</td>");
            htmlTable.append("</tr>"); // End of row
            itemID++; // Next item
        }

        htmlTable.append("</table>"); // End of table
        return htmlTable.toString();
    }
}