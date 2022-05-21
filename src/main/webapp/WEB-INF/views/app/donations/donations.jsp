<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../../headerloggedin.jsp"/>
<section class="help">
    <h2>Zarządzaj donacjami</h2>
    <h3><a href="/donation">Dodaj nową donację</a></h3>
    <div class="help--slides active">
        <ul class="help--slides-items">
            <c:forEach items="${users}" var = "i" begin = "0" end = "10" varStatus="loop">
                <c:if test="${loop.index % 2 == 0}"><li></c:if>
                <div class="col">
                    <div class="title">${i.username}</div>
                    <div class="subtitle">${i.email}</div>

                    <div class="subtitle"><c:if test="${i.enabled == 0}">Zablokowano, <a href="/admin/users/unblock/${i.id}">Odblokuj</a></c:if>
                        <c:if test="${i.enabled == 1}"><a href="/admin/users/block/${i.id}">Zablokuj</a></c:if>
                        <a href="/admin/users/edit/${i.id}">Edytuj</a> <a href="/admin/users/delete/${i.id}">Usuń</a></div>

                </div>
                <c:if test="${loop.index + 1 % 2 == 0}"></li></c:if>
            </c:forEach>

        </ul>
    </div>
</section>

<%@ include file="../../footer.jsp" %>

</body>
</html>
