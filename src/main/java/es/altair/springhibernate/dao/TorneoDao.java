package es.altair.springhibernate.dao;

import java.util.List;

import es.altair.springhibernate.bean.Torneo;

public interface TorneoDao {

	boolean validarTorneo(Torneo torneo);

	int insertar(Torneo torneo);

	Torneo torneoPorNombre(String nombre);

	List<Torneo> listarTorneos();

}
