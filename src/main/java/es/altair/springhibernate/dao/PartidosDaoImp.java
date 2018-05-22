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

		sesion.createSQLQuery("UPDATE Partidos SET idGanador1=:j1,idGanador2=:j2  where idPartido=:id " )
					.setParameter("j1", idUsuario1)
					.setParameter("j2", idUsuario2)
					.setParameter("id", idPartido)
					.executeUpdate();	
	}
	@Override
	@Transactional
	public void EditarPartido(int idPartido,int idPista,Date fecha) {
		Session sesion=sessionFactory.getCurrentSession();

		sesion.createSQLQuery("UPDATE Partidos SET idPista=:p,fechaPartido=:f  where idPartido=:id " )
					.setParameter("p", idPista)
					.setParameter("f", fecha)
					.setParameter("id", idPartido)
					.executeUpdate();	
	}
	
}
