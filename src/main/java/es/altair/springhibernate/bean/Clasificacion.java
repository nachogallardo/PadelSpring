package es.altair.springhibernate.bean;

import java.io.Serializable;
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
@Table(name="clasificacion")
public class Clasificacion implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idClasificacion;
	private int puntos;
	private int partJugados;
	@ManyToOne
	@JoinColumn(name="idTorneo")
	private Torneo torneo;
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuarios usuario;
	
	
	
	
	
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	public int getIdClasificacion() {
		return idClasificacion;
	}
	public void setIdClasificacion(int idClasificacion) {
		this.idClasificacion = idClasificacion;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getPartJugados() {
		return partJugados;
	}
	public void setPartJugados(int partJugados) {
		this.partJugados = partJugados;
	}
	public Torneo getTorneo() {
		return torneo;
	}
	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}
	public Clasificacion(int puntos, int partJugados, Torneo torneo, Usuarios usuario) {
		super();
		this.puntos = puntos;
		this.partJugados = partJugados;
		this.torneo = torneo;
		this.usuario = usuario;
	}
	
	
}
