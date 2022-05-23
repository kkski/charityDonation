<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../../headerloggedin.jsp"/>
<section class="help">
    <h2>Zarządzaj donacjami</h2>
    <div class="help--slides active">
        <ul class="help--slides-items">
            <c:forEach items="${donations}" var="donation">
            Dla: ${donation.institution.name}
                Ilość: ${donation.quantity}

            </c:forEach>

        </ul>
    </div>
</section>
<%@ include file="../../footer.jsp" %>

</body>
</html>
