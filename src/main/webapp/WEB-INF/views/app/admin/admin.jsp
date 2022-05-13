<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="headerloggedadmin.jsp"/>
<section class="login-page">
    <h2>Dzięki za zalogowanie jako admin</h2>
</section>

<jsp:include page="../../footer.jsp"/>


<script src="js/app.js"></script>
</body>
</html>
