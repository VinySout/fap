package br.com.fap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fap.model.PacienteModel;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long>{

}
