package br.com.fap.domain.port.in;

import br.com.fap.adapter.in.web.dto.JwtAuthenticationDto;
import br.com.fap.adapter.in.web.dto.LoginDto;

public interface ILoginUsuarioUseCase {

	JwtAuthenticationDto autenticarUsuario(LoginDto loginRequest);
}
