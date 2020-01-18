package br.com.fap.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fap.config.security.CurrentUser;
import br.com.fap.config.security.UserPrincipal;
import br.com.fap.exception.ResourceNotFoundException;
import br.com.fap.model.ConsultaModel;
import br.com.fap.model.PacienteModel;
import br.com.fap.repository.ConsultaRepository;
import br.com.fap.repository.impl.PacienteRepositoryImpl;

@RestController
@RequestMapping("/api")
public class ConsultaController {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private PacienteRepositoryImpl pacienteRepositoryImpl;
	
	 // Listar as consultas buscando por id de um paciente.	 
	 @GetMapping("/pacientes/{idPaciente}/consultas")
	 public List<ConsultaModel> getConsultasByPacienteId(@CurrentUser UserPrincipal currentUser, 
			 											 @PathVariable Long idPaciente) throws Exception {
			pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, idPaciente);
		 
		 return consultaRepository.findByPacienteIdPaciente(idPaciente);
	 }
	// Listar consulta buscando por id de um paciente e id da consulta.
	 @GetMapping("/pacientes/{idPaciente}/consultas/{idConsulta}")
	 public ConsultaModel getConsByPacIdAndConsId(@CurrentUser UserPrincipal currentUser, 
			 									  @PathVariable Long idPaciente, 
			 									  @PathVariable Long idConsulta) throws Exception {
			pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, idPaciente);
		 
		 return consultaRepository.findById(idConsulta)
				 .orElseThrow(() -> new ResourceNotFoundException("Consulta", "idConsulta", idConsulta));
	 }
	 
	// Adicionar uma nova consulta a um paciente.
	@PostMapping("/pacientes/{pacienteId}/consultas")
	public ConsultaModel novaConsulta(@CurrentUser UserPrincipal currentUser, 
									  @PathVariable Long pacienteId, @Valid 
							  		  @RequestBody ConsultaModel consulta) throws Exception {
		PacienteModel paciente = pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);
		
		consulta.setPaciente(paciente);
		
		return consultaRepository.save(consulta);
	}


	// Atualizar uma consulta existente buscando por id.
	@PutMapping("/pacientes/{pacienteId}/consultas/{consultaId}")
	public ConsultaModel updateConsulta(@CurrentUser UserPrincipal currentUser,
										@PathVariable Long pacienteId, 
										@PathVariable Long consultaId,
										@Valid @RequestBody ConsultaModel consultaDetails) throws Exception {
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);
		
		return consultaRepository.findById(consultaId)
				.map(consulta -> {
					consulta.setIdConsulta(consultaDetails.getIdConsulta());					
					consulta.setDataConsulta(consultaDetails.getDataConsulta());
					consulta.setIdadeGestacional(consultaDetails.getIdadeGestacional());
					consulta.setPeso(consultaDetails.getPeso());						
					consulta.setEdema(consultaDetails.getEdema());
					consulta.setPresArterial(consultaDetails.getPresArterial());
					consulta.setAlturaUterina(consultaDetails.getAlturaUterina());
					consulta.setApresFetal(consultaDetails.getApresFetal());
					consulta.setBcf(consultaDetails.getBcf());
					consulta.setToque(consultaDetails.getToque());
					consulta.setObs(consultaDetails.getObs());
					
					return consultaRepository.save(consulta);
				}).orElseThrow(() -> new ResourceNotFoundException("Consulta", "idConsulta", consultaId));
		
	}

	// Deletar uma consulta buscando por id.
	@DeleteMapping("/pacientes/{pacienteId}/consultas/{consultaId}")
	public ConsultaModel deleteConsulta(@CurrentUser UserPrincipal currentUser, 
										@PathVariable Long pacienteId,
										@PathVariable Long consultaId) throws Exception {
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);

		return consultaRepository.findById(consultaId)
				.map(consulta ->{
					consultaRepository.delete(consulta);
					return consulta;
				}).orElseThrow(() -> new ResourceNotFoundException("Consulta", "IdConsulta", consultaId));
	}
}
