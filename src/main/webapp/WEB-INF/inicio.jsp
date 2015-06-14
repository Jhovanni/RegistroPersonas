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
        <style>
            .padding{padding: 1em !important;}
            .padding-bottom{padding-bottom: 1em !important;}
            .padding-top{padding-top: 1em !important;}
            .margin-top{margin-top: 1em !important;}
            .margin-bottom{margin-bottom: 1em !important;}
            body > section.landing{padding: 6em;}
            boy > section.landing .entrada-buscar{margin-top: 1em;}
            .ligero{font-size: .7rem}
            .fondo.claro{background-color: #fff;}
            .fondo.oscuro{background-color: #222;}
            .texto.claro{color: #fff;}
            .texto.oscuro{color: #59636b;}
            .texto.tema{color: #31b0d5}
            #bienvenida{
                background-image: url("${pageContext.request.contextPath}/imagenes/chica_mirando.jpg");
                background-repeat: no-repeat;
                background-position: center center;
                background-size: cover;
                background-attachment: fixed;
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                padding-top: 20em;
                padding-bottom: 24em;
            }
            #fotografia{
                background-image: url("${pageContext.request.contextPath}/imagenes/playa_cancun.JPG");
                background-repeat: no-repeat;
                background-position: center center;
                background-size: cover;
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                padding-top: 22em;
                padding-bottom: 30em;
            }
            #top-link{position: fixed;bottom: 1em;right: 1em;min-height: 1em;padding: 1em;margin-bottom: 1em;border-radius: .4em;}            
            #top-link:hover{border:1px solid #e3e3e3;border-radius:4px;-webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.05);}
            ::-webkit-input-placeholder {text-align: center}
            :-moz-placeholder {text-align: center;}/* Firefox 18- */
            ::-moz-placeholder {text-align: center;}/* Firefox 19+ */
            :-ms-input-placeholder {text-align: center;}
        </style>
    </head>
    <body>
        <span id="top-link">
            <a href="#top" onclick="$('html,body').animate({scrollTop:0},'slow');return false;">
                <span class="glyphicon glyphicon-chevron-up"></span>
            </a>
        </span>
        <%@ include file="encabezado.jsp" %>
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
