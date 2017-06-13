package layout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.*;

public class Main {

	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();
		em.getTransaction().begin();

		
		Pessoa emitente = new Pessoa();
		emitente.setCnpjCpf("10388767618");
		emitente.setEstado("MG");
		emitente.setInscricaoEstadual("131131");
		emitente.setRazaoSocial("Matheus");
		em.persist(emitente);
		em.flush();
		
		Pessoa destinatario = new Pessoa();
		destinatario.setCnpjCpf("88103301000112");
		destinatario.setEstado("MG");
		destinatario.setInscricaoEstadual("543345");
		destinatario.setRazaoSocial("Empresa");
		em.persist(destinatario);
		em.flush();
		
		NotaFiscal nf = new NotaFiscal();
		nf.setDataEmissao(new Date());
		nf.setDataOperacao(new Date());
		nf.setEmitente(emitente);
		nf.setDestinatario(destinatario);
		nf.setInformacoes("Nada a declarar");
		
		
		List<Item> itens = new ArrayList<>();
		Item item = new Item();
		item.setCodigo(333);
		item.setDescricao("Nada a declarar");
		item.setQuant(3);
		item.setValor(500.03);
		itens.add(item);
		
		
		nf.setItens(itens);
		nf.setNotaFiscalNumero(100);
		em.persist(nf);
		
		em.getTransaction().commit();
		em.close();
	}

}
