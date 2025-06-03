package br.com.fap.adapter.out.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fap.domain.exception.ResourceNotFoundException;
import br.com.fap.domain.model.UsuarioModel;
import br.com.fap.domain.port.out.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) 
			throws UsernameNotFoundException {
		UsuarioModel user = usuarioRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                new UsernameNotFoundException(
                		"User not found with username or email : " + usernameOrEmail));

		return UserPrincipal.create(user);
	}
	
	@Transactional
    public UserDetails loadUserById(Long id) {
		UsuarioModel user = usuarioRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Usuario", "id", id)
        );

        return UserPrincipal.create(user);
    }

}
