<%-- 
    Document   : editar
    Created on : 13/02/2015, 06:31:50 PM
    Author     : Jhovanni
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<s:message code="Titulo.editarPersona" var="tituloEditarPersona"/>
<!DOCTYPE html>
<html>
    <head>
        <title>${tituloEditarPersona}</title>
    </head>
    <body>
        <%@ include file="../encabezado.jsp" %>
        <h1>${tituloEditarPersona}</h1>
        <c:if test="${personaEditada}"><s:message code="PersonaEditada"/><br></c:if>
        <c:if test="${persona==null}" var="noEncontrada"><s:message code="PersonaNoEncontrada"/></c:if>
        <c:if test="${!noEncontrada}">
            <f:form action="${persona.id}" modelAttribute="persona">
                <f:errors path=""/>
                <f:label path="nombre"><s:message code="Persona.nombre"/>:</f:label>
                <f:input path="nombre"/><f:errors path="nombre"/><br>
                <f:label path="edad"><s:message code="Persona.edad"/>:</f:label>
                <f:input path="edad"/><f:errors path="edad"/><br>
                <f:label path="genero"><s:message code="Persona.genero"/>:</f:label>
                <f:radiobutton path="genero" value="M" label="M"/>
                <f:radiobutton path="genero" value="F" label="F"/>
                <f:errors path="genero"/><br>
                <f:label path="ciudad"><s:message code="Persona.ciudad"/>:</f:label>
                <f:select path="ciudad">
                    <f:options items="${ciudades}" itemLabel="nombre" itemValue="id"/>
                </f:select>
                <f:errors path="ciudad"/><br>
                <%--<f:label path="foto"><s:message code="Persona.foto"/>:</f:label>--%>
                <%--<f:input path="foto" type="file"/><f:errors path="foto"/>--%>
                <f:button><s:message code="Boton.editarPersona"/></f:button>
            </f:form>
            <br><a href="../borrar/${persona.id}"><s:message code="Link.borrarPersona"/></a>
        </c:if>
        <br><a href="../registrar"><s:message code="Link.registrarPersona"/></a>
        <br><a href="../lista"><s:message code="Link.listaPersonas"/></a>
    </body>
</html>
