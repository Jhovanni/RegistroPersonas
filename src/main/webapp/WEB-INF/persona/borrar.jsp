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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${tituloBorrarPersona}</title>
    </head>
    <body>
        <%@ include file="../encabezado.jsp" %>
        <div class="container">
            <h1>${tituloBorrarPersona}</h1>
            <c:if test="${personaBorrada}">
                <div class="text-success"><s:message code="PersonaBorrada"/></div>
            </c:if>
            <c:if test="${persona==null}" var="noEncontrada">
                <div class="text-info"><s:message code="PersonaNoEncontrada"/></div>
            </c:if>
            <c:if test="${!noEncontrada}">
                <f:form action="../borrar/${persona.id}" modelAttribute="persona" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="Persona.id"/>: </label>
                        <div class="col-sm-10">
                            <p class="form-control-static">${persona.id}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="Persona.nombre"/>: </label>
                        <div class="col-sm-10">
                            <p class="form-control-static">${persona.nombre}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="Persona.edad"/>: </label>
                        <div class="col-sm-10">
                            <p class="form-control-static">${persona.edad}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="Persona.genero"/>: </label>
                        <div class="col-sm-10">
                            <p class="form-control-static">${persona.genero}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="Persona.ciudad"/>: </label>
                        <div class="col-sm-10">
                            <p class="form-control-static">${persona.ciudad.nombre}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <a href="../editar/${persona.id}" class="btn btn-default"><s:message code="Link.editarPersona"/></a>
                            <f:button class="btn btn-default"><s:message code="Boton.borrarPersona"/></f:button>
                        </div>
                    </div>
                </f:form>
            </c:if>
        </div>
    </body>
</html>
