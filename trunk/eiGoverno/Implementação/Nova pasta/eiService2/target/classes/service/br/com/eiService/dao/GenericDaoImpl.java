package br.com.eiService.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;

import br.com.eiService.infra.HibernateUtil;

public abstract class GenericDaoImpl <T, Type extends Serializable> implements GenericDAO<T, Type>{

	private Class<T> persistentClass;
	
	public GenericDaoImpl(Class persistentClass){
		super();
		this.persistentClass = persistentClass;
	}
	
	
	public void beginTransaction() {
		HibernateUtil.beginTransaction();
	}
	
	public void commitTransaction() {
		HibernateUtil.commitTransaction();
	}
	
	public void save(T entity) {
		HibernateUtil.getSession().save(entity);
	}
	
	public void delete(T entity) {
		HibernateUtil.getSession().delete(entity);
	}

	public List<T> listAll() {
		HibernateUtil.beginTransaction();
		Criteria criteria = HibernateUtil.getSession().createCriteria(persistentClass);
		return criteria.list();
	}
	
	public T load(Type id) {
		return (T) HibernateUtil.getSession().get(persistentClass, id);
	}
	
}
