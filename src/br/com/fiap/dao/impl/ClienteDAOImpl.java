package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.entity.Cidade;
import br.com.fiap.entity.Cliente;

public class ClienteDAOImpl extends GenericDAOImpl<Cliente,Integer> implements ClienteDAO{

	public ClienteDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	//@Override
	//public List<Cliente> buscaCLiente() {
		//TypedQuery<Cliente> query = em.createQuery("from Cliente" , Cliente.class);
		//return query.getResultList();
	//}

	@Override
	public List<Cliente> buscarPorParteNome(String ParteNome) {
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.nome like :ParteNome" , Cliente.class);
		query.setParameter("ParteNome", "%" + ParteNome + "%");
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarClientePorEstado(String cid) {
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.endereco.cidade.uf = :cid", Cliente.class);
		query.setParameter("cid", cid);
 		return  query.getResultList();
	}

	@Override
	public List<Cliente> buscarPorNReservaCliente(int numero) {

		TypedQuery<Cliente> query = em.createQuery("Select r.cliente from Reserva r where r.numeroDias = :dias " , Cliente.class);
		
		query.setParameter("dias", numero);	
		
		return query.getResultList();
	}

	@Override
	public List<Cliente> bucarClienteComEndereco(String nome, Cidade cid) {
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.nome like :nome and "
				+ "c.endereco.cidade = :cid and :cid is not null " , Cliente.class);
		
		query.setParameter("nome", "%" + nome + "%");	
		query.setParameter("cid", cid);	
		
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarClientePorEstado(List<String> estados) {
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.endereco.cidade.uf in (:estados))", Cliente.class);
		
		query.setParameter("estados" , estados);
		
		return query.getResultList();
	}

	@Override
	public long buscarQtdClientePorEstado(String estado) {
		return em.createQuery("select count(c) from Cliente c where"
				+ " c.endereco.cidade.uf = :estado ", Long.class)
		.setParameter("estado", estado )
		.getSingleResult();
	}

	
}
