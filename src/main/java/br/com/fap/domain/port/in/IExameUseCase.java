package br.com.fap.domain.port.in;

import java.util.List;

import br.com.fap.adapter.out.security.UserPrincipal;
import br.com.fap.domain.model.ExamesModel;

public interface IExameUseCase {

	List<ExamesModel> listarExamesPaciente(UserPrincipal currentUser, Long pacienteId) 
			throws Exception;
	
	ExamesModel buscarExamePaciente(UserPrincipal currentUser, Long idPaciente, Long idExames) 
			throws Exception;
	
	ExamesModel inserirExamePaciente(UserPrincipal currentUser, Long pacienteId, ExamesModel exames) 
			throws Exception;
	
	ExamesModel editarExamePaciente(UserPrincipal currentUser,Long pacienteId, Long examesId, 
			ExamesModel examesDetails) throws Exception;
	
	ExamesModel excluirExamePaciente(UserPrincipal currentUser, Long pacienteId,
			Long examesId) throws Exception;
}
