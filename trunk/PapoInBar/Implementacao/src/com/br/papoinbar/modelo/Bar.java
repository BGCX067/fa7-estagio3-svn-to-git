package com.br.papoinbar.modelo;

import java.util.List;


public class Bar {
	private long id;
	private String nome;
	private String tipoDeBar;
	private String endereco;
	private String descricao;
	
	public Bar(long id, String nome, String endereco, String tipoDeBar, String descricao){
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.tipoDeBar = tipoDeBar;
		this.descricao = descricao;
	}
	public Bar(String nome, String endereco, String tipoDeBar, String descricao){

		this.nome = nome;
		this.endereco = endereco;
		this.tipoDeBar = tipoDeBar;
		this.descricao = descricao;

	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoDeBar() {
		return tipoDeBar;
	}

	public void setTipoDeBar(String tipoDeBar) {
		this.tipoDeBar = tipoDeBar;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nome;
	}

}

 
