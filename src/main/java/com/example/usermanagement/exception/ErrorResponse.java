package com.example.usermanagement.exception;

import com.example.usermanagement.exception.custom.ValidatorException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class ErrorResponse {
    String code;
    String message;
    List<ValidatorException> validationExceptions;
}