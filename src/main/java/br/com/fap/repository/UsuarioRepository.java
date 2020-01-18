package br.com.fap.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fap.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
   
	Optional<UsuarioModel> findByEmail(String email);

    Optional<UsuarioModel> findByUsernameOrEmail(String username, String email);

    List<UsuarioModel> findByIdIn(List<Long> userIds);

    Optional<UsuarioModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
