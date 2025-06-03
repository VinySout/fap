package br.com.fap.domain.port.in;

import java.net.URI;

import br.com.fap.adapter.in.web.dto.SignUpRequest;

public interface ICadastroUsuarioUseCase {
	
	URI cadastrarUsuario(SignUpRequest signUpRequest);

	boolean validarUsuarioExistente(String username);
	
	boolean validarEmailExistente(String email);

}
