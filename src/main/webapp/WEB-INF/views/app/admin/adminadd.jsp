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
                <div class="form-group">
                    <form:input type="text" name="username" placeholder="Username" path="username"/>
                </div>
            </spring:bind>
            <spring:bind path="firstName">
                <div class="form-group">
                    <form:input type="text" name="firstName" placeholder="First name" path="firstName"/>
                </div>
            </spring:bind>
            <spring:bind path="lastName">
                <div class="form-group">
                    <form:input type="lastName" name="lastName" placeholder="Last name" path="lastName"/>
                </div>
            </spring:bind>

            <spring:bind path="email">
                <div class="form-group">
                    <form:input type="email" name="email" placeholder="Email" path="email"/>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group">
                    <form:input type="password" name="password" placeholder="Hasło" path="password"/>
                </div>
            </spring:bind>
            <div class="form-group">
                <input type="password" name="password2" placeholder="Powtórz hasło" />
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
