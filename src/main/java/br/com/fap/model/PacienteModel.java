package br.com.fap.model;

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
        
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Long getIdentidade() {
		return identidade;
	}
	public void setIdentidade(Long identidade) {
		this.identidade = identidade;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public Date getDum() {
		return dum;
	}
	public void setDum(Date dum) {
		this.dum = dum;
	}
	public Date getDpp() {
		return dpp;
	}
	public void setDpp(Date dpp) {
		this.dpp = dpp;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public Double getImc() {
		return imc;
	}
	public void setImc(Double imc) {
		this.imc = imc;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Set<ConsultaModel> getConsultas() {
		return consultas;
	}
	public void setConsultas(Set<ConsultaModel> consultas) {
		this.consultas = consultas;
	}		
	public Set<ExamesModel> getExames() {
		return exames;
	}
	public void setExames(Set<ExamesModel> exames) {
		this.exames = exames;
	}		
	public Set<EnderecoModel> getEndereco() {
		return endereco;
	}
	public void setEndereco(Set<EnderecoModel> endereco) {
		this.endereco = endereco;
	}	
	public UsuarioModel getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
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
