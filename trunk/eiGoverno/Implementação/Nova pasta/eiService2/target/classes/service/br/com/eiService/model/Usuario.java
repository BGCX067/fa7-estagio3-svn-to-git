package br.com.eiService.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity @XmlRootElement
public class Usuario{
	@Id @GeneratedValue
	private long id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	@OneToMany(mappedBy = "usuario", targetEntity = Marcacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Marcacao> marcacaos;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
