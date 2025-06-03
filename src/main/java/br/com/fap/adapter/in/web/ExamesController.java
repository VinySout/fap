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
import br.com.fap.domain.model.ExamesModel;
import br.com.fap.domain.port.in.IExameUseCase;

@RestController
@RequestMapping("/api")
public class ExamesController {
	
	@Autowired
	private IExameUseCase exameUseCase;
	
	// Listar todos os resultados de exames de um paciente.
	@GetMapping("/pacientes/{pacienteId}/exames")
	 public List<ExamesModel> getExamesByPacienteId(@CurrentUser UserPrincipal currentUser, 
			 										@PathVariable Long pacienteId) throws Exception {	 
		return exameUseCase.listarExamesPaciente(currentUser, pacienteId);
	 }	
	
	// Listar os exames de filtrado por id do paciente e id dos exames.
	@GetMapping("/pacientes/{idPaciente}/exames/{idExames}")
	 public ExamesModel getExaByPacIdAndExaId(@CurrentUser UserPrincipal currentUser,
	 										  @PathVariable Long idPaciente, 
			 								  @PathVariable Long idExames) throws Exception {
		
		return exameUseCase.buscarExamePaciente(currentUser, idPaciente, idExames);
	 }
	
	// Adicionar resultados de exames de um paciente.
	@PostMapping("/pacientes/{pacienteId}/exames")
	public ExamesModel novosExames(@CurrentUser UserPrincipal currentUser,
								   @PathVariable Long pacienteId, @Valid 
							   	   @RequestBody ExamesModel exames) throws Exception {
		
		return exameUseCase.inserirExamePaciente(currentUser, pacienteId, exames);
	}
	
	// Atualizar resultados de exames de um paciente.
	@PutMapping("/pacientes/{pacienteId}/exames/{examesId}")
	public ExamesModel updateExames(@CurrentUser UserPrincipal currentUser,
									@PathVariable Long pacienteId, 
									@PathVariable Long examesId,
									@Valid @RequestBody ExamesModel examesDetails) throws Exception {
		

		return exameUseCase.editarExamePaciente(currentUser, pacienteId, examesId, examesDetails);
	}
	
	// Deletar resultados de exames de um paciente.
	@DeleteMapping("/pacientes/{pacienteId}/exames/{examesId}")
	public ExamesModel deleteExames(@CurrentUser UserPrincipal currentUser, 
									@PathVariable Long pacienteId,
									@PathVariable Long examesId) throws Exception {
		
		return exameUseCase.excluirExamePaciente(currentUser, pacienteId, examesId);
	}
}
