package es.altair.springhibernate.dao;

import java.util.List;

import es.altair.springhibernate.bean.Pagos;

public interface PagosDao {
	void insert(Pagos p);

	List<Pagos> listarPagos(int id);

	List<Pagos> listarTodosPagos();
}
