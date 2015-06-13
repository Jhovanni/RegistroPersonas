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
            padding{}
            padding-bottom{}
            padding-top{}
        </style>
    </head>
    <body>
        <%@ include file="encabezado.jsp" %>
        <section class="container text-center">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-1">
                    <h1><strong>Jhovanni</strong></h1>
                    <h2>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-6 col-xs-offset-3 input-group">
                        <input type="text" class="form-control" placeholder="Ingresa un texto para buscar" focus state/>
                        <span class="input-group-btn">
                            <button class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                        </span>
                </div>                
            </div>
            <h5>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</h5>
        </section>
        <section class="container text-center">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-1">
                    <h1>Lorem ipsum dolor sit amet</h1>
                    <h4>Quam sunt quos minima consequatur dolore quaerat vel recusandae numquam ab qui dignissimos doloribus molestiae pariatur magnam libero nam consectetur asperiores maiores.                 </h4>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <h2><span class="glyphicon glyphicon-user" aria-hiden="true"></span></h2>
                    <h5>Lorem ipsum dolor sit</h5>
                    <small>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptates, ipsam, explicabo, fugit in magni delectus aliquid.</small>
                </div>
                <div class="col-sm-3">
                    <h2><span class="glyphicon glyphicon-picture" aria-hiden="true"></span></h2>
                    <h5>Lorem ipsum dolor sit</h5>
                    <small>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptates, ipsam, explicabo, fugit in magni delectus aliquid.</small>
                </div>
                <div class="col-sm-3">
                    <h2><span class="glyphicon glyphicon-camera" aria-hiden="true"></span></h2>
                    <h5>Lorem ipsum dolor sit</h5>
                    <small>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptates, ipsam, explicabo, fugit in magni delectus aliquid.</small>
                </div>
                <div class="col-sm-3">
                    <h2><span class="glyphicon glyphicon-pushpin" aria-hiden="true"></span></h2>
                    <h5>Lorem ipsum dolor sit</h5>
                    <small>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptates, ipsam, explicabo, fugit in magni delectus aliquid.</small>
                </div>
            </div>
        </section>
        <section class="container">
            <div class="row text-center">
                <div class="col-xs-10 col-xs-offset-1">
                    <h1>Lorem ipsum dolor sit amet</h1>
                    <h4>Quam sunt quos minima consequatur dolore quaerat vel recusandae numquam ab qui dignissimos doloribus molestiae pariatur magnam libero nam consectetur asperiores maiores.                 </h4>
                </div>
            </div>
        </section>
        <section class="container">
            <div class="row text-center">
                <div class="col-xs-10 col-xs-offset-1">
                    <h2>Lorem ipsum dolor sit amet</h2>
                    <h4>Quam sunt quos minima consequatur</h4>
                    <a class="btn btn-primary" href="#">Yes, please!</a>
                </div>
            </div>
        </section>
        <section class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-1 text-center">
                    <h1>Jhovanni</h1>
                    <h3>
                        Javi Jimenez is the CTO and co-founder of
                        <a class="text normal" href="http://tapquo.com">tapquo</a>
                        . A restless mind that always strives for continuous improvement, able to create frameworks like
                        <a class="text normal" href="http://lungo.tapquo.com">Lungo</a>
                        ,
                        <a class="text normal" href="http://quojs.tapquo.com">QuoJS</a>
                        ,
                        <a class="text normal" href="http://monocle.tapquo.com">Monocle</a>
                        used by thousands of developers around the world.
                    </h3>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-4 col-xs-offset-4">
                    <img class="img-responsive img-circle" alt="..." src="http://wallpoper.com/images/00/32/06/04/shay-laren_00320604.jpg">
                </div>
            </div>
        </section>
        <footer class="container text-center">
            <div class="row">
                <div class="col-xs-6 col-xs-offset-3">
                    <p>Hecho por <a class="" href="#">Jhovanni</a></p>
                    <small>We're a  user experiences company located in Bilbao, ES.</small>
                </div>
            </div>
        </footer>
    </body>
</html>
