var registro = (function () {
    var nombreUsuario = null;
    init = function (contextPath) {
        $("#nombreUsuarioInput").keyup(function () {
            if (this.value === nombreUsuario) {
                return;
            }
            if (this.value.length >= 3 && this.value.length <= 20) {
                //TODO:validar integridad del campo antes de verificar disponibilidad (no puede tener caracteres especiales, ni espacios)
                nombreUsuario = this.value;
                $("#nombreUsuarioDiv").removeClass("has-warning has-error has-success");
                $.ajax({
//                    url: "${pageContext.request.contextPath}/persona/isNombreUsuarioOcupado/" + nombreUsuario,
                    url: contextPath + "/persona/isNombreUsuarioOcupado/" + nombreUsuario,
                    beforeSend: function (xhr) {
                        $("#nombreUsuarioAddon").html($("#textoDinamicoVerificando").html());
                    },
                    success: function (data, textStatus, jqXHR) {
                        if (data.toString() === "false") {
                            $("#nombreUsuarioAddon").html($("#textoDinamicoDisponible").html());
                            $("#nombreUsuarioDiv").removeClass("has-warning has-error").addClass("has-success");
                        } else {
                            $("#nombreUsuarioAddon").html($("#textoDinamicoOcupado").html());
                            $("#nombreUsuarioDiv").removeClass("has-warning has-success").addClass("has-error");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        $("#nombreUsuarioAddon").html($("#textoDinamicoError").html());
                        $("#nombreUsuarioDiv").removeClass("has-warning has-succes").addClass("has-warning");
                    }
                });
            } else {
                $("#nombreUsuarioAddon").html("");
                $("#nombreUsuarioDiv").removeClass("has-warning has-error has-success");
            }
        });
    };
    return {
        init: init
    };
})();
