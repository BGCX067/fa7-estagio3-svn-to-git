package br.com.eiService.infra;

import br.com.eiService.dao.MarcacaoDao;
import br.com.eiService.dao.UsuarioDao;


public abstract class DAOFactory {

	private static final Class FACTORY_CLASS = HibernateDAOFactory.class;

	public static DAOFactory getFactory() {

		try {
			return (DAOFactory) FACTORY_CLASS.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException();
		} catch (IllegalAccessException e) {
			throw new RuntimeException();
		}
	}

	public abstract UsuarioDao getUsuariDAO();
	
	
	public abstract MarcacaoDao getMarcacaoDAO();
}
