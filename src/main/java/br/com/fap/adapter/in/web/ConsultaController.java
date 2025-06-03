package br.com.fap.adapter.in.web;

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

import br.com.fap.adapter.out.security.CurrentUser;
import br.com.fap.adapter.out.security.UserPrincipal;
import br.com.fap.domain.model.ConsultaModel;
import br.com.fap.domain.port.in.IConsultaUseCase;

@RestController
@RequestMapping("/api")
public class ConsultaController {
	
	@Autowired
	private IConsultaUseCase consultaUseCase;
	
	 // Listar as consultas buscando por id de um paciente.	 
	 @GetMapping("/pacientes/{idPaciente}/consultas")
	 public List<ConsultaModel> getConsultasByPacienteId(@CurrentUser UserPrincipal currentUser, 
			 											 @PathVariable Long idPaciente) throws Exception {
		 
		 return consultaUseCase.listarConsultasPaciente(currentUser, idPaciente);
	 }
	// Listar consulta buscando por id de um paciente e id da consulta.
	 @GetMapping("/pacientes/{idPaciente}/consultas/{idConsulta}")
	 public ConsultaModel getConsByPacIdAndConsId(@CurrentUser UserPrincipal currentUser, 
			 									  @PathVariable Long idPaciente, 
			 									  @PathVariable Long idConsulta) throws Exception {

		 return consultaUseCase.buscarConsultaPaciente(currentUser, idPaciente, idConsulta);
	 }
	 
	// Adicionar uma nova consulta a um paciente.
	@PostMapping("/pacientes/{pacienteId}/consultas")
	public ConsultaModel novaConsulta(@CurrentUser UserPrincipal currentUser, 
									  @PathVariable Long pacienteId, @Valid 
							  		  @RequestBody ConsultaModel consulta) throws Exception {
		
		return consultaUseCase.inserirConsultaPaciente(currentUser, pacienteId, consulta);
	}


	// Atualizar uma consulta existente buscando por id.
	@PutMapping("/pacientes/{pacienteId}/consultas/{consultaId}")
	public ConsultaModel updateConsulta(@CurrentUser UserPrincipal currentUser,
										@PathVariable Long pacienteId, 
										@PathVariable Long consultaId,
										@Valid @RequestBody ConsultaModel consultaDetails) throws Exception {
		return consultaUseCase.editarConsultaPaciente(currentUser, pacienteId, consultaId, consultaDetails);
		
	}

	// Deletar uma consulta buscando por id.
	@DeleteMapping("/pacientes/{pacienteId}/consultas/{consultaId}")
	public ConsultaModel deleteConsulta(@CurrentUser UserPrincipal currentUser, 
										@PathVariable Long pacienteId,
										@PathVariable Long consultaId) throws Exception {
		return consultaUseCase.excluirConsultaPaciente(currentUser, pacienteId, consultaId);
	}
}
