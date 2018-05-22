package es.altair.springhibernate.bean;

public class PagosString {
	private String nombre;
	private String fecha;
	public PagosString(String nombre, String fecha) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
