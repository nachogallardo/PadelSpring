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
@Table(name="pistas")
public class Pistas implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPista;
	private String nombre;
	@OneToMany(mappedBy="pista",cascade=CascadeType.ALL)
	private Set<Partidos> partidos;
	
	public int getIdPista() {
		return idPista;
	}
	public void setIdPista(int idPista) {
		this.idPista = idPista;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Set<Partidos> getPartidos() {
		return partidos;
	}
	public void setPartidos(Set<Partidos> partidos) {
		this.partidos = partidos;
	}
	public Pistas(String nombre) {
		super();
		this.nombre = nombre;
	}
	public Pistas() {
		super();
	}
	
	
}
