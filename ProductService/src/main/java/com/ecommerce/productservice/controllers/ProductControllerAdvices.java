package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.DTO.ExceptionDTO;
import com.ecommerce.productservice.exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvices {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFound productNotFound) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionDTO.setErrorMessage(productNotFound.getMessage());

        return new ResponseEntity<ExceptionDTO>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setHttpStatus(HttpStatus.BAD_REQUEST);
        exceptionDTO.setErrorMessage(exception.getMessage());

        return new ResponseEntity<ExceptionDTO>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
}
