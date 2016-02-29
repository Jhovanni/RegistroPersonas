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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${tituloRegistrarPersona}</title>
    </head>
    <body>
        <%@ include file="../encabezado.jsp" %>
        <div class="container">
            <h1>${tituloRegistrarPersona}</h1>
            <c:if test="${usuarioRegistrado}"><div class="text-success"><s:message code="PersonaRegistrada"/></div></c:if>
            <f:form id="registrarForm" action="registrar" modelAttribute="personaForm" enctype="multipart/form-data" cssClass="form-horizontal" autocomplete="off">
                <!-- Firefox: desactivar autocomplete --><input type="text" style="display:none"><input type="password" style="display:none">
                <div class="form-group" class="help-block"><f:errors path=""/></div>
                <div id="nombreUsuarioDiv" class="form-group">
                    <f:label path="nombreUsuario" cssClass="col-sm-2 control-label"><s:message code="Persona.nombreUsuario"/>:</f:label>
                        <div class="col-sm-10"><div class="input-group">
                            <f:input id="nombreUsuarioInput" path="nombreUsuario" cssClass="form-control" aria-describedby="nombreUsuarioAddon"/>
                            <span class="input-group-addon" id="nombreUsuarioAddon"></span>
                        </div>
                        <f:errors path="nombreUsuario" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group">
                    <f:label path="clave" cssClass="col-sm-2 control-label"><s:message code="Persona.clave"/>:</f:label>
                        <div class="col-sm-10">
                        <f:input path="clave" type="password" cssClass="form-control"/><f:errors path="clave" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group">
                    <f:label path="clave2" cssClass="col-sm-2 control-label"><s:message code="Persona.clave2"/>:</f:label>
                        <div class="col-sm-10">
                        <f:input path="clave2" type="password" cssClass="form-control"/><f:errors path="clave2" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group">
                    <f:label path="nombre" cssClass="col-sm-2 control-label"><s:message code="Persona.nombre"/>:</f:label>
                        <div class="col-sm-10">
                        <f:input path="nombre" cssClass="form-control"/><f:errors path="nombre" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group">
                    <f:label path="edad" cssClass="col-sm-2 control-label"><s:message code="Persona.edad"/>:</f:label>
                        <div class="col-sm-10">
                        <f:input path="edad" cssClass="form-control"/><f:errors path="edad" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group">
                    <f:label path="genero" cssClass="col-sm-2 control-label"><s:message code="Persona.genero"/>:</f:label>
                        <div class="col-sm-10">
                        <f:radiobutton path="genero" value="M" label="M"/>
                        <f:radiobutton path="genero" value="F" label="F"/>
                        <f:errors path="genero" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group">
                    <f:label path="ciudad" cssClass="col-sm-2 control-label"><s:message code="Persona.ciudad"/>:</f:label>
                        <div class="col-sm-10">
                        <f:select path="ciudad" cssClass="form-control">
                            <f:options items="${ciudades}" itemLabel="nombre" itemValue="id"/>
                        </f:select>
                        <f:errors path="ciudad" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group">
                    <f:label path="foto" cssClass="col-sm-2 control-label"><s:message code="Persona.foto"/>:</f:label>
                        <div class="col-sm-10">
                        <f:input path="foto" type="file" cssClass="help-block"/><f:errors path="foto"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <f:button class="btn btn-default"><s:message code="Boton.registrarPersona"/></f:button>
                        </div>
                    </div>
            </f:form>
            <div hidden="true">
                <span id="textoDinamicoVerificando"><s:message code="texto.dinanico.verficando"/></span>
                <span id="textoDinamicoDisponible"><s:message code="texto.dinamico.disponible"/></span>
                <span id="textoDinamicoOcupado"><s:message code="texto.dinamico.ocupado"/></span>
                <span id="textoDinamicoError"><s:message code="texto.dinamico.error"/></span>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $("#nombreUsuarioInput").keyup(function () {
                    if (this.value.length >= 3 && this.value.length <= 20) {
                        //TODO:validar integridad del campo antes de verificar disponibilidad (no puede tener caracteres especiales, ni espacios)
                        var nombreUsuario = this.value;
                        var baseUrl = "${pageContext.request.contextPath}";
                        $("#nombreUsuarioDiv").removeClass("has-warning has-error has-success");
                        $.ajax({
                            url: "${pageContext.request.contextPath}/persona/isNombreUsuarioOcupado/" + nombreUsuario,
                            beforeSend: function (xhr) {
                                $("#nombreUsuarioAddon").html($("#textoDinamicoVerificando").html());
                            },
                            success: function (data, textStatus, jqXHR) {
                                if (data.toString() === "false") {
                                    $("#nombreUsuarioAddon").html($("#textoDinamicoDisponible").html());
                                    $("#nombreUsuarioDiv").removeClass("has-warning has-error").addClass("has-success");
                                } else {
                                    $("#nombreUsuarioAddon").html($("#textoDinamicoOcupado").html());
                                    $("#nombreUsuarioDiv").removeClass("has-warning has-success").addClass("has-error");
                                }
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                $("#nombreUsuarioAddon").html($("#textoDinamicoError").html());
                                $("#nombreUsuarioDiv").removeClass("has-warning has-succes").addClass("has-warning");
                            }
                        });
                    } else {
                        $("#nombreUsuarioAddon").html("");
                        $("#nombreUsuarioDiv").removeClass("has-warning has-error has-success");
                    }
                });
            });
        </script>
    </body>
</html>
