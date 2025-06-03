package br.com.fap.domain.port.out;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fap.domain.model.ConsultaModel;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long>{
	public List<ConsultaModel> findByPacienteIdPaciente(Long idPaciente);
}
