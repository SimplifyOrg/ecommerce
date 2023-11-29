package com.ecommerce.productservice.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ExceptionDTO {
    HttpStatus httpStatus;
    String errorMessage;
}
