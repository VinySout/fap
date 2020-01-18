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
import br.com.fap.model.EnderecoModel;
import br.com.fap.model.PacienteModel;
import br.com.fap.repository.EnderecoRepository;
import br.com.fap.repository.impl.PacienteRepositoryImpl;

@RestController
@RequestMapping("/api")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PacienteRepositoryImpl pacienteRepositoryImpl;
	
	// Listar endereço de um paciente.	
	@GetMapping("/pacientes/{idPaciente}/endereco")
	public List<EnderecoModel> getEnderecoByPacienteId(@CurrentUser UserPrincipal currentUser,
														@PathVariable Long idPaciente) throws Exception {
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, idPaciente);
		
		return enderecoRepository.findByPacienteIdPaciente(idPaciente);
	}
	@GetMapping("/pacientes/{idPaciente}/endereco/{idEndereco}")
	 public EnderecoModel getEndByPacIdAndEndId(@PathVariable Long idPaciente, 
			 									@CurrentUser UserPrincipal currentUser,
			 									@PathVariable Long idEndereco) throws Exception {
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, idPaciente);
		 
		 return enderecoRepository.findById(idEndereco)
				 .orElseThrow(() -> new ResourceNotFoundException("Endereco", "idEndereco", idEndereco));
	 }
	// Adicionar um endereço a um paciente.
	@PostMapping("/pacientes/{pacienteId}/endereco")
	public EnderecoModel addEndereco(@CurrentUser UserPrincipal currentUser, 
									 @PathVariable Long pacienteId, @Valid @RequestBody EnderecoModel endereco) throws Exception {
		
		PacienteModel paciente = pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);
				
		endereco.setPaciente(paciente);
		
		return enderecoRepository.save(endereco);
	}
	// Atualizar endereço de um paciente.
	@PutMapping("/pacientes/{pacienteId}/endereco/{enderecoId}")
	public EnderecoModel updateEndereco(@PathVariable Long pacienteId, 
										@CurrentUser UserPrincipal currentUser, 
										@PathVariable Long enderecoId,
										@Valid @RequestBody EnderecoModel enderecoDetails) throws Exception {
		
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);
		
		return enderecoRepository.findById(enderecoId)
				.map(endereco -> {				
					endereco.setCep(enderecoDetails.getCep());
					endereco.setUf(enderecoDetails.getUf());
					endereco.setCidade(enderecoDetails.getCidade());
					endereco.setBairro(enderecoDetails.getBairro());
					endereco.setRua(enderecoDetails.getRua());
					endereco.setNumero(enderecoDetails.getNumero());
					
					return enderecoRepository.save(endereco);
				}).orElseThrow(() -> new ResourceNotFoundException("Endereço", "idEndereco", enderecoId));
		
	}
	// Deletar endereco de um paciente.
	@DeleteMapping("/pacientes/{pacienteId}/endereco/{enderecoId}")
	public EnderecoModel deleteEndereco(@PathVariable Long pacienteId,
										@CurrentUser UserPrincipal currentUser, 
									    @PathVariable Long enderecoId) throws Exception {
		
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);

		return enderecoRepository.findById(enderecoId)
				.map(endereco ->{
					enderecoRepository.delete(endereco);
					return endereco;
				}).orElseThrow(() -> new ResourceNotFoundException("Exames", "IdExames", enderecoId));
	}	
}
