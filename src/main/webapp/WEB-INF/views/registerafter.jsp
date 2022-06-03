<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ include file="header.jsp" %>

    <section class="login-page">
      <h2>Konto stworzone!</h2>
      Aktywuj konto przez link wysłany w wiadomości e-mail, a następnie zaloguj się.
    </section>

<%@ include file="footer.jsp" %>

  </body>
</html>
