<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="adminpanel" tagdir="/WEB-INF/tags" %>
<adminpanel:layout pageName="currencies">
<h2>Currencies</h2>
    <a href="<spring:url value="/admin/currencies/new" htmlEscape="true" />">Add new</a>
    <table id="vetsTable" class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Code</th>
                <th>Created</th>
                <th>Changed</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${currencies}" var="currency">
            <tr>
                <td>
                    <c:out value="${currency.id}"/>
                </td>
                <td>
                    <c:out value="${currency.currencyCode}"/>
                </td>
                <td>
                    <c:out value="${currency.created}"/>
                </td>
                <td>
                    <c:out value="${currency.updated}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</adminpanel:layout>