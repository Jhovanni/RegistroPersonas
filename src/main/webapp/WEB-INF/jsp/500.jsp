<%-- 
    Document   : denegado
    Created on : 19/02/2015, 09:01:18 PM
    Author     : Jhovanni
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib  prefix="s" uri="http://www.springframework.org/tags"%>
<s:message code="Titulo.500" var="titulo500"/>
<t:plantilla>
    <jsp:attribute name="titulo">
        ${titulo500}
    </jsp:attribute>
    <jsp:body>
        <p><s:message code="500.mensaje"/></p>
        <a href="${pageContext.request.contextPath}/"><s:message code="Link.regresar"/></a><br><br><br>
        <small>
            <s:message code="500.detalle" argumentSeparator=">" 
                       arguments="${nombreUsuario}>${excepcionTimeStamp}>
                       ${metodo}>${uri}>${excepcionClase}>${excepcionMensaje}"/>
        </small>
    </jsp:body>
</t:plantilla>