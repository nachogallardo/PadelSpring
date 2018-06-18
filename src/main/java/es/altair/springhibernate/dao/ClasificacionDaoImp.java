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
import es.altair.springhibernate.bean.Usuarios;

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
		 clasificacion=sesion.createQuery("from Clasificacion where torneo =:i order by puntos desc").setParameter("i", t1).list();
		return clasificacion;
	}
	@Override
	@Transactional
	public void Editar(int puntos,int partJugados,int idTorneo,int idUsuario) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.createSQLQuery("UPDATE clasificacion SET puntos=:p where idUsuario=:i and idTorneo=:t" )
					.setParameter("p", puntos)
					.setParameter("i", idUsuario)
					.setParameter("t", idTorneo)
					.executeUpdate();	
	}

	@Override
	@Transactional
	public Clasificacion ClasificacionPorIdUsuarioyIdTorneo(int idGanador1, int idTorneo) {
		Session sesion=sessionFactory.getCurrentSession();
		Clasificacion c = new Clasificacion();
		c=(Clasificacion)sesion.createQuery("from Clasificacion where idTorneo=:i and idUsuario=:u").setParameter("i", idTorneo).setParameter("u", idGanador1).uniqueResult();
		return c;
	}

	@Override
	@Transactional
	public void SumarPartidoJugado(Usuarios idJugador1, Usuarios idJugador2, Usuarios idJugador3, Usuarios idJugador4,Torneo t) {
		Session sesion=sessionFactory.getCurrentSession();
		System.out.println(idJugador1.getIdUsuario());
		int partJugados=0;
		partJugados = (Integer) sesion.createSQLQuery("select partJugados from clasificacion where idUsuario=:i and idTorneo=:t").setParameter("i",idJugador1.getIdUsuario() ).setParameter("t", t.getIdTorneo()).uniqueResult();
		partJugados++;
		System.out.println(partJugados);
		sesion.createSQLQuery("UPDATE clasificacion SET partJugados=:p where idUsuario=:i and idTorneo=:t" )
					.setParameter("p", partJugados)
					.setParameter("t", t.getIdTorneo())
					.setParameter("i", idJugador1.getIdUsuario())
					.executeUpdate();
		partJugados = (Integer) sesion.createSQLQuery("select partJugados from clasificacion where idUsuario=:i and idTorneo=:t").setParameter("i",idJugador2.getIdUsuario() ).setParameter("t", t.getIdTorneo()).uniqueResult();
		partJugados++;
		sesion.createSQLQuery("UPDATE clasificacion SET partJugados=:p where idUsuario=:i and idTorneo=:t" )
					.setParameter("p", partJugados)
					.setParameter("t", t.getIdTorneo())
					.setParameter("i", idJugador2.getIdUsuario())
					.executeUpdate();
		partJugados = (Integer) sesion.createSQLQuery("select partJugados from clasificacion where idUsuario=:i and idTorneo=:t").setParameter("i",idJugador3.getIdUsuario() ).setParameter("t", t.getIdTorneo()).uniqueResult();
		partJugados++;
		sesion.createSQLQuery("UPDATE clasificacion SET partJugados=:p where idUsuario=:i and idTorneo=:t" )
					.setParameter("p", partJugados)
					.setParameter("t", t.getIdTorneo())
					.setParameter("i", idJugador3.getIdUsuario())
					.executeUpdate();
		partJugados = (Integer) sesion.createSQLQuery("select partJugados from clasificacion where idUsuario=:i and idTorneo=:t").setParameter("i",idJugador4.getIdUsuario() ).setParameter("t", t.getIdTorneo()).uniqueResult();
		partJugados++;
		sesion.createSQLQuery("UPDATE clasificacion SET partJugados=:p where idUsuario=:i and idTorneo=:t" )
					.setParameter("p", partJugados)
					.setParameter("t", t.getIdTorneo())
					.setParameter("i", idJugador4.getIdUsuario())
					.executeUpdate();
		
	}

	
}
