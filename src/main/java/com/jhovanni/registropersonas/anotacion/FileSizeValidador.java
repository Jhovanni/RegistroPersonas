/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhovanni.registropersonas.anotacion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Administrator
 */
public class FileSizeValidador implements ConstraintValidator<FileSize, MultipartFile> {

    private int max;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.getSize() <= max;
    }

}
