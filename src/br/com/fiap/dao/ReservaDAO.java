package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Reserva;

public interface ReservaDAO extends GenericDAO<Reserva,Integer>{
	
	List<Reserva> listar();
	
	List<Reserva> buscarPorNumeroDias(int numero);
	
	
}
