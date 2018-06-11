package es.altair.springhibernate.dao;

import java.util.List;

import es.altair.springhibernate.bean.Clasificacion;
import es.altair.springhibernate.bean.Torneo;
import es.altair.springhibernate.bean.Usuarios;

public interface ClasificacionDao {

	void insertar(Clasificacion clasificacion);

	List<Clasificacion> listarClasificacion(Object attribute);
	void Editar(int puntos,int partJugados,int idTorneo,int idUsuario);

	Clasificacion ClasificacionPorIdUsuarioyIdTorneo(int idGanador1, int idTorneo);

	void SumarPartidoJugado(Usuarios idJugador1, Usuarios idJugador2, Usuarios idJugador3, Usuarios idJugador4,Torneo t);


}
