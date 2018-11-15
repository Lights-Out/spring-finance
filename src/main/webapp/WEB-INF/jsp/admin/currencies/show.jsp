<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="adminpanel" tagdir="/WEB-INF/tags" %>
<adminpanel:layout pageName="currencies">
    <h2>Currency ${currency['currencyCode']}</h2>
    Currency ${currency['currencyCode']}
    Created at ${currency['created']}
</adminpanel:layout>