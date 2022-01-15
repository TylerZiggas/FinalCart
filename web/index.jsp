<%-- 
    Document   : index
    Created on : Apr 2021
    Author     : Ziggas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html" />

<h1>Book Fair Order Form</h1>
<p>Please Select Books You Would Like to Add to Your Cart</p>

${sqlResult}

<c:import url="/includes/footer.jsp"/>