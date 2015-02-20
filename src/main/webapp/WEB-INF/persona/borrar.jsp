<%-- 
    Document   : borrar
    Created on : 13/02/2015, 05:21:31 PM
    Author     : Jhovanni
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<s:message code="Titulo.borrarPersona" var="tituloBorrarPersona"/>
<!DOCTYPE html>
<html>
    <head>
        <title>${tituloBorrarPersona}</title>
    </head>
    <body>
        <%@ include file="../encabezado.jsp" %>
        <h1>${tituloBorrarPersona}</h1>
        <c:if test="${personaBorrada}"><s:message code="PersonaBorrada"/><br></c:if>
        <c:if test="${persona==null}" var="noEncontrada"><s:message code="PersonaNoEncontrada"/></c:if>
        <c:if test="${!noEncontrada}">
            <f:form action="../borrar/${persona.id}" modelAttribute="persona">
                <table>
                    <tbody>
                        <tr><th><s:message code="Persona.id"/>: </th><td>${persona.id}</td></tr>
                        <tr><th><s:message code="Persona.nombre"/>: </th><td>${persona.nombre}</td></tr>
                        <tr><th><s:message code="Persona.edad"/>: </th><td>${persona.edad}</td></tr>
                        <tr><th><s:message code="Persona.genero"/>: </th><td>${persona.genero}</td></tr>
                        <tr><th><s:message code="Persona.ciudad"/>: </th><td>${persona.ciudad.nombre}</td></tr>
                    </tbody>
                </table>
                <f:button><s:message code="Boton.borrarPersona"/></f:button>
            </f:form>
            <br><a href="../editar/${persona.id}"><s:message code="Link.editarPersona"/></a>
        </c:if>
        <br><a href="../registrar"><s:message code="Link.registrarPersona"/></a>
        <br><a href="../lista"><s:message code="Link.listaPersonas"/></a>
    </body>
</html>
