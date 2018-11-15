<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="adminpanel" tagdir="/WEB-INF/tags" %>

<%@ attribute name="pageName" required="true" %>
<%@ attribute name="customScript" required="false" fragment="true"%>

<!doctype html>
<html>
<adminpanel:htmlHeader/>

<body>
<adminpanel:bodyHeader menuName="${pageName}"/>

<div class="container-fluid">
    <div class="container xd-container">

        <jsp:doBody/>

    </div>
</div>
<adminpanel:footer/>
<jsp:invoke fragment="customScript" />

</body>

</html>
