package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ReservaDAO;
import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Reserva;

public class ReservaDAOImpl extends GenericDAOImpl<Reserva,Integer> implements ReservaDAO {

	public ReservaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public List<Reserva> listar(){
		
		//Criar Query
		TypedQuery<Reserva> query = em.createQuery("from Reserva" , Reserva.class);
		
		//Excecutar Query
		return query.getResultList();
	}

	@Override
	public List<Reserva> buscarPorNumeroDias(int numero) {
		
		TypedQuery<Reserva> query = em.createQuery("from Reserva r where r.numeroDias = :dias " , Reserva.class);
		
		query.setParameter("dias", numero);	
		
		return query.getResultList();
	}

}
