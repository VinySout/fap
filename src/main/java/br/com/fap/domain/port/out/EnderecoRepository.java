package br.com.fap.domain.port.out;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fap.domain.model.EnderecoModel;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long>{
	public List<EnderecoModel> findByPacienteIdPaciente(Long pacienteId);
}
