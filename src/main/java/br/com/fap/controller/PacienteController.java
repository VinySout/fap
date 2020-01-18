package br.com.fap.controller;

import java.util.List;
import java.util.Optional;

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
import br.com.fap.model.PacienteModel;
import br.com.fap.model.UsuarioModel;
import br.com.fap.repository.PacienteRepository;
import br.com.fap.repository.UsuarioRepository;
import br.com.fap.repository.impl.PacienteRepositoryImpl;

@RestController
@RequestMapping("/api")
public class PacienteController {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PacienteRepositoryImpl pacienteRepositoryImpl;
	
	// Listar todos os pacientes.
	@GetMapping("/pacientes")
	public List<PacienteModel> getAllPacientes(@CurrentUser UserPrincipal currentUser) throws Exception{
		
		return pacienteRepositoryImpl.findAllByUserId(currentUser);
	}	
	
	// Adicionar um novo paciente.
	@PostMapping("/pacientes")
	public PacienteModel novoPaciente(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody PacienteModel paciente) {
		
		Optional<UsuarioModel> usuario =  usuarioRepository.findById(currentUser.getId());
		paciente.setUsuario(usuario.get());
		
		return pacienteRepository.save(paciente);
	}	
	
	// Buscar paciente por id.
	@GetMapping("/pacientes/{idPaciente}")
	public PacienteModel getPacienteById(@CurrentUser UserPrincipal currentUser,
										 @PathVariable(value = "idPaciente") Long pacienteId) throws Exception {
		
		return pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);
		
	}
	
	// Atualizar um paciente buscando por id.
	@PutMapping("/pacientes/{idPaciente}")
	public PacienteModel updatePaciente(@PathVariable(value = "idPaciente") Long pacienteId,
				@Valid @RequestBody PacienteModel pacienteDetails) {
		PacienteModel paciente = pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));
				
		paciente.setIdPaciente(pacienteDetails.getIdPaciente());
		paciente.setCpf(pacienteDetails.getCpf());
		paciente.setIdentidade(pacienteDetails.getIdentidade());
		paciente.setIdade(pacienteDetails.getIdade());
		paciente.setTelefone(pacienteDetails.getTelefone());
		paciente.setNomePaciente(pacienteDetails.getNomePaciente());
		paciente.setNomeMae(pacienteDetails.getNomeMae());
		paciente.setDataNasc(pacienteDetails.getDataNasc());
		paciente.setDum(pacienteDetails.getDum());
		paciente.setDpp(pacienteDetails.getDpp());
		paciente.setAltura(pacienteDetails.getAltura());
		paciente.setPeso(pacienteDetails.getPeso());
		paciente.setImc(pacienteDetails.getImc());
		paciente.setEstadoCivil(pacienteDetails.getEstadoCivil());
		
		PacienteModel updatedPaciente = pacienteRepository.save(paciente);
		return updatedPaciente;
	}
	// Deletar um paciente buscando por id.
	@DeleteMapping("/pacientes/{idPaciente}")
	public PacienteModel deletePaciente(@PathVariable(value = "idPaciente") Long pacienteId) {
		PacienteModel paciente = pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));
		
		pacienteRepository.delete(paciente);
		return paciente;
	}

}
