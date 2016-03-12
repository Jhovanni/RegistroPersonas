/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersona.validacion;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Valida el tipo de un archivo en base a los permitidos por {@link Tipo}
 * @author Administrator
 */
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = TipoArchivoValidador.class)
@Documented
public @interface TipoArchivo {

    String message() default "Tipo de archivo no aceptado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    TipoArchivo.Tipo tipo();

    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        TipoArchivo[] value();
    }

    /**
     * Tipos de archivos disponibles
     */
    public enum Tipo {

        IMAGEN("image\\/.*");
        public String regex;

        private Tipo(String regex) {
            this.regex = regex;
        }
    }
}
