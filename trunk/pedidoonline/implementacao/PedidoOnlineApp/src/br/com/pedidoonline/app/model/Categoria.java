package br.com.pedidoonline.app.model;

import java.util.ArrayList;

public class Categoria extends AbstractEntity {
	
	
	private String nome;
	
	private byte[] imagem;
	
	private ArrayList<Item> itens;

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public ArrayList<Item> getItens() {
		if(itens == null) {
			itens = new ArrayList<Item>();
		}
		return itens;
	}

	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}

}
