package layout;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameIndicadores extends JFrame{

	JLabel ind1 = new JLabel("Total de Notas Fiscais cadastradas: ");
	JLabel ind2 = new JLabel("Média de valor das Notas: ");
	JLabel ind3 = new JLabel("Média de valor dos itens nas Notas: ");
	JLabel ind4 = new JLabel("Maior valor de nota: ");
	JLabel ind5 = new JLabel("Estado com maior numero de notas emitidas: ");
	JLabel ind6 = new JLabel("Estado com maior numero de notas como destinatário: ");
	JLabel ind7 = new JLabel("CNPJ/Nome da empresa que é a maior compradora em volume de vendas: ");
	JLabel ind8 = new JLabel("CNPJ/Nome da empresa que é a maior vendedora em volume de vendas: ");
	JLabel ind9 = new JLabel("Total de notas com valor superior a 10mil: ");
	JLabel ind10 = new JLabel("Total de notas com mais de 10 itens: ");
	
	JPanel indicadores = new JPanel();
	
//	Metodo construtor da frame 
	public FrameIndicadores(){
		
		this.construtorJanela();
		
	}
	
//	Metodo construtor da janela. Contém configurações de apresentação e executa a construção individual dos Panel
	private void construtorJanela(){
		
		this.construtorPanelIndicador();
		this.construtorPanelResultIndicador();
		
		this.add(indicadores);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setVisible(true);
		pack();
		
	}

//	Construtor do Panel Sobre
	private void construtorPanelIndicador(){

		JPanel panelIndicador = new JPanel();
		panelIndicador.setLayout(new BoxLayout(panelIndicador, BoxLayout.Y_AXIS));

		panelIndicador.add(ind1);
		panelIndicador.add(ind2);
		panelIndicador.add(ind3);
		panelIndicador.add(ind4);
		panelIndicador.add(ind5);
		panelIndicador.add(ind6);
		panelIndicador.add(ind7);
		panelIndicador.add(ind8);
		panelIndicador.add(ind9);
		panelIndicador.add(ind10);
		
		indicadores.add(panelIndicador);
		
	}
	
	private void construtorPanelResultIndicador(){
		
		JPanel panelResultIndicador = new JPanel();
		panelResultIndicador.setLayout(new BoxLayout(panelResultIndicador, BoxLayout.Y_AXIS));
		
		List<String> result = new ArrayList();
		
		this.consultaIndicadores(result);
		
		panelResultIndicador.add(new JLabel(result.get(0)));
		panelResultIndicador.add(new JLabel(result.get(1)));
		panelResultIndicador.add(new JLabel(result.get(2)));
		panelResultIndicador.add(new JLabel(result.get(3)));
		panelResultIndicador.add(new JLabel(result.get(4)));
		panelResultIndicador.add(new JLabel(result.get(5)));
		panelResultIndicador.add(new JLabel(result.get(6)));
		panelResultIndicador.add(new JLabel(result.get(7)));
		panelResultIndicador.add(new JLabel(result.get(8)));
		panelResultIndicador.add(new JLabel(result.get(9)));
		
		indicadores.add(panelResultIndicador);
	}
	
	private void consultaIndicadores(List<String> result){
		
//		Total de Notas Fiscais cadastradas
		EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
		TypedQuery<Long> query = em.createQuery("select count(n) from Nf n", Long.class);
		long result1 = query.getSingleResult();
		
		result.add(""+result1);
		
		em.close();	
		
//		Média de valor das notas
		EntityManager em2 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
		TypedQuery<Double> query2 = em2.createQuery("select AVG(n.valorItens) from Nf n", Double.class);
		Double result2 = query2.getSingleResult();
	
		result.add(""+result2);
		
		em2.close();
		
//		Média de valor dos itens das notas
		EntityManager em3 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
		TypedQuery<Double> query3 = em3.createQuery("select SUM(n.valorItens)/SUM(n.quantItens) from Nf n", Double.class);
		Double result3 = query3.getSingleResult();
		
		result.add(""+result3);
		
		em3.close();
		
//		Maior valor de nota
		EntityManager em4 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
		TypedQuery<Double> query4 = em4.createQuery("select MAX(n.valorItens) from Nf n", Double.class);
		Double result4 = query4.getSingleResult();
		
		result.add(""+result4);
		
		em4.close();
		
//		Estado com maior número de notas emitidas
		EntityManager em5 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
		TypedQuery<Object[]> query5 = em5.createQuery("select n.emitente.estado, count(*) from Nf n GROUP by n.emitente.estado", Object[].class);
		List<Object[]> result5 = query5.getResultList();
		TypedQuery<Object[]> query6 = em5.createQuery("select n.destinatario.estado, count(*) from Nf n GROUP by n.destinatario.estado", Object[].class);
		List<Object[]> result6 = query6.getResultList();
		
		TreeMap<String, Long> resultado = new TreeMap();
		
		for(Object[] itens : result5){
			String name = (String) itens[0];
			long quant = (Long) itens[1];
			
			resultado.put(name, quant);
		}
		for(Object[] itens : result6){
			String name = (String) itens[0];
			long quant = (Long) itens[1];
			
			if(resultado.containsKey(name)){
				long i =resultado.get(name)+quant;
				resultado.put(name, i);
			}else{
				resultado.put(name, quant);
			}
		}
		
		result.add(""+resultado.get(resultado.firstKey()));
		
		em5.close();
		
//		Estado com maior número de notas como destinatário
		EntityManager em7 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
		TypedQuery<Object[]> query7 = em7.createQuery("select n.destinatario.estado, count(*) from Nf n GROUP by n.destinatario.estado", Object[].class);
		List<Object[]> result7 = query7.getResultList();
		
		result.add(""+result7);
		
		em7.close();
		
//		CNPJ/Nome da empresa que é a maior compradora em volume de vendas
		EntityManager em8 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
		TypedQuery<Object[]> query8 = em8.createQuery("select n.destinatario.razaoSocial, count(*) from Nf n GROUP by n.destinatario.razaoSocial", Object[].class);
		List<Object[]> result8 = query8.getResultList();
		
		result.add(""+result8);
		
		em8.close();
		
//		CNPJ/Nome da empresa que é a maior vendedora em volume de vendas
		EntityManager em9 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
		TypedQuery<Object[]> query9 = em9.createQuery("select n.emitente.razaoSocial, count(*) from Nf n GROUP by n.emitente.razaoSocial", Object[].class);
		List<Object[]> result9 = query9.getResultList();
		
		result.add(""+result9);
		
		em9.close();
		
//		Total de notas com valor superior a 10mil
		EntityManager em10 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
		TypedQuery<Object[]> query10 = em10.createQuery("select count(n.valorItens) from Nf n where n.valorItens > 10000", Object[].class);
		List<Object[]> result10 = query10.getResultList();
		
		result.add(""+result10);
		
		em10.close();
		
//		Total de notas com mais de 10 itens
		EntityManager em11 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
		TypedQuery<Object[]> query11 = em11.createQuery("select count(n.quantItens) from Nf n where n.quantItens > 10", Object[].class);
		List<Object[]> result11 = query11.getResultList();
		
		result.add(""+result11);
		
		em11.close();
	}
}
