package br.com.fap.domain.port.out.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.fap.adapter.out.security.CurrentUser;
import br.com.fap.adapter.out.security.UserPrincipal;
import br.com.fap.domain.exception.ResourceNotFoundException;
import br.com.fap.domain.model.PacienteModel;

@Repository
public class PacienteRepositoryImpl {
	
	@PersistenceContext
	public EntityManager em;
	
	public List<PacienteModel> findAllByUserId(@CurrentUser UserPrincipal currentUser) throws Exception {
		
		try {
			List<PacienteModel> listaPacientes = em.createQuery(
	    		    "SELECT p FROM PacienteModel AS p where p.usuario = " + currentUser.getId(), PacienteModel.class)
	    		    .getResultList();
			
			return listaPacientes;
			
		} catch (Exception e) {
			throw new Exception("Não foi Possível obter essas informações");
		}
		
	}
	
	public PacienteModel findByIdUserAndPaciente(@CurrentUser UserPrincipal currentUser, Long pacienteId) throws Exception {
				
		try {
			PacienteModel queryResult = em.createQuery(
	    		    "SELECT p FROM PacienteModel AS p where p.usuario = " + currentUser.getId() 
	    		    + " and p.idPaciente = " + pacienteId, PacienteModel.class)
	    		    .getSingleResult();
			
			return queryResult;
			
		} catch (Exception e) {
			throw new ResourceNotFoundException("Paciente", "Nº", pacienteId);
		}
	}

}
