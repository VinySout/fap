package br.com.fap.domain.port.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fap.domain.model.PacienteModel;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long>{
	
    // Busca todos os pacientes de um usuário específico
    @Query("SELECT p FROM PacienteModel p WHERE p.usuario.id = :userId")
    List<PacienteModel> findAllByUsuarioId(Long userId);

    // Busca um paciente específico pelo ID e pelo ID do usuário
    @Query("SELECT p FROM PacienteModel p WHERE p.usuario.id = :userId AND p.idPaciente = :pacienteId")
    Optional<PacienteModel> findByUsuarioIdAndIdPaciente(Long userId, Long pacienteId);
}
