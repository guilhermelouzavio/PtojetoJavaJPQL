package br.com.fiap.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.PacoteDAO;
import br.com.fiap.entity.Pacote;
import br.com.fiap.entity.Transporte;

public class PacoteDAOImpl extends GenericDAOImpl<Pacote,Integer> implements PacoteDAO{

	public PacoteDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Pacote> buscarPorTransporte(Transporte p) {
		TypedQuery<Pacote> query = em.createQuery("from Pacote p where p.transporte = :p" , Pacote.class);
		query.setParameter("p", p);
		return query.getResultList();
	}

	@Override
	public List<Pacote> buscarPorDatas(Calendar inicio, Calendar fim) {
		TypedQuery<Pacote> query = em.createQuery("from Pacote where dataSaida between :inicio and :fim " , Pacote.class);
		query.setParameter("inicio", inicio);
		query.setParameter("fim", fim);	
		return query.getResultList();
	}

	@Override
	public List<Pacote> buscarPorPrecoMenor(float preco) {
		return em.createNamedQuery("Pacote.porPrecoMenor" , Pacote.class)
				.setParameter("p", preco)
				.getResultList();
	}

	@Override
	public double buscarPorPrecoMedio() {
		return em.createNamedQuery("Pacote.mediaPreco", Double.class).getSingleResult();
	}

}
