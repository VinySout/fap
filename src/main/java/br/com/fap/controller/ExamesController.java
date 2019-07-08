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
import br.com.fap.model.ExamesModel;
import br.com.fap.repository.ExamesRepository;
import br.com.fap.repository.PacienteRepository;

@RestController
@RequestMapping("/api")
public class ExamesController {
	
	@Autowired
	private ExamesRepository examesRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	
	// Listar todos os resultados de exames de um paciente.
	@GetMapping("/pacientes/{pacienteId}/exames")
	 public List<ExamesModel> getExamesByPacienteId(@PathVariable Long pacienteId) {		 
		 pacienteRepository.findById(pacienteId)
		 .orElseThrow(() -> new ResourceNotFoundException("Paciente", "IdPaciente", pacienteId));
		 
		 return examesRepository.findByPacienteIdPaciente(pacienteId);
	 }	
	// Listar os exames de filtrado por id do paciente e id dos exames.
	@GetMapping("/pacientes/{idPaciente}/exames/{idExames}")
	 public ExamesModel getExaByPacIdAndExaId(@PathVariable Long idPaciente, 
			 									@PathVariable Long idExames) {
		 pacienteRepository.findById(idPaciente)
		 .orElseThrow(() -> new ResourceNotFoundException("Paciente", "IdPaciente", idPaciente));
		 
		 return examesRepository.findById(idExames)
				 .orElseThrow(() -> new ResourceNotFoundException("Endereco", "idExames", idExames));
	 }
	// Adicionar resultados de exames de um paciente.
	@PostMapping("/pacientes/{pacienteId}/exames")
	public ExamesModel novosExames(@PathVariable Long pacienteId, @Valid @RequestBody ExamesModel exames) {
		return pacienteRepository.findById(pacienteId)
				.map(paciente -> {
					exames.setPaciente(paciente);
					return examesRepository.save(exames);
		}).orElseThrow(() -> new ResourceNotFoundException("Paciente", "pacienteId", pacienteId));
	}
	// Atualizar resultados de exames de um paciente.
	@PutMapping("/pacientes/{pacienteId}/exames/{examesId}")
	public ExamesModel updateExames(@PathVariable Long pacienteId, 
										@PathVariable Long examesId,
										@Valid @RequestBody ExamesModel examesDetails) {
		
		pacienteRepository.findById(pacienteId)
		.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));
		
		return examesRepository.findById(examesId)
				.map(exames -> {
					exames.setAboRh(examesDetails.getAboRh());
					exames.setGlicemia(examesDetails.getGlicemia());
					exames.setSifilis(examesDetails.getSifilis());
					exames.setVdrlHiv(examesDetails.getVdrlHiv());
					exames.setHepatite(examesDetails.getHepatite());
					exames.setToxoplasmose(examesDetails.getToxoplasmose());
					exames.setCitomegalovirus(examesDetails.getCitomegalovirus());
					exames.setUrinaEas(examesDetails.getUrinaEas());					
					
					return examesRepository.save(exames);
				}).orElseThrow(() -> new ResourceNotFoundException("Exames", "idExames", examesId));
		
	}
	// Deletar resultados de exames de um paciente.
	@DeleteMapping("/pacientes/{pacienteId}/exames/{examesId}")
	public String deleteExames(@PathVariable Long pacienteId,
								@PathVariable Long examesId) {
		pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));

		return examesRepository.findById(examesId)
				.map(exames ->{
					examesRepository.delete(exames);
					return "Exames com o id: " + examesId + " foi deletada pelo Usuário.";
				}).orElseThrow(() -> new ResourceNotFoundException("Exames", "IdExames", examesId));
	}
}
