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
        <table>
            <tbody>
            <tr>
                <td>Ilość</td>
                <td>Kategorie:</td>
                <td>Organizacja:</td>
                <td>Ulica:</td>
                <td>Miasto:</td>
                <td>Kod pocztowy:</td>
                <td>Data odbioru:</td>
                <td>Godzina odbioru:</td>
                <td>Komentarz:</td>
                <td>Status:</td>
                <td>Data potwierdzenia:</td>
            </tr>
            <c:forEach items="${donations}" var="donation">
                <tr>
                <td>${donation.quantity}</td>
                <td>${donation.categoriesNames}</td>
                <td>${donation.institution.name}</td>
                <td>${donation.street}</td>
                <td>${donation.city}</td>
                <td>${donation.zipCode}</td>
                <td>${donation.pickUpDate}</td>
                <td>${donation.pickUpTime}</td>
                <td>${donation.pickUpComment}</td>
                <td>${donation.status.pickedUp}</td>
                <td>${donation.status.clickMoment}</td>
                <td><a href="/app/donations/${donation.id}">Szczegóły</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>
<%@ include file="../../footer.jsp" %>

</body>
</html>
