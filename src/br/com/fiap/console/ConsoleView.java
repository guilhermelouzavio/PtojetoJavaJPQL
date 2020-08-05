package br.com.fiap.console;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.CidadeDAO;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.PacoteDAO;
import br.com.fiap.dao.ReservaDAO;
import br.com.fiap.dao.TransporteDAO;
import br.com.fiap.dao.impl.CidadeDAOImpl;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.dao.impl.PacoteDAOImpl;
import br.com.fiap.dao.impl.ReservaDAOImpl;
import br.com.fiap.dao.impl.TransporteDAOImpl;
import br.com.fiap.entity.Cidade;
import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pacote;
import br.com.fiap.entity.Reserva;
import br.com.fiap.entity.Transporte;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class ConsoleView {
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		
		EntityManager em = fabrica.createEntityManager();
		
		ReservaDAO dao = new ReservaDAOImpl(em);
		
		//Chamar o metodo listar
		List<Reserva> lista = dao.listar();	
		
		//exibir as reservas
		for(Reserva item : lista) {
			System.out.println(item.getCliente().getNome() + " " + item.getNumeroDias());
		}
		
		//Chamar o metodo que pesquisa a reserva por numero de dias
		lista = dao.buscarPorNumeroDias(8);
		
		System.out.println("Buscar por numero de dias");
		
		//exibir as reservas
		for(Reserva item : lista) {
			System.out.println(item.getCliente().getNome() + " " + item.getNumeroDias());
		}
		
		//Instanciar DAO Cliente
		ClienteDAO dao1 = new ClienteDAOImpl(em);
		
		//Chamar o metodo listar
		List<Cliente> listaCliente = dao1.listar();	
		
		System.out.println("Listando todos os clientes");
		//listar todos os clientes
		for(Cliente item : listaCliente) {
			System.out.println(item.getNome());
		}
		
		System.out.println("Buscar Por numero de Reserva");
		
		listaCliente = dao1.buscarPorNReservaCliente(10);
		
		//exibir as reservas por cliente
		
		for(Cliente item : listaCliente) {
			System.out.println(item.getNome());
		}
		
		System.out.println("Buscar Por Parte do Nome");
		
		//Listar clientes por parte do nome
		listaCliente = dao1.buscarPorParteNome("J");
		
		//exibir clientes por nome
		for(Cliente item : listaCliente) {
			System.out.println(item.getNome());
		}
		
		//listando quantidade de cliente por estado
		System.out.println("CONTAR POR ESTADO -> " + dao1.buscarQtdClientePorEstado("SP"));
		
		//Listar clientes por parte do nome
		listaCliente = dao1.buscarClientePorEstado("SP");
				
		System.out.println("Listando clientes por Estado");
		//exibir clientes por nome
		for(Cliente item : listaCliente) {
			System.out.println(item.getNome() + " " + item.getEndereco().getCidade().getUf());
		}
		
		//Instanciar dao Cidade
		CidadeDAO daoCid =  new CidadeDAOImpl(em);
		//Pesquisar por uma Cidade
		Cidade cid = daoCid.pesquisar(1);
		
		//Chamar o metodo listar
		List<Cidade> listaCidade = daoCid.listar();	
		
		listaCliente = dao1.bucarClienteComEndereco("J", cid);
		System.out.println("Listando Clientes por CIDADE");
		//exibir clientes por cidade
				for(Cliente item : listaCliente) {
					System.out.println(item.getNome() + " " + item.getEndereco().getCidade().getNome());
				}
				
		//buscar por numero de habitantes com native Query
		System.out.println("POR NUMERO DE HABITANTES ");
		
		//pesquisar por numero de habitantes
		listaCidade = daoCid.buscarPorNumeroHabitantes(100);
		
		//exibir
		for(Cidade item : listaCidade) {
			System.out.println("POR NUMERO DE HABITANTES " + item.getNome() );
		}
		//exibir com forEach
		 daoCid.buscarPorNumeroHabitantes(100).forEach(c -> 
		 System.out.println("POR NUMERO DE HABITANTES 2 " + c.getNome() + " " + c.getNrHabitantes()));
		
		
		
		//Instanciar Pacote DAO
		PacoteDAO dao2 = new PacoteDAOImpl(em);
		
		//Instanciar Trasnporte DAO
		TransporteDAO tDat = new TransporteDAOImpl(em);
		
		//Instanciar um objeto de Transporte
		Transporte trans  = tDat.pesquisar(1);
		
		//Chamar o metodo listar
		List<Pacote> listaPacote = dao2.buscarPorTransporte(trans);	
		
		System.out.println("Listando Pacotes por Transporte");
		//exibir pacotes por transporte
		for(Pacote item : listaPacote) {
			System.out.println(item.getDescricao());
		}
		
		
		
		//chamar pacotes por data
		listaPacote = dao2.buscarPorDatas( new GregorianCalendar(1999 , 1 , 10),Calendar.getInstance());
		
		System.out.println("Listando PACOTE POR DATA COM PARAMETRO");
		//exibir pacotes por data
				for(Pacote item : listaPacote) {
					System.out.println(item.getDescricao());
				}
				
		
		System.out.println("BUSCAR POR PRECO MENOR");
		dao2.buscarPorPrecoMenor(10000).forEach(p -> System.out.println(p.getDescricao() + " " + p.getPreco()));
		
		System.out.println("Media dos PRECOS " + dao2.buscarPorPrecoMedio());
		
		
		//fechar
		em.close();
		fabrica.close();
		
	}
}
