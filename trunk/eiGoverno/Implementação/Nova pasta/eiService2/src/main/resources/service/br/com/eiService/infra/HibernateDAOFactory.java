package br.com.eiService.infra;

import br.com.eiService.dao.MarcacaoDaoImpl;
import br.com.eiService.dao.UsuarioDaoImpl;
import br.com.eiService.dao.MarcacaoDao;
import br.com.eiService.dao.UsuarioDao;

public class HibernateDAOFactory extends DAOFactory{

	@Override
	public UsuarioDao getUsuariDAO() {
		return new UsuarioDaoImpl();
	}

	@Override
	public MarcacaoDao getMarcacaoDAO() {
		return new MarcacaoDaoImpl();
	}


	
}
