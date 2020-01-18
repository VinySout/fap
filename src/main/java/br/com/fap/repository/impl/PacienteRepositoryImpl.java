package br.com.fap.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.fap.config.security.CurrentUser;
import br.com.fap.config.security.UserPrincipal;
import br.com.fap.exception.ResourceNotFoundException;
import br.com.fap.model.PacienteModel;

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
