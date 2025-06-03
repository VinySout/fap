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
import br.com.fap.domain.model.PacienteModel;
import br.com.fap.domain.port.in.IPacienteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Pacientes", description = "Operações relacionadas aos pacientes")
public class PacienteController {
	
	@Autowired
	private IPacienteUseCase pacienteUseCase;
	
    @Operation(summary = "Listar todos os pacientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
	@GetMapping("/pacientes")
	public List<PacienteModel> getAllPacientes(@CurrentUser UserPrincipal currentUser) throws Exception {
		
		return pacienteUseCase.listarPacientes(currentUser);
	}	
	
    @Operation(summary = "Buscar paciente por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
	@GetMapping("/pacientes/{idPaciente}")
	public PacienteModel getPacienteById(@CurrentUser UserPrincipal currentUser,
										 @PathVariable(value = "idPaciente") Long pacienteId) throws Exception {
		
		return pacienteUseCase.buscarPaciente(currentUser, pacienteId);
	}
	
    @Operation(summary = "Adicionar novo paciente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
	@PostMapping("/pacientes")
	public PacienteModel novoPaciente(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody PacienteModel paciente) {
		
		return pacienteUseCase.inserirPaciente(currentUser, paciente);
	}	
	
    @Operation(summary = "Atualizar paciente por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
	@PutMapping("/pacientes/{idPaciente}")
	public PacienteModel updatePaciente(@PathVariable(value = "idPaciente") Long pacienteId,
				@Valid @RequestBody PacienteModel pacienteDetails) {
		
		return pacienteUseCase.editarPaciente(pacienteId, pacienteDetails);
	}
	
    @Operation(summary = "Excluir paciente por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente excluído com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
	@DeleteMapping("/pacientes/{idPaciente}")
	public PacienteModel deletePaciente(@PathVariable(value = "idPaciente") Long pacienteId) {

		return pacienteUseCase.excluirPaciente(pacienteId);
	}

}
