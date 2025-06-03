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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Consultas", description = "Operações relacionadas as consultas")
public class ConsultaController {
	
	@Autowired
	private IConsultaUseCase consultaUseCase;
	
    @Operation(summary = "Listar todos as consultas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    }) 
	@GetMapping("/pacientes/{idPaciente}/consultas")
	public List<ConsultaModel> getConsultasByPacienteId(@CurrentUser UserPrincipal currentUser, 
			 											 @PathVariable Long idPaciente) throws Exception {
		 
		 return consultaUseCase.listarConsultasPaciente(currentUser, idPaciente);
	}
	
    @Operation(summary = "Buscar consulta por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Consulta não encontrado")
    })
	@GetMapping("/pacientes/{idPaciente}/consultas/{idConsulta}")
	public ConsultaModel getConsByPacIdAndConsId(@CurrentUser UserPrincipal currentUser, 
			 									  @PathVariable Long idPaciente, 
			 									  @PathVariable Long idConsulta) throws Exception {

		return consultaUseCase.buscarConsultaPaciente(currentUser, idPaciente, idConsulta);
	}
	 
    @Operation(summary = "Adicionar nova consulta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Consulta criado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
	@PostMapping("/pacientes/{pacienteId}/consultas")
	public ConsultaModel novaConsulta(@CurrentUser UserPrincipal currentUser, 
									  @PathVariable Long pacienteId, @Valid 
							  		  @RequestBody ConsultaModel consulta) throws Exception {
		
		return consultaUseCase.inserirConsultaPaciente(currentUser, pacienteId, consulta);
	}


    @Operation(summary = "Atualizar consulta por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta atualizado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Consulta não encontrado")
    })
	@PutMapping("/pacientes/{pacienteId}/consultas/{consultaId}")
	public ConsultaModel updateConsulta(@CurrentUser UserPrincipal currentUser,
										@PathVariable Long pacienteId, 
										@PathVariable Long consultaId,
										@Valid @RequestBody ConsultaModel consultaDetails) throws Exception {
		return consultaUseCase.editarConsultaPaciente(currentUser, pacienteId, consultaId, consultaDetails);
		
	}

    @Operation(summary = "Excluir consulta por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta excluído com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Consulta não encontrado")
    })
	@DeleteMapping("/pacientes/{pacienteId}/consultas/{consultaId}")
	public ConsultaModel deleteConsulta(@CurrentUser UserPrincipal currentUser, 
										@PathVariable Long pacienteId,
										@PathVariable Long consultaId) throws Exception {
		return consultaUseCase.excluirConsultaPaciente(currentUser, pacienteId, consultaId);
	}
}
