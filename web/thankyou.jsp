<%-- 
    Document   : thankyou
    Created on : Apr 2021
    Author     : Ziggas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html" />

<h1>Thank You For Your Order!</h1>

<label>First Name:</label><br>
<span>${user.firstName}</span><br><br>
<label>Last Name:</label><br>
<span>${user.lastName}</span><br><br>
<label>Your Email:</label><br>
<span>${user.email}</span><br><br>

<table>
    <tr>
        <th> Book Cover </th>
        <th> Book Title </th>
        <th> Price </th>
        <th> Amount </th>
        <th> Quantity </th>
    </tr>

    <c:forEach var="item" items="${cart.items}">
        <tr>
            <td> <a href="${item.product.image}" target="_blank">
                    <img src="${item.product.image}" width="100" height="130"</a></td>
            <td>${item.product.title}</td>
            <td>${item.product.priceCurrencyFormat}</td>
            <td>${item.totalCurrencyFormat}</td>
            <td>${item.quantity}</td>
        </tr>
    </c:forEach>
    <tr>
        <th> Total </th>
        <th>  </th>
        <th>  </th>
        <th> ${cart.totalAll} </th>
        <th>  </th>
    </tr>
</table>

<p>To request another order, please hit the return button below.</p>

<form action="cart" method="post">
    <input type="hidden" name="action" value="return">
    <input type="submit" value="Return">
</form><br>

<c:import url="/includes/footer.jsp"/>