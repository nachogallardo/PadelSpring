package es.altair.springhibernate.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.altair.springhibernate.bean.Pistas;
import es.altair.springhibernate.bean.Torneo;
import es.altair.springhibernate.bean.Usuarios;

public class TorneoDaoImp implements TorneoDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public boolean validarTorneo(Torneo torneo) {
		boolean correcto = false;
		Torneo torneos=null;

		Session sesion=sessionFactory.getCurrentSession();

		torneos=(Torneo) sesion.createQuery("SELECT p FROM Torneo p WHERE nombre=:n")
					.setParameter("n", torneo.getNombre())
					.uniqueResult();

		if(torneos!=null)
			correcto=true;
		return correcto;
	}

	@Override
	@Transactional
	public int insertar(Torneo torneo) {
		int filas = 0;
		Session sesion=sessionFactory.getCurrentSession();

		filas = sesion.createSQLQuery("INSERT INTO torneo (nombre)"
							+ "values (:n)")
					.setParameter("n", torneo.getNombre()).executeUpdate();
		return filas;
	}

	@Override
	@Transactional
	public Torneo torneoPorNombre(String nombre) {
		Torneo t= new Torneo();
		Session sesion=sessionFactory.getCurrentSession();
		t=(Torneo)sesion.createQuery("from Torneo where nombre=:i").setParameter("i", nombre).uniqueResult();

		return t;
	}

	@Override
	@Transactional
	public List<Torneo> listarTorneos() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Torneo> torneos= sesion.createQuery("from Torneo").list();
		return torneos;
	}

	@Override
	@Transactional
	public Torneo torneoPorId(int id) {
		Session sesion=sessionFactory.getCurrentSession();
		Torneo t1=new Torneo();
		t1= (Torneo)sesion.createQuery("from Torneo where idTorneo=:i").setParameter("i", id).uniqueResult();
		return t1;
	}
}
