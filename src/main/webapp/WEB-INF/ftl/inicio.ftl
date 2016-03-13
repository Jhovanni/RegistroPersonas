<#ftl strip_whitespace = true>
<#import "spring.ftl" as spring />
<#import "plantilla.ftl" as plantilla>

<@plantilla.renderizar "Titulo.index", ["/css/registro.css"]>
<#if errorConexionClase??>
<div class="row pull-left affix">
    <div class="col-sm-8 jumbotron alert-warning alert-dismissible has-warning" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4>Al  parecer hay problemas de conexi&#243;n con la base de datos </h4>
        Es muy probable que muchas funciones del sistema no est&#233;n disponibles
        <span class="glyphicon glyphicon-info-sign" aria-hidden="true" data-toggle="collapse" href="#errorConexionInfo" aria-expanded="false" aria-controls="errorConexionInfo"></span>
        <div class="collapse" id="errorConexionInfo">
            <h6><strong>Informaci&#243;n para el administrador</strong></h6>
            <div style="width: 20em;word-wrap: break-word;"><code><small><strong>${errorConexionClase}:</strong>${errorConexionMensaje!}</small></code></div>
            </div>
        </div>
    </div>
        </#if>
<section id="bienvenida" class="container-fluid text-center landing texto claro">
    <div class="row">
        <div class="col-xs-10 col-xs-offset-1">
            <h1><strong>Registro de personas</strong></h1>
            <h2 class="margin-bottom"><span class="hidden-xs">Proyecto Web para pr&#225;cticas de integraci&#243;n de diversas tecnolog&#237;as; tales como Java EE, Spring, BootStrap y m&#225;s</span></h2>
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
    <h5>Se buscar&#225; a las personas por nombre y ciudad</h5>
    </section>
<section class="container-fluid text-center landing fondo claro">
    <div class="row padding-bottom texto oscuro">
        <div class="col-xs-10 col-xs-offset-1">
            <h1 class="texto tema text-center">Tecnolog&#237;as</h1>
            <h4 class="">Colocar todas la tecnolog&#237;as implementadas, 
                dejar&#237;a est&#225; p&#225;gina muy larga. Por lo cual he decidido mostrar 
                las m&#225;s representativas del proyecto</h4>
            </div>
        </div>
    <div class="row padding-top texto oscuro">
        <div class="col-sm-3">
            <h2><span class="glyphicon glyphicon-user" aria-hiden="true"></span></h2>
            <h5>Java EE</h5>
            <small class="hidden-xs">Plataforma de programaci&#243;n (parte de la Plataforma Java) para desarrollar y ejecutar software de aplicaciones en el lenguaje de programaci&#243;n Java.</small>
            </div>
        <div class="col-sm-3">
            <h2><span class="glyphicon glyphicon-picture" aria-hiden="true"></span></h2>
            <h5>Spring</h5>
            <small class="hidden-xs">Framework de c&#243;digo abierto para la plataforma Java, para desarrollo de aplicaciones y contenedor de inversi&#243;n de control.</small>
            </div>
        <div class="col-sm-3">
            <h2><span class="glyphicon glyphicon-camera" aria-hiden="true"></span></h2>
            <h5>Hibernate</h5>
            <small class="hidden-xs">Framework de c&#243;digo abierto para el manejo de persistencia en la plataforma Java.</small>
            </div>
        <div class="col-sm-3">
            <h2><span class="glyphicon glyphicon-pushpin" aria-hiden="true"></span></h2>
            <h5>BootStrap</h5>
            <small class="hidden-xs">Framework front-end m&#243;vil, elegante, intuitivo y potente para el desarrollo web m&#225;s r&#225;pido y f&#225;cil.</small>
            </div>
        </div>
    </section>
<section class="container-fluid landing fondo oscuro hidden-xs">
    <div class="row text-center ligero">
        <div class="col-xs-10 col-xs-offset-1">
            <h1 class="texto tema">Notas de desarrollo</h1>
            <h4 class="texto claro">
                Este mensaje no se mostrar&#225; cuando se navegue a trav&#233;s de un dispositivo con dimensiones reducidas
                Bien podr&#237;a colocar aqu&#237; el archivo readme del proyecto, o alguna cosa similar.
                </h4>
            </div>
        </div>
    </section>
<section id="fotografia" class="container-fluid landing texto claro">
    <div class="row text-center">
        <div class="col-xs-8 col-xs-offset-2">
            <h2>Canc&#250;n</h2>
            <h4>Toma de Playa de las tortugas en Canc&#250;n, un lugar estupendo para descansar, 
                tomar un tiempo libre, olvidar el mundo. Apuesto te gustar&#237;a estar ah&#237;</h4>
            <a class="btn btn-primary margin-top" href="#">&#161;S&#237; por supuesto!</a>
            </div>
        </div>
    </section>
<section class="container-fluid landing fondo claro">
    <div class="row margin-bottom texto oscuro">
        <div class="col-xs-10 col-xs-offset-1 text-center">
            <h1 class="texto tema">Autor</h1>
            <h3>
                Jhovanni Montesinos es desarrollador de aplicaciones web y m&#243;viles. 
                Actualmente forma parte del proyecto DEMS, para uso interno de 
                <a class="text" href="http://www.ibm.com/mx/es/">IBM</a>.
                </h3>
            </div>
        </div>
    <div class="row">
        <div class="col-xs-4 col-xs-offset-4">
            <img class="img-responsive img-circle margin-bottom" alt="..." src='<@spring.url "/imagenes/jhovanni.png"/>'>
            </div>
        </div>
    </section>
</@plantilla.renderizar>