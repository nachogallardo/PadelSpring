package es.altair.springhibernate.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="torneo")
public class Torneo implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTorneo;
	private String nombre;
	@OneToMany(mappedBy="torneo", cascade=CascadeType.ALL)
	private Set<Clasificacion> clasificacion;
	@OneToMany(mappedBy="idTorneo1",cascade=CascadeType.ALL)
	private Set<Partidos> partidos;
	
	public int getIdTorneo() {
		return idTorneo;
	}
	public void setIdTorneo(int idTorneo) {
		this.idTorneo = idTorneo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Set<Clasificacion> getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(Set<Clasificacion> clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	public Set<Partidos> getPartidos() {
		return partidos;
	}
	public void setPartidos(Set<Partidos> partidos) {
		this.partidos = partidos;
	}
	public Torneo(String nombre) {
		super();
		this.nombre = nombre;
	}
	
}
