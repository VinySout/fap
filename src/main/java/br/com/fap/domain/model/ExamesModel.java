package br.com.fap.domain.model;

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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
