package es.altair.springhibernate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import es.altair.springhibernate.bean.Usuarios;


import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UsuariosDaoImp implements UsuariosDao {
	private String pass = "Libros123$%";
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	@Transactional
	public boolean validarUsuario(Usuarios usu) {
		boolean correcto = false;
		Usuarios usuario=null;

		Session sesion=sessionFactory.getCurrentSession();

		usuario=(Usuarios) sesion.createQuery("SELECT u FROM Usuarios u WHERE nombre=:n")
					.setParameter("n", usu.getNombre())
					.uniqueResult();

		if(usuario!=null)
			correcto=true;
		return correcto;
	}


	@Override
	@Transactional
	public Usuarios comprobarUsuario(String usuario, String password) {
		Usuarios usu = null;

		// SessionFactory sf = new Configuration().configure().buildSessionFactory();
		// Session sesion = sf.openSession();

		Session sesion=sessionFactory.getCurrentSession();

		usu = (Usuarios) sesion
					.createQuery(
							"SELECT u FROM Usuarios u WHERE nombre=:n " + "AND contrasenia=AES_ENCRYPT(:p, :passphrase)")
					.setParameter("n", usuario).setParameter("p", password).setParameter("passphrase", pass)
					.uniqueResult();

		return usu;
	}

	@Override
	@Transactional
	public void Editar(int idUsuario, String nombre, String email, int telefono, int tipo) {
		Session sesion=sessionFactory.getCurrentSession();

		sesion.createQuery("UPDATE Usuarios SET nombre=:n, email=:e, telefono=:telefono, tipoUsuario=:tipo where idUsuario=:i " )
					.setParameter("n", nombre)
					.setParameter("e", email)
					.setParameter("telefono", telefono)

					.setParameter("tipo", tipo)
					.setParameter("i", idUsuario)
					.executeUpdate();	
	}

	@Override
	@Transactional
	public List<Usuarios> listarUsuarios() {
		List<Usuarios> usuarios= new ArrayList<Usuarios>();
		Session sesion=sessionFactory.getCurrentSession();

		usuarios= sesion.createQuery("from Usuarios").list();
		return usuarios;
	}

	@Override
	@Transactional
	public void borrarUsuario(int idUsuario) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.createQuery("delete from Usuarios where idUsuario=:i").setParameter("i", idUsuario).executeUpdate();		
	}

	@Override
	@Transactional
	public Usuarios usuarioPorId(int idUsuario) {
		Usuarios usu= new Usuarios();
		Session sesion=sessionFactory.getCurrentSession();
		usu=(Usuarios)sesion.createQuery("from Usuarios where idUsuario=:i").setParameter("i", idUsuario).uniqueResult();

		return usu;
	}

	@Override
	@Transactional
	public Usuarios usuarioLoginId(int idUsuario) {
		Usuarios usu= new Usuarios();
		Session sesion=sessionFactory.getCurrentSession();
		usu=(Usuarios)sesion.createQuery("from Usuarios where idUsuario=:i").setParameter("i", idUsuario).uniqueResult();
		usu.setContrasenia("");
		return usu;
	}
	@Override
	@Transactional
	public int insertar(Usuarios usu) {
		int filas = 0;
		usu.setTipoUsuario(2);
		Session sesion=sessionFactory.getCurrentSession();

		filas = sesion.createSQLQuery("INSERT INTO usuarios (nombre, email, tipoUsuario, contrasenia, telefono)"
							+ "values (:n, :e,:tipo, AES_ENCRYPT(:p, :passphrase), :t)")
					.setParameter("n", usu.getNombre()).setParameter("p", usu.getContrasenia())
					.setParameter("passphrase", pass).setParameter("e", usu.getEmail())
					.setParameter("t", usu.getTelefono()).setParameter("tipo", usu.getTipoUsuario()).executeUpdate();
		return filas;
	}
	@Override
	@Transactional
	public List<Usuarios> listarUsuariosReserva() {
		List<Usuarios> usuarios= new ArrayList<Usuarios>();
		Session sesion=sessionFactory.getCurrentSession();

		usuarios= sesion.createQuery("from Usuarios where tipoUsuario=2").list();
		return usuarios;
	}
}
