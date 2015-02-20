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
        <title>${tituloListaPersonas}</title>
    </head>
    <body>
        <%@ include file="../encabezado.jsp" %>
        <h1>${tituloListaPersonas}</h1>        
        <c:if test="${empty personas}" var="listaVacia"><s:message code="ListaVacia.persona"/></c:if>
        <c:if test="${!listaVacia}">
            <table>
                <thead>
                    <tr>
                        <th><s:message code="Persona.id"/></th>
                        <th><s:message code="Persona.nombre"/></th>
                        <th><s:message code="Persona.edad"/></th>
                        <th><s:message code="Persona.genero"/></th>
                        <th><s:message code="Persona.ciudad"/></th>
                        <th><s:message code="Persona.foto"/></th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${personas}" var="persona">
                        <tr>
                            <td>${persona.id}</td>
                            <td>${persona.nombre}</td>
                            <td>${persona.edad}</td>
                            <td>${persona.genero}</td>
                            <td>${persona.ciudad.nombre}</td>
                            <td>
                                <c:url value="${persona.id}/foto" var="urlFoto"/>
                                <s:message code="Persona.foto.alt" var="sinFoto"/>
                                <a href="${urlFoto}"><img src="${urlFoto}" alt="${sinFoto}" width="50" height="50"/></a>
                            </td>
                            <td>
                                <sec:authorize access="hasAuthority('admin')">
                                    <a href="editar/${persona.id}"><s:message code="Link.editarPersona"/></a>
                                    <a href="borrar/${persona.id}"><s:message code="Link.borrarPersona"/></a>
                                </sec:authorize>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <br><a href="registrar"><s:message code="Link.registrarPersona"/></a>
    </body>
</html>
