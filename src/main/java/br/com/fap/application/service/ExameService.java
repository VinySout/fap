package br.com.fap.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fap.adapter.out.security.UserPrincipal;
import br.com.fap.domain.exception.ResourceNotFoundException;
import br.com.fap.domain.model.ExamesModel;
import br.com.fap.domain.model.PacienteModel;
import br.com.fap.domain.port.in.IExameUseCase;
import br.com.fap.domain.port.in.IPacienteUseCase;
import br.com.fap.domain.port.out.ExamesRepository;

@Service
public class ExameService implements IExameUseCase {
	
	@Autowired
	private ExamesRepository examesRepository;
	
	@Autowired
	private IPacienteUseCase pacienteUseCase;

	@Override
	public List<ExamesModel> listarExamesPaciente(UserPrincipal currentUser, Long pacienteId) throws Exception {
		pacienteUseCase.buscarPaciente(currentUser, pacienteId);
		 
		 return examesRepository.findByPacienteIdPaciente(pacienteId);
	}

	@Override
	public ExamesModel buscarExamePaciente(UserPrincipal currentUser, Long idPaciente, Long idExames) throws Exception {
		pacienteUseCase.buscarPaciente(currentUser, idPaciente);
		 
		 return examesRepository.findById(idExames)
				 .orElseThrow(() -> new ResourceNotFoundException("Endereco", "idExames", idExames));
	}

	@Override
	public ExamesModel inserirExamePaciente(UserPrincipal currentUser, Long pacienteId, ExamesModel exames)
			throws Exception {
		PacienteModel paciente = pacienteUseCase.buscarPaciente(currentUser, pacienteId);
		
		exames.setPaciente(paciente);
		return examesRepository.save(exames);
	}

	@Override
	public ExamesModel editarExamePaciente(UserPrincipal currentUser, Long pacienteId, Long examesId,
			ExamesModel examesDetails) throws Exception {
		pacienteUseCase.buscarPaciente(currentUser, pacienteId);
		
		return examesRepository.findById(examesId)
				.map(exames -> {
					exames.setAboRh(examesDetails.getAboRh());
					exames.setDtAboRh(examesDetails.getDtAboRh());
					exames.setHemoglobina(examesDetails.getHemoglobina());
					exames.setDtHemoglobina(examesDetails.getDtHemoglobina());
					exames.setVdrlHiv(examesDetails.getVdrlHiv());
					exames.setDtVdrlHiv(examesDetails.getDtVdrlHiv());
					exames.setUrinaEas(examesDetails.getUrinaEas());
					exames.setDtUrinaEas(examesDetails.getDtUrinaEas());
					exames.setCitOncotica(examesDetails.getCitOncotica());
					exames.setDtCitOncotica(examesDetails.getDtCitOncotica());					
					exames.setGlicemia(examesDetails.getGlicemia());
					exames.setDtGlicemia(examesDetails.getDtGlicemia());					
					exames.setSifilis(examesDetails.getSifilis());
					exames.setDtSifilis(examesDetails.getDtSifilis());					
					exames.setHepatite(examesDetails.getHepatite());
					exames.setDtHepatite(examesDetails.getDtHepatite());					
					exames.setToxoplasmose(examesDetails.getToxoplasmose());					
					exames.setDtToxopla(examesDetails.getDtToxopla());					
					exames.setCitomegalovirus(examesDetails.getCitomegalovirus());
					exames.setDtCitomega(examesDetails.getDtCitomega());
					
					
					return examesRepository.save(exames);
				}).orElseThrow(() -> new ResourceNotFoundException("Exames", "idExames", examesId));
	}

	@Override
	public ExamesModel excluirExamePaciente(UserPrincipal currentUser, Long pacienteId, Long examesId)
			throws Exception {
		pacienteUseCase.buscarPaciente(currentUser, pacienteId);

		return examesRepository.findById(examesId)
				.map(exames ->{
					examesRepository.delete(exames);
					return exames;
				}).orElseThrow(() -> new ResourceNotFoundException("Exames", "IdExames", examesId));
	}

}
