<%-- 
    Document   : index
    Created on : Jun 12, 2015, 11:26:24 PM
    Author     : jhovanni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="s" uri="http://www.springframework.org/tags"%>
<s:message code="Titulo.index" var="tituloIndex"/>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${tituloIndex}</title>
        <link href="${pageContext.request.contextPath}/css/inicio.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <span id="top-link">
            <a href="#top" onclick="$('html,body').animate({scrollTop: 0}, 'slow');
                    return false;">
                <span class="glyphicon glyphicon-chevron-up"></span>
            </a>
        </span>
        <%@ include file="encabezado.jsp" %>
        <c:if test="${errorConexionClase != null}">
            <div class="row pull-left affix">
                <div class="col-sm-8 jumbotron alert-warning alert-dismissible has-warning" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4>Al  parecer hay problemas de conexión con la base de datos </h4>
                    Es muy probable que muchas funciones del sistema no estén disponibles
                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true" data-toggle="collapse" href="#errorConexionInfo" aria-expanded="false" aria-controls="errorConexionInfo"></span>
                    <div class="collapse" id="errorConexionInfo">
                        <h6><strong>Información para el administrador</strong></h6>
                        <div style="width: 20em;word-wrap: break-word;"><code><small><strong>${errorConexionClase}:</strong>${errorConexionMensaje}</small></code></div>
                    </div>
                </div>
            </div>
        </c:if>
        <section id="bienvenida" class="container-fluid text-center landing texto claro">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-1">
                    <h1><strong>Registro de personas</strong></h1>
                    <h2 class="margin-bottom"><span class="hidden-xs">Proyecto Web para prácticas de integración de diversas tecnologías; tales como Java EE, Spring, BootStrap y más</span></h2>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-6 col-xs-offset-3 input-group entrada-buscar">
                    <input type="text" class="form-control" placeholder="Ingresa un texto para buscar" focus state/>
                    <span class="input-group-btn">
                        <button class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                    </span>
                </div>                
            </div>
            <h5>Se buscará a las personas por nombre y ciudad</h5>
        </section>
        <section class="container-fluid text-center landing fondo claro">
            <div class="row padding-bottom texto oscuro">
                <div class="col-xs-10 col-xs-offset-1">
                    <h1 class="texto tema text-center">Tecnologías</h1>
                    <h4 class="">Colocar todas la tecnologías implementadas, 
                        dejaría está página muy larga. Por lo cual he decidido mostrar 
                        las más representativas del proyecto</h4>
                </div>
            </div>
            <div class="row padding-top texto oscuro">
                <div class="col-sm-3">
                    <h2><span class="glyphicon glyphicon-user" aria-hiden="true"></span></h2>
                    <h5>Java EE</h5>
                    <small class="hidden-xs">Plataforma de programación (parte de la Plataforma Java) para desarrollar y ejecutar software de aplicaciones en el lenguaje de programación Java.</small>
                </div>
                <div class="col-sm-3">
                    <h2><span class="glyphicon glyphicon-picture" aria-hiden="true"></span></h2>
                    <h5>Spring</h5>
                    <small class="hidden-xs">Framework de código abierto para la plataforma Java, para desarrollo de aplicaciones y contenedor de inversión de control.</small>
                </div>
                <div class="col-sm-3">
                    <h2><span class="glyphicon glyphicon-camera" aria-hiden="true"></span></h2>
                    <h5>Hibernate</h5>
                    <small class="hidden-xs">Framework de código abierto para el manejo de persistencia en la plataforma Java.</small>
                </div>
                <div class="col-sm-3">
                    <h2><span class="glyphicon glyphicon-pushpin" aria-hiden="true"></span></h2>
                    <h5>BootStrap</h5>
                    <small class="hidden-xs">Framework front-end móvil, elegante, intuitivo y potente para el desarrollo web más rápido y fácil.</small>
                </div>
            </div>
        </section>
        <section class="container-fluid landing fondo oscuro hidden-xs">
            <div class="row text-center ligero">
                <div class="col-xs-10 col-xs-offset-1">
                    <h1 class="texto tema">Notas de desarrollo</h1>
                    <h4 class="texto claro">
                        Este mensaje no se mostrará cuando se navegue a través de un dispositivo con dimensiones reducidas
                        Bien podría colocar aquí el archivo readme del proyecto, o alguna cosa similar.
                    </h4>
                </div>
            </div>
        </section>
        <section id="fotografia" class="container-fluid landing texto claro">
            <div class="row text-center">
                <div class="col-xs-8 col-xs-offset-2">
                    <h2>Cancún</h2>
                    <h4>Toma de Playa de las tortugas en Cancún, un lugar estupendo para descansar, 
                        tomar un tiempo libre, olvidar el mundo. Apuesto te gustaría estar ahí</h4>
                    <a class="btn btn-primary margin-top" href="#">¡Sí por supuesto!</a>
                </div>
            </div>
        </section>
        <section class="container-fluid landing fondo claro">
            <div class="row margin-bottom texto oscuro">
                <div class="col-xs-10 col-xs-offset-1 text-center">
                    <h1 class="texto tema">Autor</h1>
                    <h3>
                        Jhovanni Montesinos es desarrollador de aplicaciones web y móviles. 
                        Actualmente forma parte del proyecto DEMS, para uso interno de 
                        <a class="text" href="http://www.ibm.com/mx/es/">IBM</a>.
                    </h3>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-4 col-xs-offset-4">
                    <img class="img-responsive img-circle margin-bottom" alt="..." src="${pageContext.request.contextPath}/imagenes/jhovanni.png">
                </div>
            </div>
        </section>
        <footer class="container-fluid text-center small fondo oscuro">
            <div class="row padding texto claro">
                <div class="col-xs-6 col-xs-offset-3">
                    <p>2015 - Desarrollado por <a class="" href="#">Jhovanni</a>, repositorio en <a href="https://github.com/Jhovanni/RegistroPersonas">GitHub</a></p>
                    <small>Actualmente radico en Guadalajara. Uniendo fuerzas con un equipo estupendo en IBM</small>
                </div>
            </div>
        </footer>
    </body>
</html>
