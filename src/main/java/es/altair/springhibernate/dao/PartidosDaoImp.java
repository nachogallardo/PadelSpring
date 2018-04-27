package es.altair.springhibernate.dao;

import org.hibernate.SessionFactory;

public class PartidosDaoImp implements PartidosDao {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
