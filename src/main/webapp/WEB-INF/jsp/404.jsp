<%-- 
    Document   : denegado
    Created on : 19/02/2015, 09:01:18 PM
    Author     : Jhovanni
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib  prefix="s" uri="http://www.springframework.org/tags"%>
<s:message code="Titulo.404" var="titulo"/>

<t:plantilla>
    <jsp:attribute name="titulo">
        ${titulo}
    </jsp:attribute>
    <jsp:body>
        <p><s:message code="404.mensaje"/></p>
        <a href="${pageContext.request.contextPath}/"><s:message code="Link.regresar"/></a>
    </jsp:body>
</t:plantilla>