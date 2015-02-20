<%-- 
    Document   : encabezado
    Created on : 19/02/2015, 09:25:22 PM
    Author     : Jhovanni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<a href="${pageContext.request.contextPath}/">Inicio</a>
<sec:authorize access="isAnonymous()">
    <form action="${pageContext.request.contextPath}/login" method="POST">
        <label for="usuario"><s:message code="Persona.nombreUsuario"/></label>
        <input id="usuario" name="usuario"/>
        <label for="clave"><s:message code="Persona.clave"/></label>
        <input id="clave" name="clave" type="password"/>
        <label for="recordarme"><s:message code="Login.recordarme"/></label>
        <input type="checkbox" id="recordarme" name="recordarme"/>
        <button type="submit"><s:message code="Boton.ingresar"/></button>
    </form>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <s:message code="Texto.hola"/> <sec:authentication property="principal.username" />.
    <form action="${pageContext.request.contextPath}/logout">
        <button type="submit"><s:message code="Boton.desconectar"/></button>
    </form>
</sec:authorize>