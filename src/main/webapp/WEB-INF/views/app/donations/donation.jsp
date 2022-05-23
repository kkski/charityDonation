<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../../headerloggedin.jsp"/>
<section class="help">
    <h2>Szczegóły Twojej darowizny</h2>
    <div class="summary">
        <div class="form-section">
            <h4>Oddajesz:</h4>
            <ul>
                <li>
                   ${donation.quantity} worków z kategorii

                       ${donation.categoriesNames}.


                </li>

                <li>
                    Dla fundacji ${donation.institution.name}.
                </li>
            </ul>
        </div>

        <div class="form-section form-section--columns">
            <div class="form-section--column">
                <h4>Adres odbioru:</h4>
                <ul>
                    ${donation.street}
                        ${donation.city}
                        ${donation.zipCode}
                </ul>
            </div>

            <div class="form-section--column">
                <h4>Termin odbioru:</h4>
                <ul>
                    <li>
                    Data: ${donation.pickUpDate}
                    </li><li>
                        Godzina:${donation.pickUpTime}
                </li><li>
                    Komentarz:${donation.pickUpComment}
                </li>
                </ul>
            </div>

            <div class="form-section--column">
                <h4>Potwierdzenie:</h4>
                <ul>
                    <li>
                        Data i czas: ${status.clickMoment}
                     </li>
                    <li>
                    Zatwierdzono: ${status.pickedUp}
                     </li>
                    <li>
                        <a href="/app/donations/${donation.id}/approve">Zatwierdź</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>


<%@ include file="../../footer.jsp" %>

</body>
</html>
