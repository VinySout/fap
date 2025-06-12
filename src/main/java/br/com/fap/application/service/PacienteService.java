package br.com.fap.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fap.adapter.out.security.UserPrincipal;
import br.com.fap.domain.exception.ResourceNotFoundException;
import br.com.fap.domain.model.PacienteModel;
import br.com.fap.domain.model.UsuarioModel;
import br.com.fap.domain.port.in.IPacienteUseCase;
import br.com.fap.domain.port.out.PacienteRepository;
import br.com.fap.domain.port.out.UsuarioRepository;

@Service
public class PacienteService implements IPacienteUseCase {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<PacienteModel> listarPacientes(UserPrincipal currentUser) throws Exception {
		return pacienteRepository.findAllByUsuarioId(currentUser.getId());
	}

	@Override
	public PacienteModel buscarPaciente(UserPrincipal currentUser, Long pacienteId) throws Exception {
		PacienteModel pacienteModel = pacienteRepository.findByUsuarioIdAndIdPaciente(currentUser.getId(), pacienteId)
		.orElseThrow(() -> new ResourceNotFoundException("Paciente", "Nº", pacienteId));
		
		return pacienteModel;
	}

	@Override
	public PacienteModel inserirPaciente(UserPrincipal currentUser, PacienteModel paciente) {
		Optional<UsuarioModel> usuario =  usuarioRepository.findById(currentUser.getId());
		paciente.setUsuario(usuario.get());
		
		return pacienteRepository.save(paciente);
	}

	@Override
	public PacienteModel editarPaciente(Long pacienteId, PacienteModel pacienteDetails) {
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

	@Override
	public PacienteModel excluirPaciente(Long pacienteId) {
		PacienteModel paciente = pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente", "idPaciente", pacienteId));
		
		pacienteRepository.delete(paciente);
		return paciente;
	}

}
