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
import br.com.fap.model.EnderecoModel;
import br.com.fap.repository.EnderecoRepository;
import br.com.fap.repository.PacienteRepository;

@RestController
@RequestMapping("/api")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	
	// Listar endereço de um paciente.	
	@GetMapping("/pacientes/{idPaciente}/endereco")
	public List<EnderecoModel> getEnderecoByPacienteId(@PathVariable Long idPaciente) {
		pacienteRepository.findById(idPaciente)
		.orElseThrow(() -> new ResourceNotFoundException("Paciente", "IdPaciente", idPaciente));
		
		return enderecoRepository.findByPacienteIdPaciente(idPaciente);
	}
	@GetMapping("/pacientes/{idPaciente}/endereco/{idEndereco}")
	 public EnderecoModel getEndByPacIdAndEndId(@PathVariable Long idPaciente, 
			 									@PathVariable Long idEndereco) {
		 pacienteRepository.findById(idPaciente)
		 .orElseThrow(() -> new ResourceNotFoundException("Paciente", "IdPaciente", idPaciente));
		 
		 return enderecoRepository.findById(idEndereco)
				 .orElseThrow(() -> new ResourceNotFoundException("Endereco", "idEndereco", idEndereco));
	 }
	// Adicionar um endereço a um paciente.
	@PostMapping("/pacientes/{pacienteId}/endereco")
	public EnderecoModel addEndereco(@PathVariable Long pacienteId, @Valid @RequestBody EnderecoModel endereco) {
		return pacienteRepository.findById(pacienteId)
				.map(paciente -> {
					endereco.setPaciente(paciente);
					return enderecoRepository.save(endereco);
		}).orElseThrow(() -> new ResourceNotFoundException("Paciente", "pacienteId", pacienteId));
	}
	// Atualizar endereço de um paciente.
	@PutMapping("/pacientes/{pacienteId}/endereco/{enderecoId}")
	public EnderecoModel updateEndereco(@PathVariable Long pacienteId, 
										@PathVariable Long enderecoId,
										@Valid @RequestBody EnderecoModel enderecoDetails) {
		
		pacienteRepository.findById(pacienteId)
		.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));
		
		return enderecoRepository.findById(enderecoId)
				.map(endereco -> {				
					endereco.setCep(enderecoDetails.getCep());
					endereco.setUf(enderecoDetails.getUf());
					endereco.setCidade(enderecoDetails.getCidade());
					endereco.setRua(enderecoDetails.getRua());
					endereco.setNumero(enderecoDetails.getNumero());
					
					return enderecoRepository.save(endereco);
				}).orElseThrow(() -> new ResourceNotFoundException("Endereço", "idEndereco", enderecoId));
		
	}
	// Deletar endereco de um paciente.
	@DeleteMapping("/pacientes/{pacienteId}/endereco/{enderecoId}")
	public String deleteEndereco(@PathVariable Long pacienteId,
								@PathVariable Long enderecoId) {
		pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));

		return enderecoRepository.findById(enderecoId)
				.map(endereco ->{
					enderecoRepository.delete(endereco);
					return "Endereço com o id: " + enderecoId + " foi deletada pelo Usuário.";
				}).orElseThrow(() -> new ResourceNotFoundException("Exames", "IdExames", enderecoId));
	}	
}
