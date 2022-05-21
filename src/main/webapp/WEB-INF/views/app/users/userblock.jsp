<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../../headerloggedin.jsp"/>

<section class="login-page">
    <h2>Zablokuj użytkownika</h2>
    <div class="col">
        <div class="title">Nazwa użytkownika: ${userToBlock.username}</div>
        <div class="subtitle">Email: ${userToBlock.email}</div>
    </div>
    <a href="/admin/users/block/${userToBlock.id}/confirm">Potwierdź zablokowanie</a>
</section>


<%@ include file="../../footer.jsp" %>

</body>
</html>
