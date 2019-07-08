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

import br.com.fap.exception.ResourceNotFoundException;
import br.com.fap.model.ConsultaModel;
import br.com.fap.repository.ConsultaRepository;
import br.com.fap.repository.PacienteRepository;

@RestController
@RequestMapping("/api")
public class ConsultaController {

	@Autowired
	private ConsultaRepository consultaRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	
	 // Listar as consultas buscando por id de um paciente.	 
	 @GetMapping("/pacientes/{idPaciente}/consultas")
	 public List<ConsultaModel> getConsultasByPacienteId(@PathVariable Long idPaciente) {
		 pacienteRepository.findById(idPaciente)
		 .orElseThrow(() -> new ResourceNotFoundException("Paciente", "IdPaciente", idPaciente));
		 
		 return consultaRepository.findByPacienteIdPaciente(idPaciente);
	 }
	// Listar consulta buscando por id de um paciente e id da consulta.
	 @GetMapping("/pacientes/{idPaciente}/consultas/{idConsulta}")
	 public ConsultaModel getConsByPacIdAndConsId(@PathVariable Long idPaciente, 
			 												   @PathVariable Long idConsulta) {
		 pacienteRepository.findById(idPaciente)
		 .orElseThrow(() -> new ResourceNotFoundException("Paciente", "IdPaciente", idPaciente));
		 
		 return consultaRepository.findById(idConsulta)
				 .orElseThrow(() -> new ResourceNotFoundException("Consulta", "idConsulta", idConsulta));
	 }
	 
	// Adicionar uma nova consulta a um paciente.
	@PostMapping("/pacientes/{pacienteId}/consultas")
	public ConsultaModel novaConsulta(@PathVariable Long pacienteId, @Valid @RequestBody ConsultaModel consulta) {
		return pacienteRepository.findById(pacienteId)
				.map(paciente -> {
					consulta.setPaciente(paciente);
					return consultaRepository.save(consulta);
		}).orElseThrow(() -> new ResourceNotFoundException("Paciente", "pacienteId", pacienteId));
	}


	// Atualizar uma consulta existente buscando por id.
	@PutMapping("/pacientes/{pacienteId}/consultas/{consultaId}")
	public ConsultaModel updateConsulta(@PathVariable Long pacienteId, 
										@PathVariable Long consultaId,
										@Valid @RequestBody ConsultaModel consultaDetails) {
		
		pacienteRepository.findById(pacienteId)
		.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));
		
		return consultaRepository.findById(consultaId)
				.map(consulta -> {
					consulta.setIdConsulta(consultaDetails.getIdConsulta());
					consulta.setDataConsulta(consultaDetails.getDataConsulta());
					consulta.setQueixa(consultaDetails.getQueixa());
					consulta.setIdadeGestacional(consultaDetails.getIdadeGestacional());
					consulta.setPeso(consultaDetails.getPeso());
					consulta.setAltura(consultaDetails.getAltura());
					consulta.setPesoImc(consultaDetails.getPesoImc());
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
	public String deleteConsulta(@PathVariable Long pacienteId,
								@PathVariable Long consultaId) {
		pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));

		return consultaRepository.findById(consultaId)
				.map(consulta ->{
					consultaRepository.delete(consulta);
					return "Consulta com o id: " + consultaId + " foi deletada pelo Usuário.";
				}).orElseThrow(() -> new ResourceNotFoundException("Consulta", "IdConsulta", consultaId));
	}
}
