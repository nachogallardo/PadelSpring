package es.altair.springhibernate.dao;

import org.hibernate.SessionFactory;

public class PagosDaoImp implements PagosDao {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
