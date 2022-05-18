<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="headerloggedadmin.jsp"/>

    <h2>Dodaj administratora</h2>
    <section class="login-page">
        <h2>Załóż konto</h2>
        <form:form method="POST" modelAttribute="userForm" class="form-signin">
            
            <spring:bind path="username">
                <p class="title">Nazwa użytkownika:</p>
                <div class="form-group">
                    <form:input type="text" name="username" path="username"/>
                    <p class="subtitle"><form:errors path="username"/></p>
                </div>
            </spring:bind>

            <spring:bind path="firstName">
                <p class="title">Imię:</p>
                <div class="form-group">
                    <form:input type="text" name="firstName" path="firstName"/>
                    <p class="subtitle"><form:errors path="firstName"/></p>
                </div>
            </spring:bind>

            <spring:bind path="lastName">
                <p class="title">Nazwisko:</p>
                <div class="form-group">
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

            <spring:bind path="password">
                <div class="form-group">
                    <p class="title">Hasło:</p>
                    <form:input type="password" name="password" path="password"/>
                    <p class="subtitle"><form:errors path="password"/></p>
                </div>
            </spring:bind>
            <div class="form-group">
                <p class="title">Potwierdź hasło:</p>
                <input type="password" name="password2"/>

            </div>

            <div class="form-group form-group--buttons">
                <button class="btn" type="submit">Załóż konto</button>
            </div>
        </form:form>
    </section>
</section>


<%@ include file="../../footer.jsp" %>

</body>
</html>
