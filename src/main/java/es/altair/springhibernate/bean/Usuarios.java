package es.altair.springhibernate.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;
	private String nombre;
	private String email;
	private int tipoUsuario;
	private String contrasenia;
	private int telefono;
	
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private Set<Pagos> pagos;
	
	@OneToMany(mappedBy="idJugador1",cascade=CascadeType.ALL)
	private Set<Partidos> partidos1;
	
	@OneToMany(mappedBy="idJugador2",cascade=CascadeType.ALL)
	private Set<Partidos> partidos2;
	
	@OneToMany(mappedBy="idJugador3",cascade=CascadeType.ALL)
	private Set<Partidos> partidos3;
	
	@OneToMany(mappedBy="idJugador4",cascade=CascadeType.ALL)
	private Set<Partidos> partidos4;
	
	@OneToMany(mappedBy = "usuario")
	private Set<Clasificacion> clasificacion = new HashSet<Clasificacion>();
	
	public Usuarios() {
		super();
	}
	

	public Usuarios(String nombre, String email, int tipoUsuario, String contrasenia, int telefono,
			Clasificacion clasificacion) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
		this.contrasenia = contrasenia;
		this.telefono = telefono;
	}




	public Set<Pagos> getPagos() {
		return pagos;
	}


	public void setPagos(Set<Pagos> pagos) {
		this.pagos = pagos;
	}


	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	


	


	public Set<Partidos> getPartidos1() {
		return partidos1;
	}


	public void setPartidos1(Set<Partidos> partidos1) {
		this.partidos1 = partidos1;
	}


	public Set<Partidos> getPartidos2() {
		return partidos2;
	}


	public void setPartidos2(Set<Partidos> partidos2) {
		this.partidos2 = partidos2;
	}


	public Set<Partidos> getPartidos3() {
		return partidos3;
	}


	public void setPartidos3(Set<Partidos> partidos3) {
		this.partidos3 = partidos3;
	}


	public Set<Partidos> getPartidos4() {
		return partidos4;
	}


	public void setPartidos4(Set<Partidos> partidos4) {
		this.partidos4 = partidos4;
	}


	@Override
	public String toString() {
		return "Usuarios [idUsuario=" + idUsuario + ", nombre=" + nombre + ", contrasenia=" + contrasenia + ", email="
				+ email + ", telefono=" + telefono + ", tipoUsuario=" + tipoUsuario + "]";
	}
	
	
	
}
