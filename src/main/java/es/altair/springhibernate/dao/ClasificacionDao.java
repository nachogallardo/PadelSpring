package es.altair.springhibernate.dao;

import java.util.List;

import es.altair.springhibernate.bean.Clasificacion;

public interface ClasificacionDao {

	void insertar(Clasificacion clasificacion);

	List<Clasificacion> listarClasificacion(Object attribute);



}
