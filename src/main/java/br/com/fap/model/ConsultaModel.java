package br.com.fap.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "consultas")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class ConsultaModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Long idConsulta;
	
	@ManyToOne (fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "paciente_id", nullable = false)
	@JsonIgnore
	private PacienteModel paciente;
	
	@NotBlank
	private String dataConsulta;
	@NotBlank
	private String queixa;
	@NotBlank
	private String idadeGestacional;
	@NotBlank
	private String pesoImc;
	@NotBlank
	private String edema;
	@NotBlank
	private String presArterial;
	@NotBlank
	private String alturaUterina;
	@NotBlank
	private String presFetal;
	@NotBlank
	private String bcf;
	@NotBlank
	private String toque;
	@NotBlank
	private String obs;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
	
	public Long getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Long idConsulta) {
		this.idConsulta = idConsulta;
	}
	
	public PacienteModel getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteModel paciente) {
		this.paciente = paciente;
	}

	public String getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getQueixa() {
		return queixa;
	}

	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}

	public String getIdadeGestacional() {
		return idadeGestacional;
	}

	public void setIdadeGestacional(String idadeGestacional) {
		this.idadeGestacional = idadeGestacional;
	}

	public String getPesoImc() {
		return pesoImc;
	}

	public void setPesoImc(String pesoImc) {
		this.pesoImc = pesoImc;
	}

	public String getEdema() {
		return edema;
	}

	public void setEdema(String edema) {
		this.edema = edema;
	}

	public String getPresArterial() {
		return presArterial;
	}

	public void setPresArterial(String presArterial) {
		this.presArterial = presArterial;
	}

	public String getAlturaUterina() {
		return alturaUterina;
	}

	public void setAlturaUterina(String alturaUterina) {
		this.alturaUterina = alturaUterina;
	}

	public String getPresFetal() {
		return presFetal;
	}

	public void setPresFetal(String presFetal) {
		this.presFetal = presFetal;
	}

	public String getBcf() {
		return bcf;
	}

	public void setBcf(String bcf) {
		this.bcf = bcf;
	}

	public String getToque() {
		return toque;
	}

	public void setToque(String toque) {
		this.toque = toque;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
