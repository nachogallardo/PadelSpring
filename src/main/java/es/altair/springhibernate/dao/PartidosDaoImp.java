package es.altair.springhibernate.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.altair.springhibernate.bean.Partidos;

public class PartidosDaoImp implements PartidosDao {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void insert(Partidos p) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.save(p);
		
	}

	@Override
	@Transactional
	public void borrarPartidos() {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.createSQLQuery("delete from partidos").executeUpdate();
		
	}
}
