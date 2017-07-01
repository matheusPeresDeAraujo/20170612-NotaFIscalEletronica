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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class FrameIndicadores extends JFrame{
	
//	Atributos necessários para a manipulação da tabela de indicadores
	DefaultTableModel model;
	JScrollPane  painelTabela;
	JTable tabela = new JTable();
	
//	Metodo construtor da frame 
	public FrameIndicadores(){
		
		this.construtorJanela();
		
	}
	
//	Metodo construtor da janela. Contém configurações de apresentação e executa a construção individual dos Panel
	private void construtorJanela(){
		
		this.construtorPanelIndicador();
		
		JPanel telaInicial = new JPanel();
		telaInicial.setLayout(new BoxLayout(telaInicial, BoxLayout.Y_AXIS));
		telaInicial.add(painelTabela);
		
		add(telaInicial);
		setVisible(true);
		pack();
		
	}

	private void construtorPanelIndicador(){

		Object[] colunas = new String[]{"Pesquisa","Resultado"};

		Object[][] dados = new Object[][]{
		};

		model = new DefaultTableModel(dados , colunas ){
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		tabela = new JTable();
		tabela.setModel(model);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		painelTabela = new JScrollPane();
		painelTabela.setViewportView(tabela);
		
		// busca e inserção dos resultados na tabela
		this.consultaIndicadores();
		
	}

	
	private void consultaIndicadores(){
		
		EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();
		
		
//		Total de Notas Fiscais cadastradas	
		TypedQuery<Long> query = em.createQuery("select count(n) from Nf n", Long.class);
		long result1 = query.getSingleResult();
		model.addRow(new Object[]{"Total de Notas Fiscais cadastradas: ", ""+result1});
		
		
//		Média de valor das notas	
		TypedQuery<Double> query2 = em.createQuery("select AVG(n.valorItens) from Nf n", Double.class);
		Double result2 = query2.getSingleResult();
		model.addRow(new Object[]{"Média de valor das Notas: ", ""+result2});
		
		
//		Média de valor dos itens das notas	
		TypedQuery<Double> query3 = em.createQuery("select SUM(n.valorItens)/SUM(n.quantItens) from Nf n", Double.class);
		Double result3 = query3.getSingleResult();
		model.addRow(new Object[]{"Média de valor dos itens nas Notas: ", ""+result3});
		
		
//		Maior valor de nota	
		TypedQuery<Double> query4 = em.createQuery("select MAX(n.valorItens) from Nf n", Double.class);
		Double result4 = query4.getSingleResult();
		model.addRow(new Object[]{"Maior valor de nota: ", ""+result4});
		
		
//		Estado com maior número de notas emitidas	
		TypedQuery<Object[]> query5 = em.createQuery("select n.emitente.estado, count(*) from Nf n GROUP by n.emitente.estado", Object[].class);
		List<Object[]> result5 = query5.getResultList();
		TypedQuery<Object[]> query6 = em.createQuery("select n.destinatario.estado, count(*) from Nf n GROUP by n.destinatario.estado", Object[].class);
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

		model.addRow(new Object[]{"Estado com maior numero de notas emitidas*: ", ""+resultado.get(resultado.firstKey())});

		
//		Estado com maior número de notas como destinatário	
		TypedQuery<Object[]> query7 = em.createQuery("select n.destinatario.estado, count(*) as num from Nf n GROUP by n.destinatario.estado order by num desc", Object[].class);
		Object[] result7 = query7.setMaxResults(1).getSingleResult();
		
		String estado = ""+result7[0];
		model.addRow(new Object[]{"Estado com maior numero de notas como destinatário: ", estado});
		
		
//		CNPJ/Nome da empresa que é a maior compradora em volume de vendas	
		TypedQuery<Object[]> query8 = em.createQuery("select n.destinatario.razaoSocial, sum(n.valorItens) as soma from Nf n GROUP by n.destinatario.razaoSocial order by soma desc", Object[].class);
		Object[] result8 = query8.setMaxResults(1).getSingleResult();
		
		String razaoSocial = ""+result8[0];
		model.addRow(new Object[]{"CNPJ/Nome da empresa que é a maior compradora em volume de vendas: ", razaoSocial});
		
		
//		CNPJ/Nome da empresa que é a maior vendedora em volume de vendas	
		TypedQuery<Object[]> query9 = em.createQuery("select n.emitente.razaoSocial, sum(n.valorItens) as soma from Nf n GROUP by n.emitente.razaoSocial order by soma desc", Object[].class);
		Object[] result9 = query9.setMaxResults(1).getSingleResult();
		
		String razaoSocial2 = ""+result9[0];
		model.addRow(new Object[]{"CNPJ/Nome da empresa que é a maior vendedora em volume de vendas: ", razaoSocial2});
		
		
//		Total de notas com valor superior a 10mil
		TypedQuery<Long> query10 = em.createQuery("select count(n.valorItens) from Nf n where n.valorItens > 10000", Long.class);
		Long result10 = query10.getSingleResult();
		model.addRow(new Object[]{"Total de notas com valor superior a 10mil: ", ""+result10});
		
		
//		Total de notas com mais de 10 itens	
		TypedQuery<Long> query11 = em.createQuery("select count(n.quantItens) from Nf n where n.quantItens > 10", Long.class);
		Long result11 = query11.getSingleResult();
		model.addRow(new Object[]{"Total de notas com mais de 10 itens: ", ""+result11});
		
		
		em.close();	
	}
}
