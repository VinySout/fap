package br.com.fap.adapter.in.web.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDto {
	
    @NotBlank
    private String usernameOrEmail;
    
    @NotBlank
    private String password;
}
