<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ include file="header.jsp" %>

    <section class="login-page">
      <h2>Załóż konto</h2>
      <form:form method="POST" modelAttribute="userForm" class="form-signin">

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
          <a href="/login" class="btn btn--without-border">Zaloguj się</a>
          <button class="btn" type="submit">Załóż konto</button>
        </div>
      </form:form>
    </section>

<%@ include file="footer.jsp" %>

  </body>
</html>
