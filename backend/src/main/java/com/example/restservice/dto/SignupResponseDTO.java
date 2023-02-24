package com.example.restservice.dto;

import com.example.restservice.error.ErrorDTO;

public class SignupResponseDTO extends ResponseDTO {
    String token;

    public SignupResponseDTO() {
    }

    public SignupResponseDTO(String message) {
        super(message);
    }

    public SignupResponseDTO(String message, String token) {
        super(message);
        this.token = token;
    }

    public SignupResponseDTO(String message, ErrorDTO errorDTO) {
        super(message);
        addError(errorDTO);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
