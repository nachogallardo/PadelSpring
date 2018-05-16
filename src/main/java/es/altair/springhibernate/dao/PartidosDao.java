package es.altair.springhibernate.dao;

import java.util.List;

import es.altair.springhibernate.bean.Partidos;

public interface PartidosDao {


	void insert(Partidos p);

	void borrarPartidos();

	List<Partidos> listarPartidos();

}
