package es.altair.springhibernate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.altair.springhibernate.bean.Pistas;
import es.altair.springhibernate.bean.Usuarios;

public class PistasDaoImp implements PistasDao {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Pistas> listarPistas() {
		List<Pistas> pistas= new ArrayList<Pistas>();
		Session sesion=sessionFactory.getCurrentSession();

		pistas= sesion.createQuery("from Pistas").list();
		return pistas;
	}

	@Override
	@Transactional
	public boolean validarPista(Pistas pista) {
		boolean correcto = false;
		Pistas pistas=null;

		Session sesion=sessionFactory.getCurrentSession();

		pistas=(Pistas) sesion.createQuery("SELECT p FROM Pistas p WHERE nombre=:n")
					.setParameter("n", pista.getNombre())
					.uniqueResult();

		if(pistas!=null)
			correcto=true;
		return correcto;
	}
	

	@Override
	@Transactional
	public int insertar(Pistas pista) {
		int filas = 0;
		Session sesion=sessionFactory.getCurrentSession();

		filas = sesion.createSQLQuery("INSERT INTO pistas (nombre)"
							+ "values (:n)")
					.setParameter("n", pista.getNombre()).executeUpdate();
		return filas;
	}

	@Override
	@Transactional
	public void borrarPista(int id) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.createQuery("delete from Pistas where idPista=:i").setParameter("i", id).executeUpdate();		
		
	}

	@Override
	@Transactional
	public String pistaPorId(int parseInt) {
		Session sesion=sessionFactory.getCurrentSession();
		String nombre="";
		nombre=(String) sesion.createQuery("select nombre from Pistas where idPista=:i").setParameter("i", parseInt).uniqueResult();
		
		return nombre;
	}
}
