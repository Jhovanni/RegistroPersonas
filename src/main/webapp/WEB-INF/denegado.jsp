<%-- 
    Document   : denegado
    Created on : 19/02/2015, 09:01:18 PM
    Author     : Jhovanni
--%>
<%@taglib  prefix="s" uri="http://www.springframework.org/tags"%>
<s:message code="Titulo.denegado" var="tituloDenegado"/>
<!DOCTYPE html>
<html>
    <head>
        <title>${tituloDenegado}</title>
    </head>
    <body>
        <h1><s:message code="Denegado.h1"/> ${nombreUsuario}:</h1>
        <p><s:message code="Denegado.mensaje"/></p>
        <a href="${pageContext.request.contextPath}/"><s:message code="Link.regresar"/></a>
    </body>
</html>
