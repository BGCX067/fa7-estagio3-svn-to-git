package br.com.pedidoonline.app.model;

import java.util.ArrayList;

public class Cardapio extends AbstractEntity {
	
	private String nomeEstabelecimento;
	
	private ArrayList<Categoria> categorias;

	
	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}

	public ArrayList<Categoria> getCategorias() {
		if(categorias == null) {
			categorias = new ArrayList<Categoria>();
		}
		return categorias;
	}

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}

}
