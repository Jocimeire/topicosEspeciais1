package br.fepi.si.financeiro.app;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.fepi.si.financeiro.model.Lancamento;
import br.fepi.si.financeiro.model.Pessoa;
import br.fepi.si.financeiro.model.TipoLancamento;
import br.fepi.si.financeiro.repository.Lancamentos;
import br.fepi.si.financeiro.util.JpaUtil;

public class CriarLancamentoApp {

	private static EntityManager em;
	private static EntityTransaction et;

	public static void main(String[] args) {

		try {

			em = JpaUtil.getEntityManager();
			et = em.getTransaction();

			et.begin();

			Calendar dataVencimento1 = Calendar.getInstance();
			dataVencimento1.set(2013, 10, 1, 0, 0, 0);

			Calendar dataVencimento2 = Calendar.getInstance();
			dataVencimento2.set(2013, 12, 10, 0, 0, 0);

			Pessoa cliente = new Pessoa();
			cliente.setNome("MEIRE");

			Pessoa fornecedor = new Pessoa();
			fornecedor.setNome("Cachaca Store");

			Lancamento lancamento1 = new Lancamento();
			lancamento1.setDescricao("Venda de Cachaca");
			lancamento1.setPessoa(cliente);
			lancamento1.setDataVencimento(dataVencimento1.getTime());
			lancamento1.setDataPagamento(dataVencimento1.getTime());
			lancamento1.setValor(new BigDecimal(122_000));
			lancamento1.setTipoLancamento(TipoLancamento.RECEITA);

			Lancamento lancamento2 = new Lancamento();
			lancamento2.setDescricao("Venda de Coxinha");
			lancamento2.setPessoa(cliente);
			lancamento2.setDataVencimento(dataVencimento1.getTime());
			lancamento2.setDataPagamento(dataVencimento1.getTime());
			lancamento2.setValor(new BigDecimal(200_000));
			lancamento2.setTipoLancamento(TipoLancamento.RECEITA);

			Lancamento lancamento3 = new Lancamento();
			lancamento3.setDescricao("Venda de Cigarro");
			lancamento3.setPessoa(fornecedor);
			lancamento3.setDataVencimento(dataVencimento2.getTime());
			lancamento3.setValor(new BigDecimal(37_000));
			lancamento3.setTipoLancamento(TipoLancamento.DESPESA);

			em.persist(cliente);
			em.persist(fornecedor);
			em.persist(lancamento1);
			em.persist(lancamento2);
			em.persist(lancamento3);

			et.commit();
			
			Lancamentos l = new Lancamentos(em);
			System.out.println(l.obterLancamentos().get(0).getDescricao());
		} catch (Exception e) {
			System.out.println(e.getMessage().toUpperCase());
		} finally {
			em.close();
		}
	}
}
