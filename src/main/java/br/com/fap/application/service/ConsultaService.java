package br.com.fap.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fap.adapter.out.security.UserPrincipal;
import br.com.fap.domain.exception.ResourceNotFoundException;
import br.com.fap.domain.model.ConsultaModel;
import br.com.fap.domain.model.PacienteModel;
import br.com.fap.domain.port.in.IConsultaUseCase;
import br.com.fap.domain.port.in.IPacienteUseCase;
import br.com.fap.domain.port.out.ConsultaRepository;

@Service
public class ConsultaService implements IConsultaUseCase {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private IPacienteUseCase pacienteUseCase;

	@Override
	public List<ConsultaModel> listarConsultasPaciente(UserPrincipal currentUser, Long idPaciente) throws Exception {
		pacienteUseCase.buscarPaciente(currentUser, idPaciente);
		return consultaRepository.findByPacienteIdPaciente(idPaciente);
	}

	@Override
	public ConsultaModel buscarConsultaPaciente(UserPrincipal currentUser, Long idPaciente, Long idConsulta)
			throws Exception {
		pacienteUseCase.buscarPaciente(currentUser, idPaciente);
		return consultaRepository.findById(idConsulta)
				.orElseThrow(() -> new ResourceNotFoundException("Consulta", "idConsulta", idConsulta));
	}

	@Override
	public ConsultaModel inserirConsultaPaciente(UserPrincipal currentUser, Long pacienteId, ConsultaModel consulta)
			throws Exception {
		PacienteModel paciente = pacienteUseCase.buscarPaciente(currentUser, pacienteId);
		
		consulta.setPaciente(paciente);
		
		return consultaRepository.save(consulta);
	}

	@Override
	public ConsultaModel editarConsultaPaciente(UserPrincipal currentUser, Long pacienteId, Long consultaId,
			ConsultaModel consultaDetails) throws Exception {
		pacienteUseCase.buscarPaciente(currentUser, pacienteId);
		
		return consultaRepository.findById(consultaId)
				.map(consulta -> {
					consulta.setIdConsulta(consultaDetails.getIdConsulta());					
					consulta.setDataConsulta(consultaDetails.getDataConsulta());
					consulta.setIdadeGestacional(consultaDetails.getIdadeGestacional());
					consulta.setPeso(consultaDetails.getPeso());						
					consulta.setEdema(consultaDetails.getEdema());
					consulta.setPresArterial(consultaDetails.getPresArterial());
					consulta.setAlturaUterina(consultaDetails.getAlturaUterina());
					consulta.setApresFetal(consultaDetails.getApresFetal());
					consulta.setBcf(consultaDetails.getBcf());
					consulta.setToque(consultaDetails.getToque());
					consulta.setObs(consultaDetails.getObs());
					
					return consultaRepository.save(consulta);
				}).orElseThrow(() -> new ResourceNotFoundException("Consulta", "idConsulta", consultaId));
	}

	@Override
	public ConsultaModel excluirConsultaPaciente(UserPrincipal currentUser, Long pacienteId, Long consultaId)
			throws Exception {
		pacienteUseCase.buscarPaciente(currentUser, pacienteId);

		return consultaRepository.findById(consultaId)
				.map(consulta ->{
					consultaRepository.delete(consulta);
					return consulta;
				}).orElseThrow(() -> new ResourceNotFoundException("Consulta", "IdConsulta", consultaId));
	}
	
}
