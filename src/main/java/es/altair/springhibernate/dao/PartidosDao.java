package es.altair.springhibernate.dao;

import java.util.Date;
import java.util.List;

import es.altair.springhibernate.bean.Partidos;

public interface PartidosDao {


	void insert(Partidos p);

	void borrarPartidos();

	List<Partidos> listarPartidos();

	void EditarGanadores(int idUsuario1, int idUsuario2,int idPartido);
	
	void EditarPartido(int idPartido,int idPista,Date fecha);
	
	Partidos partidoPorNombreJugador(String nombre);

	Partidos partidoPorIdPartido(int idPartido);
}
