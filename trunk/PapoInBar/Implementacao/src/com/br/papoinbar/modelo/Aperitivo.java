package com.br.papoinbar.modelo;

public class Aperitivo {
	private long id;
	private String nome;
	private double preco;
	private long bar;
	private int quantidade;
	
	public Aperitivo(Long id,String nome, double preco, long idBar)
	{
		this.setId(id);
		this.bar = idBar;
		this.preco = preco;
		this.nome = nome;
	}
	
	public Aperitivo(String nome, double preco, long bar)
	{
		this.bar = bar;
		this.preco = preco;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public long getBar() {
		return bar;
	}
	public void setBar(long bar) {
		this.bar = bar;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getValorAmigavel() {
		String formattedString = String.format("R$%8.2f %n X %d = %d", this.preco, this.quantidade, this.preco*this.quantidade); 
		return formattedString;

	
	}
}
