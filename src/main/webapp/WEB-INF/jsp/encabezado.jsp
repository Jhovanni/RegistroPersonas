<%-- 
    Document   : encabezado
    Created on : 19/02/2015, 09:25:22 PM
    Author     : Jhovanni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<style>
    body { padding-top: 50px; }
</style>
<header class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="sr-only">&nbsp;</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">
                <span class="glyphicon glyphicon-home"></span>
            </a>            
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-right" id="myNavbar">
            <ul class="nav navbar-nav">
                <!-- Hidden li included to remove active class when scrolled up -->
                <li class="hidden"><a href="#page-top"></a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/persona/lista">
                        <span class="glyphicon glyphicon-list"></span> <s:message code="Link.listaPersonas"/>
                    </a></li></ul>
            <sec:authorize access="isAnonymous()">
                <form action="${pageContext.request.contextPath}/login" method="POST" class="navbar-form form-inline navbar-left">
                    <div class="form-group input-group-sm">
                        <label for="usuario" class="sr-only"><s:message code="Persona.nombreUsuario" var="nombreUsuarioLabel"/></label>
                        <input id="usuario" name="usuario" class="form-control" placeholder="${nombreUsuarioLabel}"/>
                        <label for="clave" class="sr-only"><s:message code="Persona.clave" var="claveLabel"/></label>
                        <input id="clave" name="clave" type="password" class="form-control" placeholder="${claveLabel}"/>
                        <button type="submit" class="btn btn-success btn-sm"><s:message code="Boton.ingresar"/></button>
                    </div>
                </form>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/persona/editar">
                            <span class="glyphicon glyphicon-user"></span> <sec:authentication property="principal.username"/>
                        </a></li>
                    <li><a href="${pageContext.request.contextPath}/persona/registrar">
                            <span class="glyphicon glyphicon-new-window"></span> <s:message code="Link.registrarPersona"/>
                        </a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">
                            <span class="glyphicon glyphicon-log-out"></span> <s:message code="Boton.desconectar"/>
                        </a></li>
                </ul>
            </sec:authorize>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</header>