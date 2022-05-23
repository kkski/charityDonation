<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="app/headerloggedin.jsp"/>

<section class="login-page">

    <h2>Edytuj swój profil</h2>

    <form:form method="POST" modelAttribute="userForm" class="form-signin">

        <spring:bind path="firstName">

            <div class="form-group">
                <p class="title">Imię:</p>
                <form:input type="text" name="firstName" path="firstName"/>
                <p class="subtitle"><form:errors path="firstName"/></p>
            </div>
        </spring:bind>

        <spring:bind path="lastName">

            <div class="form-group">
                <p class="title">Nazwisko:</p>
                <form:input type="lastName" name="lastName" path="lastName"/>
                <p class="subtitle"><form:errors path="lastName"/></p>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group">
                <p class="title">Email:</p>
                <form:input type="email" name="email" path="email"/>
                <p class="subtitle"><form:errors path="email"/></p>
            </div>
        </spring:bind>

        <sec:authorize access="hasRole('ROLE_USER')">
            <div class="form-group">
                <p class="title">Stare hasło:</p>
                <form:input type="password" name="oldPassword" path="oldPassword"/>
                <p class="subtitle"><form:errors path="oldPassword"/></p>
            </div>
        </sec:authorize>

        <spring:bind path="password">
            <div class="form-group">
                <p class="title">Hasło:</p>
                <form:input type="password" name="password" path="password"/>
                <p class="subtitle"><form:errors path="password"/></p>
            </div>
        </spring:bind>
        <spring:bind path="password2">
            <div class="form-group">
                <p class="title">Potwierdź hasło:</p>
                <form:input type="password" name="password2" path="password2"/>
                <p class="subtitle"><form:errors path="password2"/></p>

            </div>
        </spring:bind>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Wykonaj</button>
        </div>
    </form:form>
</section>

<%@ include file="footer.jsp" %>

</body>
</html>
