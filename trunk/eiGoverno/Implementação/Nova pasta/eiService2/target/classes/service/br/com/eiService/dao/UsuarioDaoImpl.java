package br.com.eiService.dao;

import br.com.eiService.model.Usuario;

public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Long> implements UsuarioDao{

	public UsuarioDaoImpl(){
		super(Usuario.class);
	}

}
