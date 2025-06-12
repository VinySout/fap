package br.com.fap.domain.port.in;

import java.net.URI;

import br.com.fap.adapter.in.web.dto.SignUpDto;

public interface ICadastroUsuarioUseCase {
	
	URI cadastrarUsuario(SignUpDto signUpRequest);

	boolean validarUsuarioExistente(String username);
	
	boolean validarEmailExistente(String email);

}
