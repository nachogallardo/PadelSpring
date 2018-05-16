package es.altair.springhibernate.bean;

import java.util.Date;

public class PartidoString {
	private String jug1;
	private String jug2;
	private String jug3;
	private String jug4;
	private int anio;
	private int mes;
	private int dia;
	private int hora;
	private int minutos;
    private String pista;
    private int numJornada;
    
    
    
    
    
	public PartidoString(String jug1, String jug2, String jug3, String jug4, int anio, int mes, int dia, int hora,
			int minutos, String pista, int numJornada) {
		super();
		this.jug1 = jug1;
		this.jug2 = jug2;
		this.jug3 = jug3;
		this.jug4 = jug4;
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
		this.hora = hora;
		this.minutos = minutos;
		this.pista = pista;
		this.numJornada = numJornada;
	}
	public String getJug1() {
		return jug1;
	}
	public void setJug1(String jug1) {
		this.jug1 = jug1;
	}
	public String getJug2() {
		return jug2;
	}
	public void setJug2(String jug2) {
		this.jug2 = jug2;
	}
	public String getJug3() {
		return jug3;
	}
	public void setJug3(String jug3) {
		this.jug3 = jug3;
	}
	public String getJug4() {
		return jug4;
	}
	public void setJug4(String jug4) {
		this.jug4 = jug4;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	public int getMinutos() {
		return minutos;
	}
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	public String getPista() {
		return pista;
	}
	public void setPista(String pista) {
		this.pista = pista;
	}
	public int getNumJornada() {
		return numJornada;
	}
	public void setNumJornada(int numJornada) {
		this.numJornada = numJornada;
	}
	
    
    
    
    
	
	
    
    
	
}
