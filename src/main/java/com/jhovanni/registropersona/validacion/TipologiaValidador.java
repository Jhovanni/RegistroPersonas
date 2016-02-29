/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersona.validacion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Valida que los caracteres en una cadena se encuentren en minúsculas o
 * mayúsculas.
 *
 * @author Administrator
 */
public class TipologiaValidador implements ConstraintValidator<Tipologia, String> {

    private Tipologia.Tipo letra;

    @Override
    public void initialize(Tipologia constraintAnnotation) {
        this.letra = constraintAnnotation.tipo();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        } else if (letra == Tipologia.Tipo.MAYUSCULA) {
            return value.equals(value.toUpperCase());
        } else {
            return value.equals(value.toLowerCase());
        }
    }
}
