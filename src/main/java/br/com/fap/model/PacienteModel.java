package br.com.fap.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "pacientes")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class PacienteModel implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Long idPaciente;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ConsultaModel> consultas;
	
    private Long idUsuario;
	@NotBlank
    private String cpf;	
	@NotBlank
    private String identidade;
	@NotBlank
    private String idade;
	@NotBlank
    private String telefone;
	@NotBlank
    private String nomePaciente;
	@NotBlank
    private String nomeMae;
	@NotBlank
    private String dataNasc;
	@NotBlank
	private String dum;
	@NotBlank
	private String dpp;
	@NotBlank
	private String peso;
	@NotBlank
	private String altura;
	@NotBlank
	private String casada;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
        
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
		
	public Set<ConsultaModel> getConsultas() {
		return consultas;
	}
	public void setConsultas(Set<ConsultaModel> consultas) {
		this.consultas = consultas;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getIdentidade() {
		return identidade;
	}
	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
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
	public String getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getDum() {
		return dum;
	}
	public void setDum(String dum) {
		this.dum = dum;
	}
	public String getDpp() {
		return dpp;
	}
	public void setDpp(String dpp) {
		this.dpp = dpp;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getCasada() {
		return casada;
	}
	public void setCasada(String casada) {
		this.casada = casada;
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
	@Override
	  public String toString() {
	    return "PacienteModel [idPaciente=" + idPaciente + ", idUsuario=" + idUsuario + ", cpf=" + cpf
	        + ", identidade=" + identidade + ", idade=" + idade + ", telefone=" + telefone + ", nomePaciente=" + nomePaciente
	        + ", nomeMae=" + nomeMae + ", dataNasc=" + dataNasc + ", dum=" + dum + ", dpp=" + dpp + ", peso=" + peso + ", altura=" + altura
	        + ", casada" + casada + ", createdAt=" + createdAt + ", updatedAt" + updatedAt + "]";
	  }
	
	
}
