<#--
 * plantilla.ftl
 *
 * Proporciona una plantilla para las páginas de la aplicación.
 * Automáticamente carga spring.ftl, encabezado, pie de página, jquery, 
 * bootstrap; y los renderiza de acuerdo a la plantilla:
 *      encabezado
 *      contenido (#nested)
 *      pie de pagina (#nested)
 *
 * De igual manera, carga cualquier estilo y scrips js recibidos como 
 * parámetros.
 *
 * Es importante notar que el parámetro messageTitulo, es la llave para el
 * mensaje.properties que será usado como título de página
 * 
 * @author jhovanni
 * @since 0.1
 -->
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#assign codificacion="UTF-8">

<#macro renderizar messageTitulo, estilos="", scripts="">
<#assign titulo>
<@spring.message "${messageTitulo}"/>
</#assign>
<!DOCTYPE html>
<html>
    <head>
        <title>${titulo}</title>
        <meta codificacion="${codificacion}">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='<@spring.url "/css/bootstrap.min.css"/>' rel="stylesheet" type="text/css"/>
        <#if estilos?has_content><#list estilos as estilo>
        <link href='<@spring.url "${estilo}"/>' rel="stylesheet" type="text/css"/>
        </#list></#if>
        </head>
    <body>
    <#include "encabezado.ftl"/>
    <#nested>
    <#include "piePagina.ftl"/>
        <style>
            body { padding-top: 50px; }
            </style>
        <script src='<@spring.url "/js/jquery.min.js"/>' type="text/javascript"></script>
        <script src='<@spring.url "/js/bootstrap.min.js"/>' type="text/javascript"></script>
        <#if scripts?has_content><#list scripts as script>
        <script src='<@spring.url "${script}"/>' type="text/javascript"></script>
        </#list></#if>
        </body>
    </html>
</#macro>