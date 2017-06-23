package layout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import model.NotaFiscal;

public class FrameInicial extends JFrame{
	
//	Atributos necessários para a manipulação da tabela de notas fiscais
	DefaultTableModel model;
	JScrollPane  painelTabela;
	JTable tabela = new JTable();
	
//	Atributos necessários para a manipulação dos campos na nota
	JMenuBar menuBar = new JMenuBar();
	JToolBar toolbar = new JToolBar("Aplicações");
	
//	Acoes dentro do frame
	ActionListener acessSobre;
	ActionListener acessIndicador;
	ActionListener acessCadastro;
	ActionListener remove;
	ActionListener acessEditor;

	
	public FrameInicial(){
		
		this.construtorJanela();
		
	}
	
	private void construtorJanela(){

		JPanel telaInicial = new JPanel();
		
		this.construtorTabelaModel();
		this.construtorMenuBar();
		this.construtorToobar();
		
		telaInicial.setLayout(new BoxLayout(telaInicial, BoxLayout.Y_AXIS));
		telaInicial.add(painelTabela);
		
		add(telaInicial);
		setJMenuBar(menuBar);
		getContentPane().add(toolbar, BorderLayout.PAGE_START);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(500, 200);
		pack();
		setVisible(true);
	}
	
	private void construtorTabelaModel(){
		
		Object[] colunas = new String[]{"Numero","Date Emissão","CNPJ/CPF","Razão Social/Nome","Quantidade de Itens","Valor Total"};

		Object[][] dados = new Object[][]{
//		       {"100", "2017-06-13", "10388767618", "Matheus", "100", "1000.00"},
//		       {"200", "2017-06-13", "10388767618", "Matheus", "100", "1000.00"},
//		       {"300", "2017-06-13", "10388767618", "Matheus", "100", "1000.00"},
//		       {"400", "2017-06-13", "10388767618", "Matheus", "100", "1000.00"},
//		       {"500", "2017-06-13", "10388767618", "Matheus", "100", "1000.00"}
		};

		model = new DefaultTableModel(dados , colunas );
		tabela = new JTable();
		tabela.setModel(model);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		painelTabela = new JScrollPane();
		painelTabela.setViewportView(tabela);
		
//		busca e inserção de notas fiscais do banco na tabela
		this.buscaNf();
		
	}
	
	private void construtorMenuBar(){
		
		JMenu arquivo = new JMenu("Arquivo");
		JMenu cadastro = new JMenu("Cadastro");
		JMenuItem sobre = new JMenuItem("Sobre");
		JMenuItem indicador = new JMenuItem("Indicador");
		
		JMenuItem sair = new JMenuItem("Sair");
		JMenuItem adicionar = new JMenuItem("Adicionar");
		
		arquivo.add(adicionar);
		arquivo.add(sair);
		
		this.actionAcessSobre();
		sobre.addActionListener(acessSobre);
		
		this.actionAcessIndicador();
		indicador.addActionListener(acessIndicador);
		
		
		menuBar.add(arquivo);
		menuBar.add(cadastro);
		menuBar.add(sobre);
		menuBar.add(indicador);
		
		
	}
	
	private void construtorToobar(){
		
		JButton btn1 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/adicionar.png"));
		JButton btn2 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/remover.png"));
		JButton btn3 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/3.png"));
		JButton btn4 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/4.png"));
		JButton btn5 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/5.png"));

		this.actionAcessCadastro();
		btn1.addActionListener(acessCadastro);
		this.actionRemove();
		btn2.addActionListener(remove);
		this.actionEditor();
		btn3.addActionListener(acessEditor);
		
		toolbar.setFloatable(false);
		toolbar.add(btn1);
		toolbar.add(btn2);
		toolbar.add(btn3);
		toolbar.add(btn4);
		toolbar.add(btn5);
		
	}
	
	private void actionAcessSobre(){
		
		acessSobre = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				FrameSobre sobre = new FrameSobre();
				
				sobre.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
							setEnabled(true);
					}
				});
				setEnabled(false);
				
			}
		};
		
	}
	
	private void actionAcessIndicador(){
		
		acessIndicador = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				Total de Notas Fiscais cadastradas
				EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
				TypedQuery<Long> query = em.createQuery("select count(n) from Nf n", Long.class);
				long result = query.getSingleResult();
				System.out.println(result);
				
				em.close();	
				
//				Média de valor das notas
				EntityManager em2 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
				TypedQuery<Double> query2 = em2.createQuery("select AVG(n.valorItens) from Nf n", Double.class);
				Double result2 = query2.getSingleResult();
				System.out.println(result2);
				
				em2.close();
				
//				Média de valor dos itens das notas
				EntityManager em3 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
				TypedQuery<Double> query3 = em3.createQuery("select SUM(n.valorItens)/SUM(n.quantItens) from Nf n", Double.class);
				Double result3 = query3.getSingleResult();
				System.out.println(result3);
				
				em3.close();
				
//				Maior valor de nota
				EntityManager em4 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
				TypedQuery<Double> query4 = em4.createQuery("select MAX(n.valorItens) from Nf n", Double.class);
				Double result4 = query4.getSingleResult();
				System.out.println(result4);
				
				em4.close();
				
//				Estado com maior número de notas emitidas
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
				
				System.out.println(resultado.get(resultado.firstKey()));
				
				em5.close();
				
//				Estado com maior número de notas como destinatário
				EntityManager em7 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
				TypedQuery<Object[]> query7 = em7.createQuery("select n.destinatario.estado, count(*) from Nf n GROUP by n.destinatario.estado", Object[].class);
				List<Object[]> result7 = query7.getResultList();
				
				System.out.println(result7);
				
				em7.close();
				
//				CNPJ/Nome da empresa que é a maior compradora em volume de vendas
				EntityManager em8 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
				TypedQuery<Object[]> query8 = em8.createQuery("select n.destinatario.razaoSocial, count(*) from Nf n GROUP by n.destinatario.razaoSocial", Object[].class);
				List<Object[]> result8 = query8.getResultList();
				
				System.out.println(result8);
				
				em8.close();
				
//				CNPJ/Nome da empresa que é a maior vendedora em volume de vendas
				EntityManager em9 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
				TypedQuery<Object[]> query9 = em9.createQuery("select n.emitente.razaoSocial, count(*) from Nf n GROUP by n.emitente.razaoSocial", Object[].class);
				List<Object[]> result9 = query9.getResultList();
				
				System.out.println(result9);
				
				em9.close();
				
//				Total de notas com valor superior a 10mil
				EntityManager em10 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
				TypedQuery<Object[]> query10 = em10.createQuery("select count(n.valorItens) from Nf n where n.valorItens > 10000", Object[].class);
				List<Object[]> result10 = query10.getResultList();
				
				System.out.println(result10);
				
				em10.close();
				
//				Total de notas com mais de 10 itens
				EntityManager em11 = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
				TypedQuery<Object[]> query11 = em11.createQuery("select count(n.quantItens) from Nf n where n.quantItens > 10", Object[].class);
				List<Object[]> result11 = query11.getResultList();
				
				System.out.println(result11);
				
				em11.close();
				
			}
		};
	}
	
	private void actionAcessCadastro(){
		
		acessCadastro = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				FrameCadastroNf cadastro = new FrameCadastroNf(new Evento<NotaFiscal>() {

					@Override
					public void notificar(NotaFiscal info) {
						
						try {
							
							model.addRow(new Object[]{
									info.getNotaFiscalNumero(),
									info.getDataEmissao(),
									info.getEmitente().getCnpjCpf(),
									info.getEmitente().getRazaoSocial(),
									info.getQuantItens(),
									info.getValorItens()
									
							});
							
							EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();
							em.getTransaction().begin();
							
							em.persist(info);
							
							em.getTransaction().commit();
							em.close();
							
							
						} catch (Exception e2) {
							// TODO: handle exception
						} finally {
							setEnabled(true);
						}
						
					}
				});
				cadastro.setEnabled(true);
				cadastro.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
							setEnabled(true);
					}
				});
				setEnabled(false);
				
			}
		};
		
	}
	
	private void actionEditor(){
		
		acessEditor = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (tabela.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "Selecione o registro a ser Editado","Alerta", JOptionPane.PLAIN_MESSAGE);
					return;
				}

//				Captura do numero da nota fiscal selecionada
				String j =""+tabela.getValueAt(tabela.getSelectedRow(), 0);
				long i = Long.parseLong(j);
				
				EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();
				em.getTransaction().begin();
				
				TypedQuery<NotaFiscal> query = em.createQuery("select n from Nf n join fetch n.itens where n.notaFiscalNumero = :numero", NotaFiscal.class);
				NotaFiscal nf = query.setParameter("numero",i).getSingleResult();
					  
				FrameCadastroNf editar = new FrameCadastroNf(nf, new Evento<NotaFiscal>() {

					@Override
					public void notificar(NotaFiscal info) {
						
						try {
							model.removeRow(tabela.getSelectedRow());
							
							model.addRow(new Object[]{
									info.getNotaFiscalNumero(),
									info.getDataEmissao(),
									info.getEmitente().getCnpjCpf(),
									info.getEmitente().getRazaoSocial(),
									info.getQuantItens(),
									info.getValorItens()
									
							});
							
//							EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();
//							em.getTransaction().begin();
							System.out.println("Informação a ser passada: "+info);
							
							em.merge(info);
							
							em.getTransaction().commit();
							em.close();
							
							
						} catch (Exception e2) {
							// TODO: handle exception
						} finally {
							setEnabled(true);
						}
						
					}
				});
				
				editar.setEnabled(true);
				editar.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
							setEnabled(true);
					}
				});
				setEnabled(false);
				
			}
		};
	}
	
	private void actionRemove(){
		
		remove = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (tabela.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "Selecione o registro a ser apagado","Alerta", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				Object[] options = {"Sim", "Não"};
				int n = JOptionPane.showOptionDialog(null, "Deseja realmente apagar o registro: "+tabela.getSelectedRow(), "Alerta", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,options[0] );
				
				if (n == 1){
					return;
				}
				
				EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();
				em.getTransaction().begin();
				
				String j =""+tabela.getValueAt(tabela.getSelectedRow(), 0);
				long i = Long.parseLong(j);
				Query q = em.createQuery("delete from Nf n " 
						+ "where n.notaFiscalNumero = :name");
				q.setParameter("name", i);
				int deleted = q.executeUpdate();	  
					  
				em.getTransaction().commit();
				em.close();
				
				model.removeRow(tabela.getSelectedRow());
				
			}
		};
	}
	
	private void buscaNf(){
		
		EntityManager em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
//		Busca de notas fiscais.
		TypedQuery<NotaFiscal> query = em.createQuery("select n from Nf n order by n.notaFiscalNumero", NotaFiscal.class);
		List<NotaFiscal> result = query.getResultList();
		System.out.println(result);
		
		for(NotaFiscal notas : result){
			
			model.addRow(new Object[]{notas.getNotaFiscalNumero(), notas.getDataEmissao(), notas.getEmitente().getCnpjCpf(), notas.getEmitente().getRazaoSocial(), notas.getQuantItens(), notas.getValorItens()});
	
		}
		em.close();	
		
	}
	
	public static void main(String[] args){
		new FrameInicial();
	}

	
}
