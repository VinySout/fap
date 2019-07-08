package br.com.fap.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fap.model.EnderecoModel;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long>{
	public List<EnderecoModel> findByPacienteIdPaciente(Long pacienteId);
}
