package br.com.eiService.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.eiService.infra.HibernateUtil;
import br.com.eiService.model.Usuario;

public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Long> implements UsuarioDao{
	
	Session session = HibernateUtil.getSession();
	
	public UsuarioDaoImpl(){
		super(Usuario.class);
	}

	public Usuario buscarPorEmail(String email) {
		Criteria criteria = session.createCriteria(Usuario.class);
		Usuario user = (Usuario) criteria.add(Restrictions.eq("email", email)).uniqueResult();
		return user;
	}
	
	
}
