<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="estilos" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="titulo" fragment="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><jsp:invoke fragment="titulo"/></title>
        <link rel="icon" href="${pageContext.request.contextPath}/imagenes/personas.png" type="image/png" sizes="32x22">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/registro.css" rel="stylesheet" type="text/css"/>
        <jsp:invoke fragment="estilos"/>
    </head>
    <body>
        <%@ include file="../jsp/encabezado.jsp" %>
        <jsp:doBody/>
        <%@ include file="../jsp/piePagina.jsp" %>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
        <jsp:invoke fragment="scripts"/>
        <script>
            //ajustar el margin inferior para dar espacio al pie de página, al momento de redimensionar la ventana
            $(window).resize(function () {
                var altura = $("#pie-pagina").height();
                $("body").css("margin-bottom", altura);
            });
        </script>
    </body>
</html>