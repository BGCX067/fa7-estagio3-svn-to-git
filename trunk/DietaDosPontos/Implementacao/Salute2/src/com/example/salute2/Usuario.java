package com.example.salute2;

public class Usuario {
	
	int id;
	String nome;
	String email;
	String senha;
	String peso;
	String sexo;
	String idade;

	
	public Usuario(int id,String nome, String email, String senha, String peso, String sexo, String idade){
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.peso = peso;
		this.sexo = sexo;
		this.idade = idade;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	
	@Override
	public String toString(){
		return nome + " " + email + " " + senha + " " + peso + " " + idade + "" + sexo;
	}
}

