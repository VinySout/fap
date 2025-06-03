package br.com.fap.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fap.adapter.out.security.UserPrincipal;
import br.com.fap.domain.exception.ResourceNotFoundException;
import br.com.fap.domain.model.EnderecoModel;
import br.com.fap.domain.model.PacienteModel;
import br.com.fap.domain.port.in.IEnderecoUseCase;
import br.com.fap.domain.port.out.EnderecoRepository;
import br.com.fap.domain.port.out.impl.PacienteRepositoryImpl;

@Service
public class EnderecoService implements IEnderecoUseCase {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PacienteRepositoryImpl pacienteRepositoryImpl;

	@Override
	public List<EnderecoModel> listarEnderecosPaciente(UserPrincipal currentUser, Long idPaciente) 
			throws Exception {
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, idPaciente);
		
		return enderecoRepository.findByPacienteIdPaciente(idPaciente);
	}

	@Override
	public EnderecoModel buscarEnderecoPaciente(UserPrincipal currentUser, Long idPaciente, Long idEndereco)
			throws Exception {
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, idPaciente);
		 
		 return enderecoRepository.findById(idEndereco)
				 .orElseThrow(() -> new ResourceNotFoundException("Endereco", "idEndereco", idEndereco));
	}

	@Override
	public EnderecoModel inserirEnderecoPaciente(UserPrincipal currentUser, Long pacienteId, EnderecoModel endereco)
			throws Exception {
		PacienteModel paciente = pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);
		
		endereco.setPaciente(paciente);
		
		return enderecoRepository.save(endereco);
	}

	@Override
	public EnderecoModel editarEnderecoPaciente(Long pacienteId, UserPrincipal currentUser, Long enderecoId,
			EnderecoModel enderecoDetails) throws Exception {
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

	@Override
	public EnderecoModel excluirEndereco(Long pacienteId, UserPrincipal currentUser, Long enderecoId) 
			throws Exception {
		pacienteRepositoryImpl.findByIdUserAndPaciente(currentUser, pacienteId);

		return enderecoRepository.findById(enderecoId)
				.map(endereco ->{
					enderecoRepository.delete(endereco);
					return endereco;
				}).orElseThrow(() -> new ResourceNotFoundException("Exames", "IdExames", enderecoId));
	}

}
