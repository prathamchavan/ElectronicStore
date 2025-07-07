package com.electronic.store.Electronic.Store.dtos;

import lombok.*;
import org.aspectj.internal.lang.annotation.ajcDeclareEoW;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseMessage {

    private String message;
    private boolean success;
    private HttpStatus status;


}
