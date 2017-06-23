package layout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.*;

public class Main {

	public static void main(String[] args) {
//		EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();
//		em.getTransaction().begin();
//
//		
//		Pessoa emitente = new Pessoa();
//		emitente.setCnpjCpf("10388767618");
//		emitente.setEstado("MG");
//		emitente.setInscricaoEstadual("131131");
//		emitente.setRazaoSocial("Matheus");
////		em.persist(emitente);
////		em.flush();
//		
//		Pessoa destinatario = new Pessoa();
//		destinatario.setCnpjCpf("88103301000112");
//		destinatario.setEstado("MG");
//		destinatario.setInscricaoEstadual("543345");
//		destinatario.setRazaoSocial("Empresa");
////		em.persist(destinatario);
////		em.flush();
////		
//		NotaFiscal nf = new NotaFiscal();
//		nf.setDataEmissao(new Date());
//		nf.setDataOperacao(new Date());
//		nf.setEmitente(emitente);
//		nf.setDestinatario(destinatario);
//		nf.setInformacoes("Nada a declarar");
//		
//		
//		List<Item> itens = new ArrayList<>();
//		Item item = new Item();
//		item.setCodigo(333);
//		item.setDescricao("Nada a declarar");
//		item.setQuant(3);
//		item.setValor(500.03);
//		itens.add(item);
//		
//		
//		nf.setItens(itens);
//		nf.setNotaFiscalNumero(1400);
//		em.persist(nf);
//
//		
//		em.getTransaction().commit();
//		em.close();
		
		EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
		TypedQuery<Object[]> query = em.createQuery("select count(*) as quant, n.emitente.estado from Nf n GROUP by n.emitente.estado order by quant desc", Object[].class);
		List<Object[]> result = query.setMaxResults(1).getResultList();
		
		for(Object[] itens : result){
			String name = (String) itens[1];
			long quant = (Long) itens[0];
			
			System.out.println("Nome é: " + name + "  Quantidade é: " + quant);
	
		}
		
		em.close();
		
//		EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
//		TypedQuery<NotaFiscal> query = em.createQuery("select n from Nf n join fetch n.itens where n.notaFiscalNumero = 1", NotaFiscal.class);
//		NotaFiscal result = query.getSingleResult();
//		
//		Pessoa emitente = result.getEmitente();
//		emitente.setRazaoSocial("modifi");
//		result.setEmitente(emitente);
//		
//		em.getTransaction().begin();
//		
//		em.merge(result);
//
//		em.getTransaction().commit();
//		
//		em.close();
	}

}
