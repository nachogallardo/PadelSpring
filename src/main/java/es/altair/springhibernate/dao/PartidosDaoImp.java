package es.altair.springhibernate.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@Override
	@Transactional
	public List<Partidos> listarPartidos() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Partidos> partidos= new ArrayList<Partidos>();
		partidos=sesion.createQuery("from Partidos").list();
		return partidos;
	}
	@Override
	@Transactional
	public void EditarGanadores(int idUsuario1, int idUsuario2,int idPartido) {
		Session sesion=sessionFactory.getCurrentSession();
		
		sesion.createSQLQuery("UPDATE partidos SET idGanador1=:j1,idGanador2=:j2  where idPartido=:id " )
					.setParameter("j1", idUsuario1)
					.setParameter("j2", idUsuario2)
					.setParameter("id", idPartido)
					.executeUpdate();	
	}
	@Override
	@Transactional
	public void EditarPartido(int idPartido,int idPista,Date fecha) {
		Session sesion=sessionFactory.getCurrentSession();

		sesion.createQuery("UPDATE Partidos SET idPista=:p,fechaPartido=:f  where idPartido=:id " )
					.setParameter("p", idPista)
					.setParameter("f", fecha)
					.setParameter("id", idPartido)
					.executeUpdate();	
	}

	@Override
	@Transactional
	public Partidos partidoPorNombreJugador(String nombre) {
		Session sesion=sessionFactory.getCurrentSession();
		Partidos p = new Partidos();
		p=(Partidos)sesion.createQuery("from Partidos where idJugador1=:i or idJugador2=:i or idJugador3=:i or idJugador4=:i").setParameter("i", nombre).uniqueResult();
		return p;
	}

	@Override
	@Transactional
	public Partidos partidoPorIdPartido(int idPartido) {
		Session sesion=sessionFactory.getCurrentSession();
		Partidos p = new Partidos();
		p=(Partidos)sesion.createQuery("from Partidos where idPartido=:i").setParameter("i", idPartido).uniqueResult();
		return p;
	}

	@Override
	@Transactional
	public List<Partidos> listarPartidosSinGanador() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Partidos> partidos= new ArrayList<Partidos>();
		partidos=sesion.createQuery("from Partidos where idGanador1 = 0").list();
		return partidos;
	}

	@Override
	@Transactional
	public int sacarNumeroJornada() {
		Session sesion=sessionFactory.getCurrentSession();
		int num=0;
		num=(Integer) sesion.createQuery("select max(numJornada) from Partidos").uniqueResult();
		return num;
	}

	@Override
	@Transactional
	public List<Partidos> listarPartidosConGanador() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Partidos> partidos= new ArrayList<Partidos>();
		partidos=sesion.createQuery("from Partidos where idGanador1 > 0").list();
		return partidos;
	}

	@Override
	@Transactional
	public List<Partidos> partidosPorIdpista(int id) {
		Session sesion=sessionFactory.getCurrentSession();
		List<Partidos> partidos= new ArrayList<Partidos>();
		partidos=sesion.createQuery("from Partidos where idPista = :p").setParameter("p", id).list();
		return partidos;
	}
	
}
