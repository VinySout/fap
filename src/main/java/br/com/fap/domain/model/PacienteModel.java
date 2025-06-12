package br.com.fap.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@Table(name = "pacientes")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class PacienteModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne (fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id", nullable = false)
	@JsonIgnore
	private UsuarioModel usuario;
	
	@Id
    private Long idPaciente;	
	@NotNull
    private String cpf;
	@NotNull
    private Long identidade;
	@NotNull
    private int idade;
	@NotNull
    private String telefone;
	@NotBlank
    private String nomePaciente;
	@NotBlank
    private String nomeMae;
	@NotBlank
	private String estadoCivil;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
    private Date dataNasc;	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dum; // Data da Última Mestruação	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)	
	private Date dpp; // Data Provável do Parto
	
	private Double altura;
	private Double peso;
	private Double imc;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ConsultaModel> consultas;
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ExamesModel> exames;
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<EnderecoModel> endereco;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date updatedAt;
}
