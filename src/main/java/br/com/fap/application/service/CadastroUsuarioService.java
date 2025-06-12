package br.com.fap.application.service;

import java.net.URI;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fap.adapter.in.web.dto.SignUpDto;
import br.com.fap.domain.exception.AppException;
import br.com.fap.domain.model.Role;
import br.com.fap.domain.model.RoleName;
import br.com.fap.domain.model.UsuarioModel;
import br.com.fap.domain.port.in.ICadastroUsuarioUseCase;
import br.com.fap.domain.port.out.RoleRepository;
import br.com.fap.domain.port.out.UsuarioRepository;

@Service
public class CadastroUsuarioService implements ICadastroUsuarioUseCase {
    
    @Autowired
    private UsuarioRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public URI cadastrarUsuario(SignUpDto signUpRequest) {
    	
        // Creating user's account
        UsuarioModel user = new UsuarioModel(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        UsuarioModel result = userRepository.save(user);

        return ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
	}
	
	@Override
	public boolean validarUsuarioExistente(String username) {
		return userRepository.existsByUsername(username);
	}
	
	@Override
	public boolean validarEmailExistente(String email) {
		return userRepository.existsByEmail(email);
	}

}
