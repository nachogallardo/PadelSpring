package es.altair.springhibernate.bean;

public class ClasificacionString {
	private String nombre;
	private String nombreTorneo;
	private int puntos;
	private int partJugados;
	
	public ClasificacionString(String nombre, String nombreTorneo, int puntos, int partJugados) {
		super();
		this.nombre = nombre;
		this.nombreTorneo = nombreTorneo;
		this.puntos = puntos;
		this.partJugados = partJugados;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreTorneo() {
		return nombreTorneo;
	}
	public void setNombreTorneo(String nombreTorneo) {
		this.nombreTorneo = nombreTorneo;
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
	
}
