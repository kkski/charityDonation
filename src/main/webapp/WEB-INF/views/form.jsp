<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>

<%@ include file="header.jsp" %>
<div>
    <h3>Podaj dane donacji</h3>
    <form:form method="POST" modelAttribute="donation" class="form-signin" action="">
    <div class="form-section form-section--columns">
        <div class="form-section--column">

            <h4>Kategorie</h4>
            <form:checkboxes path="categories"
                             items="${categories}" itemLabel="name"/>
            <form:errors path="categories"></form:errors>

        </div>

        <div class="form-section--column">

            <h4>Instytucja</h4>
            <form:select path="institution" items="${institutions}" itemLabel="name"/>
            <form:errors path="institution"></form:errors>

        </div>

        <div class="form-section--column">

            <h4>Kod pocztowy</h4>
            <form:input path="zipCode"/>
            <form:errors path="zipCode"></form:errors>

        </div>


        <form:input path="street"/>
        <form:input path="city"/>
        <form:input path="quantity"/>
        <form:textarea path="pickUpComment"/>
        <form:input type="date" path="pickUpDate"/>
        <form:input type="time" path="pickUpTime"/>

        <button type="submit" class="btn">Submit</button>
        </form:form>

    </div>
</div>
    <%@ include file="footer.jsp" %>

    <script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>
