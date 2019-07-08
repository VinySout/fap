package br.com.fap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fap.model.ExamesModel;

@Repository
public interface ExamesRepository extends JpaRepository<ExamesModel, Long> {
	
	public List<ExamesModel> findByPacienteIdPaciente(Long pacienteId);

}
