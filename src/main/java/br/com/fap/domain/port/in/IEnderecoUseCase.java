package br.com.fap.domain.port.in;

import java.util.List;

import br.com.fap.adapter.out.security.UserPrincipal;
import br.com.fap.domain.model.EnderecoModel;

public interface IEnderecoUseCase {

	List<EnderecoModel> listarEnderecosPaciente(UserPrincipal currentUser, Long idPaciente)
			 throws Exception;
	
	EnderecoModel buscarEnderecoPaciente(UserPrincipal currentUser, Long idPaciente, Long idEndereco) 
			throws Exception;
	
	EnderecoModel inserirEnderecoPaciente(UserPrincipal currentUser, Long pacienteId, EnderecoModel endereco) 
			throws Exception;
	
	EnderecoModel editarEnderecoPaciente(Long pacienteId, UserPrincipal currentUser, 
			Long enderecoId, EnderecoModel enderecoDetails) throws Exception;
	
	EnderecoModel excluirEndereco(Long pacienteId, UserPrincipal currentUser, Long enderecoId)
			throws Exception;
}
