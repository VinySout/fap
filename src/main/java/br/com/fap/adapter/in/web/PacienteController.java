package br.com.fap.adapter.in.web;

import java.util.List;

import jakarta.validation.Valid;

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

@RestController
@RequestMapping("/api")
public class PacienteController {
	
	@Autowired
	private IPacienteUseCase pacienteUseCase;
	
	// Listar todos os pacientes.
	@GetMapping("/pacientes")
	public List<PacienteModel> getAllPacientes(@CurrentUser UserPrincipal currentUser) throws Exception {
		
		return pacienteUseCase.listarPacientes(currentUser);
	}	
	
	// Buscar paciente por id.
	@GetMapping("/pacientes/{idPaciente}")
	public PacienteModel getPacienteById(@CurrentUser UserPrincipal currentUser,
										 @PathVariable(value = "idPaciente") Long pacienteId) throws Exception {
		
		return pacienteUseCase.buscarPaciente(currentUser, pacienteId);
	}
	
	// Adicionar um novo paciente.
	@PostMapping("/pacientes")
	public PacienteModel novoPaciente(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody PacienteModel paciente) {
		
		return pacienteUseCase.inserirPaciente(currentUser, paciente);
	}	
	
	// Atualizar um paciente buscando por id.
	@PutMapping("/pacientes/{idPaciente}")
	public PacienteModel updatePaciente(@PathVariable(value = "idPaciente") Long pacienteId,
				@Valid @RequestBody PacienteModel pacienteDetails) {
		
		return pacienteUseCase.editarPaciente(pacienteId, pacienteDetails);
	}
	
	// Deletar um paciente buscando por id.
	@DeleteMapping("/pacientes/{idPaciente}")
	public PacienteModel deletePaciente(@PathVariable(value = "idPaciente") Long pacienteId) {

		return pacienteUseCase.excluirPaciente(pacienteId);
	}

}
