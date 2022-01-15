<%-- 
    Document   : cart
    Created on : Apr 2021
    Author     : Ziggas
--%>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html" />

<h1>Your cart</h1>
<p><i>${message}</i></p>

<table>
    <tr>
        <th> Book Cover </th>
        <th> Book Title </th>
        <th> Price </th>
        <th> Amount </th>
        <th> Quantity </th>
        <th></th>
    </tr>

    <c:forEach var="item" items="${cart.items}">
        <tr>
            <td> <a href="${item.product.image}" target="_blank">
                <img src="${item.product.image}" width="100" height="130"</a></td>
            <td>${item.product.title}</td>
            <td>${item.product.priceCurrencyFormat}</td>
            <td>${item.totalCurrencyFormat}</td>
            <td>
                <form action="" method="post">
                    <input type="hidden" name="productCode" value="${item.product.code}">
                    <input type=text name="quantity" value="${item.quantity}" id="quantity" size="3">
                    <input type="submit" value="Update">
                </form>
            </td>
            <td>
                <form action="" method="post">
                    <input type="hidden" name="productCode" 
                           value="${item.product.code}">
                    <input type="hidden" name="quantity" 
                           value="0">
                    <input type="submit" value="Remove Item">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<p><b>To change the quantity</b>, enter the new quantity 
    and click on the Update button.</p>

<form action="" method="post">
    <input type="hidden" name="action" value="shop">
    <input type="submit" value="Continue Shopping">
</form><br>

<form action="cart" method="post">
    <input type="hidden" name="action" value="checkout">
    <input type="submit" value="Checkout">
</form>

</body>

<c:import url="/includes/footer.jsp"/>
