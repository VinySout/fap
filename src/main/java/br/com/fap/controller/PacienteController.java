package br.com.fap.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fap.exception.ResourceNotFoundException;
import br.com.fap.model.PacienteModel;
import br.com.fap.repository.PacienteRepository;

@RestController
@RequestMapping("/api")
public class PacienteController {
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	//listar todos os pacientes
	@GetMapping("/pacientes")
	public List<PacienteModel> getAllPacientes(){
		return pacienteRepository.findAll();
	}
	
	//Adicionar um novo paciente
	@PostMapping("/pacientes")
	public PacienteModel novoPaciente(@Valid @RequestBody PacienteModel paciente) {
		return pacienteRepository.save(paciente);
	}
	
	//buscar paciente por id
	@GetMapping("/pacientes/{idPaciente}")
	public PacienteModel getPacienteById(@PathVariable(value = "idPaciente") Long pacienteId) {
		return pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));
	}
	//atualizar um paciente, buscando por id
	@PutMapping("/pacientes/{idPaciente}")
	public PacienteModel updatePaciente(@PathVariable(value = "idPaciente") Long pacienteId,
				@Valid @RequestBody PacienteModel pacienteDetails) {
		PacienteModel paciente = pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));
				
		paciente.setIdPaciente(pacienteDetails.getIdPaciente());		
		paciente.setIdUsuario(pacienteDetails.getIdUsuario());
		paciente.setCpf(pacienteDetails.getCpf());
		paciente.setIdentidade(pacienteDetails.getIdentidade());
		paciente.setIdade(pacienteDetails.getIdade());
		paciente.setTelefone(pacienteDetails.getTelefone());
		paciente.setNomePaciente(pacienteDetails.getNomePaciente());
		paciente.setNomeMae(pacienteDetails.getNomeMae());
		paciente.setDataNasc(pacienteDetails.getDataNasc());
		paciente.setDum(pacienteDetails.getDum());
		paciente.setDpp(pacienteDetails.getDpp());
		paciente.setPeso(pacienteDetails.getPeso());
		paciente.setAltura(pacienteDetails.getAltura());
		paciente.setCasada(pacienteDetails.getCasada());
		
		PacienteModel updatedPaciente = pacienteRepository.save(paciente);
		return updatedPaciente;
	}
	//deletar um paciente buscando por Id
	@DeleteMapping("/pacientes/{idPaciente}")
	public ResponseEntity<?> deletePaciente(@PathVariable(value = "idPaciente") Long pacienteId) {
		PacienteModel paciente = pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));
		
		pacienteRepository.delete(paciente);
		
		return ResponseEntity.ok().build();
	}

}
