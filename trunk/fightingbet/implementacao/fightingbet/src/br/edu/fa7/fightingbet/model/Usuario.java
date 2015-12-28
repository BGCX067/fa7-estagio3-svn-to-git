package br.edu.fa7.fightingbet.model;

import com.google.gson.annotations.SerializedName;

public class Usuario extends BaseModel {
	
	@SerializedName("nome")
	private String nome;
	
	@SerializedName("email")
	private String email;
	
	@SerializedName("password")
	private String senha;
	
	@SerializedName("auth_token")
	private String token;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String email, String token) {
		this.nome = nome;
		this.email = email;
		this.token = token;
	}

	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

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
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
