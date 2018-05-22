package es.altair.springhibernate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.altair.springhibernate.bean.Clasificacion;
import es.altair.springhibernate.bean.Torneo;

public class ClasificacionDaoImp implements ClasificacionDao {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void insertar(Clasificacion clasificacion) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.save(clasificacion);
		
	}

	@Override
	@Transactional
	public List<Clasificacion> listarClasificacion(Object attribute) {
		Session sesion=sessionFactory.getCurrentSession();
		List<Clasificacion> clasificacion = new ArrayList<Clasificacion>();
		Torneo t1 = (Torneo)attribute;
		 clasificacion=sesion.createQuery("from Clasificacion where torneo =:i").setParameter("i", t1).list();
		return clasificacion;
	}
	@Override
	@Transactional
	public void Editar(int puntos,int partJugados,int idTorneo,int idUsuario) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.createSQLQuery("UPDATE clasificacion SET puntos=:p, partJugados=:pj where idUsuario=:i and idTorneo=:t" )
					.setParameter("p", puntos)
					.setParameter("pj", partJugados)
					.setParameter("i", idUsuario)
					.setParameter("t", idTorneo)
					.executeUpdate();	
	}

	
}
