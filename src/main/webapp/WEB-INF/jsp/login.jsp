<%-- 
    Document   : login
    Created on : 17/02/2015, 11:26:27 PM
    Author     : Jhovanni
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<s:message code="Titulo.paginaLogin" var="tituloPaginaLogin"/>

<t:plantilla>
    <jsp:attribute name="titulo">
        ${tituloPaginaLogin}
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <h1>${tituloPaginaLogin}</h1>
            <form action="login" method="post">
                <c:if test="${param.error != null}">
                    <div class="text-danger"><s:message code="Login.incorrecto"/></div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="text-success"><s:message code="Login.desconectado"/></div>
                </c:if>
                <div class="form-group">
                    <label for="usuario"><s:message code="Persona.nombreUsuario" var="nombreUsuarioLabel"/></label>
                    <input id="usuario" name="usuario" class="form-control" placeholder="${nombreUsuarioLabel}"/>
                </div>
                <div class="form-group">
                    <label for="clave"><s:message code="Persona.clave" var="claveLabel"/></label>
                    <input id="clave" name="clave" type="password" class="form-control" placeholder="${claveLabel}"/>
                </div>
                <button type="submit" class="btn btn-default"><s:message code="Boton.ingresar"/></button>
            </form>
        </div>
    </jsp:body>
</t:plantilla>