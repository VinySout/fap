package br.com.fap.adapter.in.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fap.adapter.in.web.dto.ApiResponse;
import br.com.fap.adapter.in.web.dto.LoginRequest;
import br.com.fap.adapter.in.web.dto.SignUpRequest;
import br.com.fap.domain.port.in.ICadastroUsuarioUseCase;
import br.com.fap.domain.port.in.ILoginUsuarioUseCase;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private ILoginUsuarioUseCase loginUsuarioUseCase;
	
	@Autowired
	private ICadastroUsuarioUseCase cadastroUsuarioUseCase;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginUsuarioUseCase.autenticarUsuario(loginRequest));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    	if (cadastroUsuarioUseCase.validarUsuarioExistente(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, 
            		"Nome para Login: '" + signUpRequest.getUsername() 
            		+ "' já cadastrado em nossa base! (Escolha outro)."),
                    HttpStatus.BAD_REQUEST); 
    	}
    	
        if(cadastroUsuarioUseCase.validarEmailExistente(signUpRequest.getEmail())) {
        	String emailExistente = signUpRequest.getEmail();
            return new ResponseEntity(new ApiResponse(false, 
            		"Email: '" + emailExistente + "' já cadastrado em base! (Escolha outro)."),
                    HttpStatus.BAD_REQUEST);
        }
        
        return ResponseEntity.created(cadastroUsuarioUseCase.cadastrarUsuario(signUpRequest))
        		.body(new ApiResponse(true, "User registered successfully"));
    }

}
