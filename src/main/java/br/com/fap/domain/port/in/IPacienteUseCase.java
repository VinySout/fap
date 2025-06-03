package br.com.fap.domain.port.in;

import java.util.List;

import br.com.fap.adapter.out.security.UserPrincipal;
import br.com.fap.domain.model.PacienteModel;

public interface IPacienteUseCase {

	List<PacienteModel> listarPacientes(UserPrincipal currentUser) throws Exception;
	
	PacienteModel buscarPaciente(UserPrincipal currentUser, Long pacienteId) throws Exception;
	
	PacienteModel inserirPaciente(UserPrincipal currentUser, PacienteModel paciente);
	
	PacienteModel editarPaciente(Long pacienteId, PacienteModel pacienteDetails);
	
	PacienteModel excluirPaciente(Long pacienteId);
}
