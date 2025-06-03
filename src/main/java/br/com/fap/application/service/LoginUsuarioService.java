package br.com.fap.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.fap.adapter.in.web.dto.JwtAuthenticationResponse;
import br.com.fap.adapter.in.web.dto.LoginRequest;
import br.com.fap.adapter.out.security.JwtTokenProvider;
import br.com.fap.domain.port.in.ILoginUsuarioUseCase;

@Service
public class LoginUsuarioService implements ILoginUsuarioUseCase {
	
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

	@Override
	public JwtAuthenticationResponse autenticarUsuario(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
	}

}
