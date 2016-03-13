<%-- 
    Document   : lista
    Created on : 12/02/2015, 08:51:00 AM
    Author     : Jhovanni
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="s" uri="http://www.springframework.org/tags"%>
<s:message code="Titulo.listaPersonas" var="tituloListaPersonas"/>
<t:plantilla>
    <jsp:attribute name="titulo">
        ${tituloListPersonas}
    </jsp:attribute>
    <jsp:body>
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
                                        <c:choose>
                                            <c:when test="${persona.fotoPerfil.id == null}"><c:choose>
                                                    <c:when test="${persona.genero eq 'M'}"><c:url value="/imagenes/perfil-chico.png" var="urlFoto"/></c:when>
                                                    <c:otherwise><c:url value="/imagenes/perfil-chica.png" var="urlFoto"/></c:otherwise>
                                                </c:choose></c:when>
                                            <c:otherwise><c:url value="foto/${persona.fotoPerfil.id}" var="urlFoto"/></c:otherwise>
                                        </c:choose>
                                        <a href="${urlFoto}" target="_blank">
                                            <img src="${urlFoto}" alt="<s:message code="Persona.fotoPerfil.alt"/>" class="img-thumbnail" width="50" height="50"/>
                                        </a>
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
    </jsp:body>
</t:plantilla>