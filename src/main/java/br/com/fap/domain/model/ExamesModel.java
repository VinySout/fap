package br.com.fap.domain.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
	
	private String aboRh;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAboRh;
	
	private String hemoglobina;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtHemoglobina;
	
	private String vdrlHiv;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtVdrlHiv;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtUrinaEas;
	private int urinaEas;
	
	private String citOncotica;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCitOncotica;
	
	private int glicemia;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtGlicemia;
	
	private String sifilis;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtSifilis;
		
	private String hepatite;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtHepatite;
	
	private String toxoplasmose;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtToxopla;
	
	private String citomegalovirus;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCitomega;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
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
	public Date getDtAboRh() {
		return dtAboRh;
	}

	public void setDtAboRh(Date dtAboRh) {
		this.dtAboRh = dtAboRh;
	}

	public String getHemoglobina() {
		return hemoglobina;
	}

	public void setHemoglobina(String hemoglobina) {
		this.hemoglobina = hemoglobina;
	}
	public Date getDtHemoglobina() {
		return dtHemoglobina;
	}

	public void setDtHemoglobina(Date dtHemoglobina) {
		this.dtHemoglobina = dtHemoglobina;
	}

	public String getVdrlHiv() {
		return vdrlHiv;
	}

	public void setVdrlHiv(String vdrlHiv) {
		this.vdrlHiv = vdrlHiv;
	}
	public Date getDtVdrlHiv() {
		return dtVdrlHiv;
	}

	public void setDtVdrlHiv(Date dtVdrlHiv) {
		this.dtVdrlHiv = dtVdrlHiv;
	}
	public Date getDtUrinaEas() {
		return dtUrinaEas;
	}

	public void setDtUrinaEas(Date dtUrinaEas) {
		this.dtUrinaEas = dtUrinaEas;
	}

	public int getUrinaEas() {
		return urinaEas;
	}

	public void setUrinaEas(int urinaEas) {
		this.urinaEas = urinaEas;
	}

	public String getCitOncotica() {
		return citOncotica;
	}

	public void setCitOncotica(String citOncotica) {
		this.citOncotica = citOncotica;
	}
	public Date getDtCitOncotica() {
		return dtCitOncotica;
	}

	public void setDtCitOncotica(Date dtCitOncotica) {
		this.dtCitOncotica = dtCitOncotica;
	}

	public int getGlicemia() {
		return glicemia;
	}

	public void setGlicemia(int glicemia) {
		this.glicemia = glicemia;
	}
	public Date getDtGlicemia() {
		return dtGlicemia;
	}

	public void setDtGlicemia(Date dtGlicemia) {
		this.dtGlicemia = dtGlicemia;
	}

	public String getSifilis() {
		return sifilis;
	}

	public void setSifilis(String sifilis) {
		this.sifilis = sifilis;
	}
	public Date getDtSifilis() {
		return dtSifilis;
	}

	public void setDtSifilis(Date dtSifilis) {
		this.dtSifilis = dtSifilis;
	}

	public String getHepatite() {
		return hepatite;
	}

	public void setHepatite(String hepatite) {
		this.hepatite = hepatite;
	}
	public Date getDtHepatite() {
		return dtHepatite;
	}

	public void setDtHepatite(Date dtHepatite) {
		this.dtHepatite = dtHepatite;
	}

	public String getToxoplasmose() {
		return toxoplasmose;
	}

	public void setToxoplasmose(String toxoplasmose) {
		this.toxoplasmose = toxoplasmose;
	}
	public Date getDtToxopla() {
		return dtToxopla;
	}

	public void setDtToxopla(Date dtToxopla) {
		this.dtToxopla = dtToxopla;
	}

	public String getCitomegalovirus() {
		return citomegalovirus;
	}

	public void setCitomegalovirus(String citomegalovirus) {
		this.citomegalovirus = citomegalovirus;
	}
	public Date getDtCitomega() {
		return dtCitomega;
	}

	public void setDtCitomega(Date dtCitomega) {
		this.dtCitomega = dtCitomega;
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
