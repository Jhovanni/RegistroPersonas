/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersona.validacion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Administrator
 */
public class TipoArchivoValidador implements ConstraintValidator<TipoArchivo, MultipartFile> {

    private TipoArchivo.Tipo tipo;

    @Override
    public void initialize(TipoArchivo constraintAnnotation) {
        this.tipo = constraintAnnotation.tipo();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.getContentType().matches(tipo.regex);
    }

}
