<%-- 
    Document   : lista
    Created on : 12/02/2015, 08:51:00 AM
    Author     : Jhovanni
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="s" uri="http://www.springframework.org/tags"%>
<s:message code="Titulo.listaPersonas" var="tituloListaPersonas"/>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${tituloListaPersonas}</title>
    </head>
    <body>
        <%@ include file="../encabezado.jsp" %>
        <div class="container">
            <h1>${tituloListaPersonas}</h1>
            <c:if test="${empty personas}" var="listaVacia"><s:message code="ListaVacia.persona"/></c:if>
            <div class="table-responsive"><c:if test="${!listaVacia}">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th><s:message code="Persona.nombre"/></th>
                                <th><s:message code="Persona.edad"/></th>
                                <th><s:message code="Persona.genero"/></th>
                                <th><s:message code="Persona.ciudad"/></th>
                                <th><s:message code="Persona.fotoPerfil"/></th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${personas}" var="persona">
                                <tr>
                                    <td>${persona.nombre}</td>
                                    <td>${persona.edad}</td>
                                    <td><s:message code="Persona.genero.${persona.genero}"/></td>
                                    <td>${persona.ciudad.nombre}</td>
                                    <td>
                                        <c:url value="foto/${persona.fotoPerfil.id}" var="urlFoto"/>
                                        <s:message code="Persona.fotoPerfil.alt" var="sinFoto"/>
                                        <a href="${urlFoto}"><img src="${urlFoto}" alt="${sinFoto}" class="img-thumbnail" width="50" height="50"/></a>
                                    </td>
                                    <td>
                                        <sec:authorize access="hasAuthority('Administrador')">
                                            <a href="editar/${persona.id}" class="btn btn-default"><s:message code="Link.editarPersona"/>
                                            </a>
                                            <a href="borrar/${persona.id}" class="btn btn-default"><s:message code="Link.borrarPersona"/>
                                            </a>
                                        </sec:authorize>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table></div>
                </c:if>
        </div>
        <!--Pie de página-->
        <div class="container">
            <div class="col-sm-12"><p class="text-right"> Febrero 2015 | Desarrollado por <a href="#">Jhovanni</a></p></div>
        </div>
    </body>
</html>
