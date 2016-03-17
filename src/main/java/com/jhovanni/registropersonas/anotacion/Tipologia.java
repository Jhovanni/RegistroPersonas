/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.anotacion;

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
 * Los caracteres de la cadena deben de contener sólo elementos del tipo
 * especificado. Ésta es una anotación de práctica, con funcionalidad de algo
 * sencillo. Claro que por ahora ha generado más trabajo que ayuda, un simple
 * regex podría haber ayudado. Más lo importante fue aprender un poco de como se
 * crean la validaciones por anotación
 * <p>
 * {@code null} se considera válido
 *
 * @author Administrator
 */
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = TipologiaValidador.class)
@Documented
public @interface Tipologia {

    String message() default "La tipología de las letras ingresadas no se encuentran dentro de las admitidas Minúsculas/Mayúsculas";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     *
     * @return tipo de letras admitidas
     */
    Tipologia.Tipo tipo();

    /**
     * Define varias anotaciones {@link Tipologia} para el mismo elemento
     *
     * @see Tipologia
     */
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        Tipologia[] value();
    }

    /**
     * Tipologías disponibles
     */
    public enum Tipo {

        MAYUSCULA, MINUSCULA

    }
}
