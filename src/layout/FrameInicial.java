package layout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
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
		
		this.construtorTabelaModel();
		this.construtorMenuBar();
		this.construtorToobar();
		
		JPanel telaInicial = new JPanel();
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
		
		JButton btn1 = new JButton(new ImageIcon("src/IMG/adicionar2.png"));
		JButton btn2 = new JButton(new ImageIcon("src/IMG/remover2.png"));
		JButton btn3 = new JButton(new ImageIcon("src/IMG/editar.png"));
		JButton btn4 = new JButton(new ImageIcon("src/IMG/4.png"));
		JButton btn5 = new JButton(new ImageIcon("src/IMG/5.png"));

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
//		toolbar.add(btn4);
//		toolbar.add(btn5);
		
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

				FrameIndicadores fi = new FrameIndicadores();
				
				fi.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
							setEnabled(true);
					}
				});
				setEnabled(false);
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
							
						
						EntityManager em = null;
						
						try {
							
							
							DateFormat f = DateFormat.getDateInstance();
							
							
							model.addRow(new Object[]{
									info.getNotaFiscalNumero(),
									f.format(info.getDataEmissao()),
									info.getEmitente().getCnpjCpf(),
									info.getEmitente().getRazaoSocial(),
									info.getQuantItens(),
									info.getValorItens()
									
							});
							
							em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();
							em.getTransaction().begin();
							
							em.persist(info);
							
							em.getTransaction().commit();
							
							
						} catch (Exception e2) {
							// TODO: handle exception
						} finally {
							em.close();
							setEnabled(true);
						}
						
					}
					
					@Override
					public void notificarCancelar(){
						setEnabled(true);
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
							
							DateFormat f = DateFormat.getDateInstance();
							
							model.addRow(new Object[]{
									info.getNotaFiscalNumero(),
									f.format(info.getDataEmissao()),
									info.getEmitente().getCnpjCpf(),
									info.getEmitente().getRazaoSocial(),
									info.getQuantItens(),
									info.getValorItens()
									
							});
							
							
							em.merge(info);
							
							em.getTransaction().commit();
							em.close();
							
							
						} catch (Exception e2) {
							// TODO: handle exception
						} finally {
							setEnabled(true);
						}
						
					}

					@Override
					public void notificarCancelar() {
						setEnabled(true);
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
		
		EntityManager em = null;
		
//		Busca de notas fiscais cadastradas em banco.
		try{
		
			em = Persistence.createEntityManagerFactory("notaFiscal_unit").createEntityManager();	
			TypedQuery<NotaFiscal> query = em.createQuery("select n from Nf n order by n.notaFiscalNumero", NotaFiscal.class);
			List<NotaFiscal> result = query.getResultList();
			
//			Formatação da data para visualização
			DateFormat f = DateFormat.getDateInstance();
			for(NotaFiscal notas : result){
				
				model.addRow(new Object[]{notas.getNotaFiscalNumero(), f.format(notas.getDataEmissao()), notas.getEmitente().getCnpjCpf(), notas.getEmitente().getRazaoSocial(), notas.getQuantItens(), notas.getValorItens()});
			
			}
			
		}catch(Exception e){
			
		}finally {
			em.close();
		}	
		
	}
	
	public static void main(String[] args){
		new FrameInicial();
	}

	
}
