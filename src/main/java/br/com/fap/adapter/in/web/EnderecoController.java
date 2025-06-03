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
import br.com.fap.domain.model.EnderecoModel;
import br.com.fap.domain.port.in.IEnderecoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Endereço", description = "Operações relacionadas ao endereço")
public class EnderecoController {
	
	@Autowired
	private IEnderecoUseCase enderecoUseCase;

	
    @Operation(summary = "Listar todos os enderecos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
	@GetMapping("/pacientes/{idPaciente}/endereco")
	public List<EnderecoModel> getEnderecoByPacienteId(@CurrentUser UserPrincipal currentUser,
														@PathVariable Long idPaciente) throws Exception {
		return enderecoUseCase.listarEnderecosPaciente(currentUser, idPaciente);
	}
	
    @Operation(summary = "Buscar endereço por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
	@GetMapping("/pacientes/{idPaciente}/endereco/{idEndereco}")
	 public EnderecoModel getEndByPacIdAndEndId(@PathVariable Long idPaciente, 
			 									@CurrentUser UserPrincipal currentUser,
			 									@PathVariable Long idEndereco) throws Exception {
		return enderecoUseCase.buscarEnderecoPaciente(currentUser, idPaciente, idEndereco);
	 }
	
    @Operation(summary = "Adicionar novo endereço")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
	@PostMapping("/pacientes/{pacienteId}/endereco")
	public EnderecoModel addEndereco(@CurrentUser UserPrincipal currentUser, 
									 @PathVariable Long pacienteId, @Valid @RequestBody EnderecoModel endereco) throws Exception {
		
		return enderecoUseCase.inserirEnderecoPaciente(currentUser, pacienteId, endereco);
	}
	
    @Operation(summary = "Atualizar endereço por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
	@PutMapping("/pacientes/{pacienteId}/endereco/{enderecoId}")
	public EnderecoModel updateEndereco(@PathVariable Long pacienteId, 
										@CurrentUser UserPrincipal currentUser, 
										@PathVariable Long enderecoId,
										@Valid @RequestBody EnderecoModel enderecoDetails) throws Exception {
		
		return enderecoUseCase.editarEnderecoPaciente(pacienteId, currentUser, enderecoId, enderecoDetails);
	}
	
    @Operation(summary = "Excluir endereço por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço excluído com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
	@DeleteMapping("/pacientes/{pacienteId}/endereco/{enderecoId}")
	public EnderecoModel deleteEndereco(@PathVariable Long pacienteId,
										@CurrentUser UserPrincipal currentUser, 
									    @PathVariable Long enderecoId) throws Exception {
		return enderecoUseCase.excluirEndereco(pacienteId, currentUser, enderecoId);
	}	
}
