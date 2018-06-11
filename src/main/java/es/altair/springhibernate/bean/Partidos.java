package es.altair.springhibernate.bean;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.helpers.DateTimeDateFormat;
@Entity
@Table(name="partidos")
public class Partidos implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPartido;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPartido;
	private int numJornada;
	@ManyToOne
	@JoinColumn(name="idPista")
	private Pistas pista;
	@ManyToOne
	@JoinColumn(name="idJugador1")
	private Usuarios idJugador1;
	@ManyToOne
	@JoinColumn(name="idJugador2")
	private Usuarios idJugador2;
	@ManyToOne
	@JoinColumn(name="idJugador3")
	private Usuarios idJugador3;
	@ManyToOne
	@JoinColumn(name="idJugador4")
	private Usuarios idJugador4;	
	private int idGanador1;
	private int idGanador2;
	@ManyToOne
	@JoinColumn(name="idTorneo1")
	private Torneo idTorneo1;
	
	
	
	
	
	
	
	public Partidos(Date fechaPartido, int numJornada, Pistas pista, Usuarios idJugador1, Usuarios idJugador2,
			Usuarios idJugador3, Usuarios idJugador4, int idGanador1, int idGanador2, Torneo idTorneo1) {
		super();
		this.fechaPartido = fechaPartido;
		this.numJornada = numJornada;
		this.pista = pista;
		this.idJugador1 = idJugador1;
		this.idJugador2 = idJugador2;
		this.idJugador3 = idJugador3;
		this.idJugador4 = idJugador4;
		this.idGanador1 = 0;
		this.idGanador2 = 0;
		this.idTorneo1 = idTorneo1;
	}

	public Partidos() {
		super();
	}

	

	public Usuarios getIdJugador1() {
		return idJugador1;
	}
	public void setIdJugador1(Usuarios idJugador1) {
		this.idJugador1 = idJugador1;
	}
	public Usuarios getIdJugador2() {
		return idJugador2;
	}
	public void setIdJugador2(Usuarios idJugador2) {
		this.idJugador2 = idJugador2;
	}
	public Usuarios getIdJugador3() {
		return idJugador3;
	}
	public void setIdJugador3(Usuarios idJugador3) {
		this.idJugador3 = idJugador3;
	}
	public Usuarios getIdJugador4() {
		return idJugador4;
	}
	public void setIdJugador4(Usuarios idJugador4) {
		this.idJugador4 = idJugador4;
	}
	public int getIdGanador1() {
		return idGanador1;
	}
	public void setIdGanador1(int idGanador1) {
		this.idGanador1 = idGanador1;
	}
	public int getIdGanador2() {
		return idGanador2;
	}
	public void setIdGanador2(int idGanador2) {
		this.idGanador2 = idGanador2;
	}
	
	public Torneo getIdTorneo1() {
		return idTorneo1;
	}
	public void setIdTorneo1(Torneo idTorneo1) {
		this.idTorneo1 = idTorneo1;
	}
	public int getIdPartido() {
		return idPartido;
	}
	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}
	public Date getFechaPartido() {
		return fechaPartido;
	}
	public void setFechaPartido(Date fechaPartido) {
		this.fechaPartido = fechaPartido;
	}
	public int getNumJornada() {
		return numJornada;
	}
	public void setNumJornada(int numJornada) {
		this.numJornada = numJornada;
	}
	public Pistas getPista() {
		return pista;
	}
	public void setPista(Pistas pista) {
		this.pista = pista;
	}
	

}
