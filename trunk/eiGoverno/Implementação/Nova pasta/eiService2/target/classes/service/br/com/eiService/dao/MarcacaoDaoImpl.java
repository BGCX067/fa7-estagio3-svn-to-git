package br.com.eiService.dao;

import br.com.eiService.model.Marcacao;

public class MarcacaoDaoImpl  extends GenericDaoImpl<Marcacao, Long> implements MarcacaoDao{
	
	public MarcacaoDaoImpl(){
		super(Marcacao.class);
	}
}
