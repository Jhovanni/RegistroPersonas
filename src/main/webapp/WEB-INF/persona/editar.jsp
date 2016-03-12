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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${tituloEditarPersona}</title>
    </head>
    <body>
        <%@ include file="../encabezado.jsp" %>
        <div class="container">
            <h1>${tituloEditarPersona}</h1>
            <c:if test="${personaEditada}">
                <div class="text-success"><s:message code="PersonaEditada"/></div>
            </c:if>
            <c:if test="${persona==null}" var="noEncontrada">
                <div class="text-info"><s:message code="PersonaNoEncontrada"/></div>
            </c:if>
            <c:if test="${!noEncontrada}">
                <f:form action="${persona.id}" modelAttribute="personaForm" class="form-horizontal" enctype="multipart/form-data">
                    <div class="form-group"><f:errors path="" cssClass="has-error"/></div>
                    <div class="form-group">
                        <f:label path="nombre" class="col-sm-2 control-label"><s:message code="Persona.nombre"/>:</f:label>
                        <div class="col-sm-10"><f:input path="nombre" class="form-control"/><f:errors path="nombre" cssClass="help-block"/></div>
                    </div>
                    <div class="form-group">
                        <f:label path="edad" class="col-sm-2 control-label"><s:message code="Persona.edad"/>:</f:label>
                        <div class="col-sm-10"><f:input path="edad" class="form-control"/><f:errors path="edad" cssClass="help-block"/></div>
                    </div>                
                    <div class="form-group">
                        <f:label path="ciudad" class="col-sm-2 control-label"><s:message code="Persona.ciudad"/>:</f:label>
                            <div class="col-sm-10">
                            <f:select path="ciudad" class="form-control">
                                <f:options items="${ciudades}" itemLabel="nombre" itemValue="id"/>
                            </f:select>
                            <f:errors path="ciudad" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <f:label path="genero" class="col-sm-2 control-label"><s:message code="Persona.genero"/>:</f:label>
                            <div class="col-sm-10">
                            <f:radiobutton path="genero" value="M" label="M"/>
                            <f:radiobutton path="genero" value="F" label="F"/>
                            <f:errors path="genero" cssClass="help-block"/>
                            </div>
                    </div>
                    <div class="form-group">
                        <f:label path="foto" cssClass="col-sm-2 control-label"><s:message code="Persona.fotoPerfil"/>:</f:label>
                            <div class="col-sm-10">
                            <f:input path="foto" type="file" cssClass="help-block"/><f:errors path="foto"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <f:button class="btn btn-default"><s:message code="Boton.editarPersona"/></f:button>
                            <a href="../borrar/${persona.id}" class="btn btn-default"><s:message code="Link.borrarPersona"/></a>
                        </div>
                    </div>
                </f:form>
                <br>
            </c:if>
        </div>
    </body>
</html>
