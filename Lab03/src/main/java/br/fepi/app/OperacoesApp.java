package br.fepi.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.fepi.model.Carro;
import br.fepi.model.Cliente;
import br.fepi.model.Locacao;

public class OperacoesApp {

	public static void inserir(EntityManager em) throws ParseException {

		Cliente cliente1 = new Cliente("11111111111", "Silvio Santos");
		Cliente cliente2 = new Cliente("22211111111", "Fausto Silva");
		Cliente cliente3 = new Cliente("33311111111", "Raul Gil");

		Carro carro1 = new Carro("Fusca", "PXP4505");
		Carro carro2 = new Carro("Camaro", "CXF8005");
		Carro carro3 = new Carro("Opala", "PPX4185");

		Locacao locacao1 = new Locacao(cliente3, carro3, new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2020"), null);
		Locacao locacao2 = new Locacao(cliente1, carro2, new SimpleDateFormat("dd/MM/yyyy").parse("20/01/2020"),
				new SimpleDateFormat("dd/MM/yyyy").parse("27/01/2020"));
		Locacao locacao3 = new Locacao(cliente2, carro1, new SimpleDateFormat("dd/MM/yyyy").parse("10/03/2020"), null);

		em.persist(cliente1);
		em.persist(cliente2);
		em.persist(cliente3);

		em.persist(carro1);
		em.persist(carro2);
		em.persist(carro3);

		em.persist(locacao1);
		em.persist(locacao2);
		em.persist(locacao3);
	}

	public static void atualizar(EntityManager em) {
		Cliente cliente = em.find(Cliente.class, 3L);
		cliente.setCpfCliente("00000011154");
		em.merge(cliente);
	}

	public static void remover(EntityManager em) {
		em.remove(em.find(Cliente.class, 1L));
	}

	public static void buscaSingle(EntityManager em) {
		String parametro = "1111111111";

		Cliente cliente = em.createQuery("from Cliente c where c.cpfCliente = :cpf", Cliente.class)
				.setParameter("cpf", parametro).getSingleResult();

		System.out.println(cliente.getNomeCliente().toUpperCase());
	}

	public static void buscaList(EntityManager em) {
		TypedQuery<Locacao> locacoes = em.createQuery("from Locacao", Locacao.class);
		for (Locacao locacao : locacoes.getResultList()) {
			System.out.println(locacao.getCarro().getModeloCarro().toUpperCase() + " foi alugado em "
					+ new SimpleDateFormat("dd/MM/yyyy").format(locacao.getDataLocacao().getTime()) + " por "
					+ locacao.getCliente().getNomeCliente().toUpperCase());
		}
	}
	
	public static void carrosOrdenadosPelaPlaca(EntityManager em) {
		TypedQuery<Carro> carros = em.createQuery("FROM Carro c ORDER BY c.placaCarro", Carro.class);
		System.out.println("Carros ordenados pela placa:");
		for (Carro carro: carros.getResultList()) {
			System.out.println("> " + carro.getModeloCarro() + " - Placa: " + carro.getPlacaCarro());
		}
	}
	
	public static void primeiroNomeSilvio(EntityManager em) {
		TypedQuery<Cliente> clientes = em.createQuery("FROM Cliente c WHERE c.nomeCliente LIKE 'Silvio%'", Cliente.class);
		System.out.println("Todos que possuem o nome, onde o primeiro é Silvio:");
		for (Cliente cliente: clientes.getResultList()) {
			System.out.println("> CPF: "+ cliente.getCpfCliente() + " e nome: " + cliente.getNomeCliente());
		}
	}
	
	public static void portadorDosVariosUm(EntityManager em) {
		//Pode ser tambem com a clausula ON: SELECT c FROM Cliente c INNER JOIN Locacao l ON l.cliente = c.idCliente
		TypedQuery<Locacao> locacoes = em.createQuery("SELECT l FROM Locacao l INNER JOIN l.cliente c WHERE c.cpfCliente = '11111111111'", Locacao.class);
		System.out.println("Locações do cliente portador do cpf: 11111111111:");
		for (Locacao locacao: locacoes.getResultList()) {
			System.out.println("> CPF: " + locacao.getCliente().getCpfCliente() 
					+ ", nome " + locacao.getCliente().getNomeCliente().toUpperCase()
					+ " e placa do veiculo: " + locacao.getCarro().getPlacaCarro());
		}
	}
	
	public static void naoDevolveramCarro(EntityManager em) {
		TypedQuery<Locacao> locacoes = em.createQuery("SELECT l FROM Locacao l INNER JOIN l.cliente c WHERE l.dataDevolucao IS NULL", Locacao.class);
		System.out.println("Clientes que fizeram locação mas não devolveram:");
		for (Locacao locacao: locacoes.getResultList()) {
			System.out.println("> CPF: " + locacao.getCliente().getCpfCliente() 
					+ ", nome " + locacao.getCliente().getNomeCliente().toUpperCase()
					+ " e placa do veiculo: " + locacao.getCarro().getPlacaCarro());
		}
	}
	
	public static void locadosAoMenosUmaVez(EntityManager em) {
		TypedQuery<Locacao> locacoes = em.createQuery("SELECT l FROM Locacao l INNER JOIN l.carro c", Locacao.class);
		System.out.println("Clientes que fizeram locação mas não devolveram:");
		for (Locacao locacao: locacoes.getResultList()) {
			System.out.println("> Placa: " + locacao.getCarro().getPlacaCarro() 
					+ ", modelo " + locacao.getCarro().getModeloCarro());
		}
	}
	
	public static void qtdClientes(EntityManager em) {
		Long qtdClientes = (Long) em.createQuery("SELECT COUNT(*) FROM Cliente").getSingleResult();
		System.out.println("Há " + qtdClientes + " clientes");
	}
	
	public static void qtdPorClienteLocado(EntityManager em) {
		List<Object[]> results = em.createQuery(
				"SELECT c.cpfCliente, c.nomeCliente, COUNT(ca.placaCarro) FROM Locacao l INNER JOIN l.carro ca INNER JOIN l.cliente c GROUP BY c.cpfCliente, c.nomeCliente")
				.getResultList();
		for (Object[] objeto : results) {
			String cpf = (String) objeto[0];
			String nome = (String) objeto[1];
			int cont = ((Long) objeto[2]).intValue();
			System.out.println("CPF: " + cpf +" (" + nome + ") já locou " + cont + " carros");
		}
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			//et.begin();
			//inserir(em);
			//buscaUm(em);
			//buscaLista(em);
			//carrosOrdenadosPelaPlaca(em);
			//primeiroNomeSilvio(em);
			//portadorDosVariosUm(em);
			//naoDevolveramCarro(em);
			//locadosAoMenosUmaVez(em);
			//qtdClientes(em);
			//qtdPorClienteLocado(em);
			//atualizar(em);
			//remover(em);
			//et.commit();
		}
		catch (Exception e) {
			//et.rollback();
			System.out.println("Erro na operação. " + e.getMessage());
		}
		finally {
			em.close();
			emf.close();
		}

	}

}
