package es.altair.springhibernate.dao;

import java.util.List;

import es.altair.springhibernate.bean.Pistas;

public interface PistasDao {

	List<Pistas> listarPistas();

	boolean validarPista(Pistas pista);

	int insertar(Pistas pista);

	void borrarPista(int id);

	String pistaPorId(int parseInt);



	

}
