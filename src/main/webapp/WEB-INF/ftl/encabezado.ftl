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
            <a class="navbar-brand" href="<@spring.url ''/>">
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
                <li><a href='<@spring.url "/persona/lista"/>'>
                        <span class="glyphicon glyphicon-list"></span> <@spring.message "Link.listaPersonas"/>
                        </a></li></ul>
            <@security.authorize access="isAnonymous()">
            <form action="<@spring.url ''/>/login" method="POST" class="navbar-form form-inline navbar-left">
                <div class="form-group input-group-sm">
                    <label for="usuario" class="sr-only"><@spring.message "Persona.nombreUsuario"/></label>
                    <input id="usuario" name="usuario" class="form-control" placeholder='<@spring.message "Persona.nombreUsuario"/>'/>
                    <label for="clave" class="sr-only"><@spring.message "Persona.clave"/></label>
                    <input id="clave" name="clave" type="password" class="form-control" placeholder='<@spring.message "Persona.clave"/>'/>
                    <button type="submit" class="btn btn-success btn-sm"><@spring.message "Boton.ingresar"/></button>
                    </div>
                </form>
            </@security.authorize>
            <@security.authorize access="isAuthenticated()">
            <ul class="nav navbar-nav navbar-right">
                <li><a href='<@spring.url "/persona/editar"/>'>
                        <span class="glyphicon glyphicon-user"></span> <@security.authentication property="principal.username"/>
                        </a></li>
                <li><a href='<@spring.url "/persona/registrar"/>'>
                        <span class="glyphicon glyphicon-new-window"></span> <@spring.message "Link.registrarPersona"/>
                        </a></li>
                <li><a href='<@spring.url "/logout"/>'">
                        <span class="glyphicon glyphicon-log-out"></span> <@spring.message "Boton.desconectar"/>
                        </a></li>
                </ul>
            </@security.authorize>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
    </header>