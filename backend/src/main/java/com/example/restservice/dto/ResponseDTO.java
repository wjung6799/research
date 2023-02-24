package com.example.restservice.dto;

import com.example.restservice.error.ErrorDTO;

import java.util.ArrayList;
import java.util.List;

public class ResponseDTO {
    String message;
    List<ErrorDTO> errors = new ArrayList<>();

    public ResponseDTO() {
    }

    public ResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addError(ErrorDTO errorDTO) {
        errors.add(errorDTO);
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }
}
