<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="adminpanel" tagdir="/WEB-INF/tags" %>
<adminpanel:layout pageName="currencies">
    <h2>New currency</h2>
    <sf:form method="POST" modelAttribute="currency" >
        <%--<sf:errors path="*" element="div" cssClass="errors" />--%>
        <%--<sf:label path="currencyCode"--%>
                  <%--cssErrorClass="error">Currency Code</sf:label>:--%>
        <%--<sf:input path="currencyCode" cssErrorClass="error" /><br/>--%>
        <%--<sf:errors path="*" element="div" cssClass="errors" />--%>
        Currency code: <sf:input path="currencyCode" />
        <sf:errors path="currencyCode" /><br>
        <input type="submit" value="Register" />
    </sf:form>

</adminpanel:layout>