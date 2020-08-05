package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Cidade;
import br.com.fiap.entity.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente,Integer>{
	
	//List<Cliente> buscaCLiente();
	
	List<Cliente> buscarPorParteNome(String ParteNome);
	
	List<Cliente> buscarClientePorEstado(String cid);
	
	List<Cliente> buscarPorNReservaCliente(int numero);
	
	List<Cliente> bucarClienteComEndereco(String nome , Cidade cid);
	
	List<Cliente> buscarClientePorEstado(List<String> estados);
	
	long buscarQtdClientePorEstado(String estado);
	
	
	
	
}
