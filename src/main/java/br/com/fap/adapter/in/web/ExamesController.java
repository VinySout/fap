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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Exames", description = "Operações relacionadas aos exames")
public class ExamesController {
	
	@Autowired
	private IExameUseCase exameUseCase;
	
    @Operation(summary = "Listar todos os exames")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
	@GetMapping("/pacientes/{pacienteId}/exames")
	 public List<ExamesModel> getExamesByPacienteId(@CurrentUser UserPrincipal currentUser, 
			 										@PathVariable Long pacienteId) throws Exception {	 
		return exameUseCase.listarExamesPaciente(currentUser, pacienteId);
	 }	
	
    @Operation(summary = "Buscar exames por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
	@GetMapping("/pacientes/{idPaciente}/exames/{idExames}")
	 public ExamesModel getExaByPacIdAndExaId(@CurrentUser UserPrincipal currentUser,
	 										  @PathVariable Long idPaciente, 
			 								  @PathVariable Long idExames) throws Exception {
		
		return exameUseCase.buscarExamePaciente(currentUser, idPaciente, idExames);
	 }
	
    @Operation(summary = "Adicionar novo exame")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Exame criado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
	@PostMapping("/pacientes/{pacienteId}/exames")
	public ExamesModel novosExames(@CurrentUser UserPrincipal currentUser,
								   @PathVariable Long pacienteId, @Valid 
							   	   @RequestBody ExamesModel exames) throws Exception {
		
		return exameUseCase.inserirExamePaciente(currentUser, pacienteId, exames);
	}
	
    @Operation(summary = "Atualizar exame por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exame atualizado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Exame não encontrado")
    })
	@PutMapping("/pacientes/{pacienteId}/exames/{examesId}")
	public ExamesModel updateExames(@CurrentUser UserPrincipal currentUser,
									@PathVariable Long pacienteId, 
									@PathVariable Long examesId,
									@Valid @RequestBody ExamesModel examesDetails) throws Exception {
		

		return exameUseCase.editarExamePaciente(currentUser, pacienteId, examesId, examesDetails);
	}
	
    @Operation(summary = "Excluir exame por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exame excluído com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Exame não encontrado")
    })
	@DeleteMapping("/pacientes/{pacienteId}/exames/{examesId}")
	public ExamesModel deleteExames(@CurrentUser UserPrincipal currentUser, 
									@PathVariable Long pacienteId,
									@PathVariable Long examesId) throws Exception {
		
		return exameUseCase.excluirExamePaciente(currentUser, pacienteId, examesId);
	}
}
