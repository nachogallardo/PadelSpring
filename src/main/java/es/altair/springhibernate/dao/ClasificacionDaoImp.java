package es.altair.springhibernate.dao;

import org.hibernate.SessionFactory;

public class ClasificacionDaoImp implements ClasificacionDao {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
