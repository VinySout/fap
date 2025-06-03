package br.com.fap.domain.port.in;

import br.com.fap.adapter.in.web.dto.JwtAuthenticationResponse;
import br.com.fap.adapter.in.web.dto.LoginRequest;

public interface ILoginUsuarioUseCase {

	JwtAuthenticationResponse autenticarUsuario(LoginRequest loginRequest);
}
