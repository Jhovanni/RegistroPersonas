<%-- 
    Document   : registrar
    Created on : 12/02/2015, 09:23:56 AM
    Author     : Jhovanni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<s:message code="Titulo.registrarPersona" var="tituloRegistrarPersona"/>
<!DOCTYPE html>
<html>
    <head>
        <title>${tituloRegistrarPersona}</title>
    </head>
    <body>
        <h1>${tituloRegistrarPersona}</h1>
        <c:if test="${usuarioRegistrado}"><s:message code="PersonaRegistrada"/></c:if>
        <c:if test="${!usuarioRegistrado}">
            <f:form action="registrar" modelAttribute="personaForm" enctype="multipart/form-data">
                <f:errors path=""/>
                <f:label path="nombreUsuario"><s:message code="Persona.nombreUsuario"/>:</f:label>
                <f:input path="nombreUsuario"/><f:errors path="nombreUsuario"/><br>
                <f:label path="clave"><s:message code="Persona.clave"/>:</f:label>
                <f:input path="clave" type="password"/><f:errors path="clave"/><br>
                <f:label path="clave2"><s:message code="Persona.clave2"/>:</f:label>
                <f:input path="clave2" type="password"/><f:errors path="clave2"/><br>
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
                <f:button>Registrar</f:button>
            </f:form></c:if>
        <br><a href="lista"><s:message code="Link.listaPersonas"/></a>
    </body>
</html>
