package br.com.fap.domain.port.in;

import java.util.List;

import br.com.fap.adapter.out.security.UserPrincipal;
import br.com.fap.domain.model.ConsultaModel;

public interface IConsultaUseCase {
	
	List<ConsultaModel> listarConsultasPaciente(UserPrincipal currentUser, Long pacienteId) 
			throws Exception;

	ConsultaModel buscarConsultaPaciente(UserPrincipal currentUser, Long pacienteId, Long consultaId) 
			throws Exception;
	
	ConsultaModel inserirConsultaPaciente(UserPrincipal currentUser, Long pacienteId, ConsultaModel consulta)
			throws Exception;
	
	ConsultaModel editarConsultaPaciente(UserPrincipal currentUser, Long pacienteId, Long consultaId,
			ConsultaModel consultaDetails) throws Exception;
	
	ConsultaModel excluirConsultaPaciente(UserPrincipal currentUser, Long pacienteId, Long consultaId) 
			throws Exception;
}
