package br.com.fap.domain.port.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fap.domain.model.PacienteModel;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long>{
	
}
