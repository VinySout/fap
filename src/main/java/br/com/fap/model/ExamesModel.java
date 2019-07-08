package br.com.fap.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "exames")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class ExamesModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idExames;
	
	@ManyToOne (fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "paciente_id", nullable = false)
	@JsonIgnore
	private PacienteModel paciente;
	@NotBlank
	private String aboRh;
	@NotNull
	private int glicemia;
	@NotNull
	private boolean sifilis;
	@NotNull
	private boolean vdrlHiv;
	@NotNull
	private boolean hepatite;
	@NotNull
	private boolean toxoplasmose;
	@NotNull
	private boolean citomegalovirus;
	@NotNull
	private int urinaEas;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date updatedAt;

	public Long getIdExames() {
		return idExames;
	}

	public void setIdExames(Long idExames) {
		this.idExames = idExames;
	}

	public PacienteModel getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteModel paciente) {
		this.paciente = paciente;
	}

	public String getAboRh() {
		return aboRh;
	}

	public void setAboRh(String aboRh) {
		this.aboRh = aboRh;
	}

	public int getGlicemia() {
		return glicemia;
	}

	public void setGlicemia(int glicemia) {
		this.glicemia = glicemia;
	}

	public boolean getSifilis() {
		return sifilis;
	}

	public void setSifilis(boolean sifilis) {
		this.sifilis = sifilis;
	}

	public boolean getVdrlHiv() {
		return vdrlHiv;
	}

	public void setVdrlHiv(boolean vdrlHiv) {
		this.vdrlHiv = vdrlHiv;
	}

	public boolean getHepatite() {
		return hepatite;
	}

	public void setHepatite(boolean hepatite) {
		this.hepatite = hepatite;
	}

	public boolean getToxoplasmose() {
		return toxoplasmose;
	}

	public void setToxoplasmose(boolean toxoplasmose) {
		this.toxoplasmose = toxoplasmose;
	}

	public boolean getCitomegalovirus() {
		return citomegalovirus;
	}

	public void setCitomegalovirus(boolean citomegalovirus) {
		this.citomegalovirus = citomegalovirus;
	}

	public int getUrinaEas() {
		return urinaEas;
	}

	public void setUrinaEas(int urinaEas) {
		this.urinaEas = urinaEas;
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
