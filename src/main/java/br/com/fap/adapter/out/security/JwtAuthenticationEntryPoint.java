package br.com.fap.adapter.out.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
        logger.error("Responding with unauthorized error. Message - {}", authException.getMessage());
        String msgErro = authException.getMessage();
        if(msgErro == "Bad credentials") {
        	msgErro = "Credenciais invalidas";
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msgErro);
        } else {
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
        		
	}

}
