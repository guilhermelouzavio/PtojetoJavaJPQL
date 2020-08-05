package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.dao.CidadeDAO;
import br.com.fiap.entity.Cidade;

public class CidadeDAOImpl extends GenericDAOImpl<Cidade,Integer> implements CidadeDAO{

	public CidadeDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Cidade> buscarPorNumeroHabitantes(int numero) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("Select * from CIDADE where NR_HABITANTES > :n" , Cidade.class)
				.setParameter("n", numero).getResultList();
	}

}
