package es.altair.springhibernate.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="pagos")
public class Pagos implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPago;
	@Temporal(TemporalType.DATE)
	private Date ultimoPago;
	@ManyToOne
	@JoinColumn(name="idJugador")
	private Usuarios usuario;
	public Pagos(Date ultimoPago, Usuarios usuario) {
		super();
		this.ultimoPago = ultimoPago;
		this.usuario = usuario;
	}
	
	public Pagos(int idPago, Date ultimoPago, Usuarios usuario) {
		super();
		this.idPago = idPago;
		this.ultimoPago = ultimoPago;
		this.usuario = usuario;
	}

	
	public Pagos() {
		super();
	}

	public int getIdPago() {
		return idPago;
	}
	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}
	public Date getUltimoPago() {
		return ultimoPago;
	}
	public void setUltimoPago(Date ultimoPago) {
		this.ultimoPago = ultimoPago;
	}
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	
}
