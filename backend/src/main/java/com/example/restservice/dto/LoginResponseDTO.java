package com.example.restservice.dto;

import com.example.restservice.error.ErrorDTO;

public class LoginResponseDTO extends ResponseDTO{
    String token;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String message) {
        super(message);
    }

    public LoginResponseDTO(String message, String token) {
        super(message);
        this.token = token;
    }

    public LoginResponseDTO(String message, ErrorDTO errorDTO) {
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
