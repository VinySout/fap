package br.com.fap.adapter.in.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fap.adapter.in.web.dto.ApiResponseDto;
import br.com.fap.adapter.in.web.dto.LoginDto;
import br.com.fap.adapter.in.web.dto.SignUpDto;
import br.com.fap.domain.port.in.ICadastroUsuarioUseCase;
import br.com.fap.domain.port.in.ILoginUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação/Inscrição", description = "Operações relacionadas a autenticação no serviço")
public class AuthController {
	
	@Autowired
	private ILoginUsuarioUseCase loginUsuarioUseCase;
	
	@Autowired
	private ICadastroUsuarioUseCase cadastroUsuarioUseCase;

    @Operation(summary = "Geração do Bearer Token")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses
        .ApiResponse(responseCode = "200", description = "accessToken e tokenType com sucesso"),
        @io.swagger.v3.oas.annotations.responses
        .ApiResponse(responseCode = "401", description = "Credenciais invalidas sem autorização")
    }) 
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginRequest) {
        return ResponseEntity.ok(loginUsuarioUseCase.autenticarUsuario(loginRequest));
    }
    
    @Operation(summary = "Cadastro de usuário no sistema")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses
        .ApiResponse(responseCode = "201", description = "User registered successfully"),
        @io.swagger.v3.oas.annotations.responses
        .ApiResponse(responseCode = "400", description = "Nome para Login: '<PARAM-USERNAME>' já cadastrado em nossa base! (Escolha outro).")
    }) 
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpRequest) {
    	if (cadastroUsuarioUseCase.validarUsuarioExistente(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponseDto(false, 
            		"Nome para Login: '" + signUpRequest.getUsername() 
            		+ "' já cadastrado em nossa base! (Escolha outro)."),
                    HttpStatus.BAD_REQUEST); 
    	}
    	
        if(cadastroUsuarioUseCase.validarEmailExistente(signUpRequest.getEmail())) {
        	String emailExistente = signUpRequest.getEmail();
            return new ResponseEntity(new ApiResponseDto(false, 
            		"Email: '" + emailExistente + "' já cadastrado em base! (Escolha outro)."),
                    HttpStatus.BAD_REQUEST);
        }
        
        return ResponseEntity.created(cadastroUsuarioUseCase.cadastrarUsuario(signUpRequest))
        		.body(new ApiResponseDto(true, "User registered successfully"));
    }

}
