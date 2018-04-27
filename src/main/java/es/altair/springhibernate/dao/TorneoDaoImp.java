package es.altair.springhibernate.dao;

import org.hibernate.SessionFactory;

public class TorneoDaoImp implements TorneoDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
