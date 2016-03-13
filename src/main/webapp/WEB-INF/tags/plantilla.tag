<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="estilos" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="titulo" fragment="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><jsp:invoke fragment="titulo"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <jsp:invoke fragment="estilos"/>
        <style>
            body { padding-top: 50px; }
        </style>
    </head>
    <body>
        <%@ include file="../jsp/encabezado.jsp" %>
        <jsp:doBody/>
        <%@ include file="../jsp/piePagina.jsp" %>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
        <jsp:invoke fragment="scripts"/>
    </body>
</html>