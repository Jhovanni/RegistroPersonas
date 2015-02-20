<%-- 
    Document   : login
    Created on : 17/02/2015, 11:26:27 PM
    Author     : Jhovanni
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<s:message code="Titulo.paginaLogin" var="tituloPaginaLogin"/>
<!DOCTYPE html>
<html>
    <head>
        <title>${tituloPaginaLogin}</title>
    </head>
    <body>
        <h1>${tituloPaginaLogin}</h1>        
        <form action="login" method="post">
            <fieldset>
                <legend><s:message code="Login.leyenda"/></legend>
                <c:if test="${param.error != null}">
                    <div><s:message code="Login.incorrecto"/></div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div><s:message code="Login.desconectado"/></div>
                </c:if>
                <label for="usuario"><s:message code="Persona.nombreUsuario"/></label>
                <input id="usuario" name="usuario"/><br>
                <label for="clave"><s:message code="Persona.clave"/></label>
                <input id="clave" name="clave" type="password"/><br>
                <label for="recordarme"><s:message code="Login.recordarme"/></label>
                <input type="checkbox" id="recordarme" name="recordarme"/>
                <button type="submit"><s:message code="Boton.ingresar"/></button>
            </fieldset>            
        </form>
        <br><a href="persona/lista"><s:message code="Link.listaPersonas"/></a>
    </body>
</html>
