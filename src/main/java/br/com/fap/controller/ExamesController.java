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
import br.com.fap.model.ExamesModel;
import br.com.fap.model.PacienteModel;
import br.com.fap.repository.ExamesRepository;
import br.com.fap.repository.impl.PacienteRepositoryImpl;

@RestController
@RequestMapping("/api")
public class ExamesController {
	
	@Autowired
	private ExamesRepository examesRepository;
	
	@Autowired
	private PacienteRepositoryImpl pacienteRepositoryImpl;
	
	// Listar todos os resultados de exames de um paciente.
	@GetMapping("/pacientes/{pacienteId}/exames")
	 public List<ExamesModel> getExamesByPacienteId(@CurrentUser UserPrincipal currentUser, 
			 										@PathVariable Long pacienteId) throws Exception {	 
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);
		 
		 return examesRepository.findByPacienteIdPaciente(pacienteId);
	 }	
	// Listar os exames de filtrado por id do paciente e id dos exames.
	@GetMapping("/pacientes/{idPaciente}/exames/{idExames}")
	 public ExamesModel getExaByPacIdAndExaId(@CurrentUser UserPrincipal currentUser,
	 										  @PathVariable Long idPaciente, 
			 								  @PathVariable Long idExames) throws Exception {
		
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, idPaciente);
		 
		 return examesRepository.findById(idExames)
				 .orElseThrow(() -> new ResourceNotFoundException("Endereco", "idExames", idExames));
	 }
	// Adicionar resultados de exames de um paciente.
	@PostMapping("/pacientes/{pacienteId}/exames")
	public ExamesModel novosExames(@CurrentUser UserPrincipal currentUser,
								   @PathVariable Long pacienteId, @Valid 
							   	   @RequestBody ExamesModel exames) throws Exception {
		
		PacienteModel paciente = pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);
		
		exames.setPaciente(paciente);
		return examesRepository.save(exames);
	}
	// Atualizar resultados de exames de um paciente.
	@PutMapping("/pacientes/{pacienteId}/exames/{examesId}")
	public ExamesModel updateExames(@CurrentUser UserPrincipal currentUser,
									@PathVariable Long pacienteId, 
									@PathVariable Long examesId,
									@Valid @RequestBody ExamesModel examesDetails) throws Exception {
		
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);
		
		return examesRepository.findById(examesId)
				.map(exames -> {
					exames.setAboRh(examesDetails.getAboRh());
					exames.setDtAboRh(examesDetails.getDtAboRh());
					exames.setHemoglobina(examesDetails.getHemoglobina());
					exames.setDtHemoglobina(examesDetails.getDtHemoglobina());
					exames.setVdrlHiv(examesDetails.getVdrlHiv());
					exames.setDtVdrlHiv(examesDetails.getDtVdrlHiv());
					exames.setUrinaEas(examesDetails.getUrinaEas());
					exames.setDtUrinaEas(examesDetails.getDtUrinaEas());
					exames.setCitOncotica(examesDetails.getCitOncotica());
					exames.setDtCitOncotica(examesDetails.getDtCitOncotica());					
					exames.setGlicemia(examesDetails.getGlicemia());
					exames.setDtGlicemia(examesDetails.getDtGlicemia());					
					exames.setSifilis(examesDetails.getSifilis());
					exames.setDtSifilis(examesDetails.getDtSifilis());					
					exames.setHepatite(examesDetails.getHepatite());
					exames.setDtHepatite(examesDetails.getDtHepatite());					
					exames.setToxoplasmose(examesDetails.getToxoplasmose());					
					exames.setDtToxopla(examesDetails.getDtToxopla());					
					exames.setCitomegalovirus(examesDetails.getCitomegalovirus());
					exames.setDtCitomega(examesDetails.getDtCitomega());
					
					
					return examesRepository.save(exames);
				}).orElseThrow(() -> new ResourceNotFoundException("Exames", "idExames", examesId));
		
	}
	// Deletar resultados de exames de um paciente.
	@DeleteMapping("/pacientes/{pacienteId}/exames/{examesId}")
	public ExamesModel deleteExames(@CurrentUser UserPrincipal currentUser, 
									@PathVariable Long pacienteId,
									@PathVariable Long examesId) throws Exception {
		
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);

		return examesRepository.findById(examesId)
				.map(exames ->{
					examesRepository.delete(exames);
					return exames;
				}).orElseThrow(() -> new ResourceNotFoundException("Exames", "IdExames", examesId));
	}
}
