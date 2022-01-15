<%-- 
    Document   : registration
    Created on : Apr 2021
    Author     : Ziggas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html" />

<h1>New User Registration</h1>
<p>Please register for our website</p>
<p><i>${message}</i></p>
<form action="user" method="post">
    <input type="hidden" name="action" value="register">        
    <label class="pad_top">Email:</label>
    <input type="email" name="email" value="${user.email}" 
           required><br>
    <label class="pad_top">First Name:</label>
    <input type="text" name="firstName" value="${user.firstName}" 
           required><br>
    <label class="pad_top">Last Name:</label>
    <input type="text" name="lastName" value="${user.lastName}"  
           required><br>   
    <label class="pad_top">Password:</label>
    <input type="password" name="password" value="${user.password}"  
           required><br>
    <input type="submit" value="Join Now" class="margin_left">
</form>

<c:import url="/includes/footer.jsp"/>