package es.altair.springhibernate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.altair.springhibernate.bean.Pagos;

public class PagosDaoImp implements PagosDao {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	@Transactional
	public void insert(Pagos p) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.save(p);
		
	}
	@Override
	@Transactional
	public List<Pagos> listarPagos(int id) {
		Session sesion=sessionFactory.getCurrentSession();
		List<Pagos> pagos=new ArrayList<Pagos>();
		pagos= sesion.createQuery("from Pagos where idJugador=:id").setParameter("id", id).list();
		return pagos;
	}
	@Override
	@Transactional
	public List<Pagos> listarTodosPagos() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Pagos> pagos=new ArrayList<Pagos>();
		pagos= sesion.createQuery("from Pagos").list();
		return pagos;
	}
}
