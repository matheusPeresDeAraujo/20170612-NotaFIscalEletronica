package layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class TelaInicial {

	public static void main(String[] args) {
		
		
		// Tabela de visualização de notas fiscais
		Object[] colunas = new String[]{"Numero","Date Emissão","CNPJ/CPF","Razão Social/Nome","Quantidade de Itens","Valor Total"};

		Object[][] dados = new Object[][]{
		       {"100", "2017-06-13", "10388767618", "Matheus", "100", "1000.00"},
		       {"200", "2017-06-13", "10388767618", "Matheus", "100", "1000.00"},
		       {"300", "2017-06-13", "10388767618", "Matheus", "100", "1000.00"},
		       {"400", "2017-06-13", "10388767618", "Matheus", "100", "1000.00"},
		       {"500", "2017-06-13", "10388767618", "Matheus", "100", "1000.00"}
		};

		DefaultTableModel model = new DefaultTableModel(dados , colunas );
		JTable tabela = new JTable();
		tabela.setModel(model);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane  painelTabela = new JScrollPane();
		painelTabela.setViewportView(tabela);
				
		
		//Tabela Item
		Object[] colunasItem = new String[]{"Codigo","Descricao","Preco","Quantidade","Valor Total"};

		Object[][] dadosItem = new Object[][]{
		       {"100", "Item 1", "100.00", "5", "500.00"},
		       {"200", "Item 2", "100.00", "5", "500.00"},
		       {"300", "Item 3", "100.00", "5", "500.00"},
		       {"400", "Item 4", "100.00", "5", "500.00"},
		       {"500", "Item 5", "100.00", "5", "500.00"}
		};

		DefaultTableModel modelItem = new DefaultTableModel(dadosItem , colunasItem );
		JTable tabelaItem = new JTable();
		tabelaItem.setModel(modelItem);
		tabelaItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane  painelTabelaItem = new JScrollPane();
		painelTabelaItem.setViewportView(tabelaItem);
		
		
		JFrame frame = new JFrame("null");
		JFrame sobreFrame = new JFrame("Sobre");
		JFrame cadastroFrame = new JFrame("Cadastro");
		
		ActionListener action0 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JTextField Codigo = new JTextField();
				JTextField Descricao = new JTextField();
				JTextField Preco = new JTextField();
				JTextField Quantidade = new JTextField();
				
				JComponent[] inputs = new JComponent[] {
				new JLabel("Codigo"),
				Codigo,
				new JLabel("Descricao"),
				Descricao,
				new JLabel("Preco"),
				Preco,
				new JLabel("Quantidade"),
				Quantidade};

				int result = JOptionPane.showConfirmDialog(null, inputs, "Insira as informações!", JOptionPane.PLAIN_MESSAGE);

				if (Codigo.getText() == null || Codigo.getText().equals("") || Descricao.getText() == null || Descricao.getText().equals("") || Preco.getText() == null || Preco.getText().equals("") || Quantidade.getText() == null || Quantidade.getText().equals(""))
			           return;
				
					String codigoText = ""+Codigo.getText(); 
					String descricaoText = ""+Descricao.getText(); 
					String precoText = ""+Preco.getText(); 
					String quantidadeText = ""+Quantidade.getText();
					float valorTotal = Integer.parseInt(Preco.getText()) * Integer.parseInt(Quantidade.getText());
					
					modelItem.addRow(new Object[]{codigoText, descricaoText, precoText, quantidadeText, valorTotal});
				
			}
		};
		
		ActionListener action1 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JTextField Codigo = new JTextField();
				JTextField Descricao = new JTextField();
				JTextField Preco = new JTextField();
				JTextField Quantidade = new JTextField();
				
				JComponent[] inputs = new JComponent[] {
				new JLabel("Codigo"),
				Codigo,
				new JLabel("Descricao"),
				Descricao,
				new JLabel("Preco"),
				Preco,
				new JLabel("Quantidade"),
				Quantidade};

				int result = JOptionPane.showConfirmDialog(null, inputs, "Insira as informações!", JOptionPane.PLAIN_MESSAGE);

				if (Codigo.getText() == null || Codigo.getText().equals("") || Descricao.getText() == null || Descricao.getText().equals("") || Preco.getText() == null || Preco.getText().equals("") || Quantidade.getText() == null || Quantidade.getText().equals(""))
			           return;
				
					String codigoText = ""+Codigo.getText(); 
					String descricaoText = ""+Descricao.getText(); 
					String precoText = ""+Preco.getText(); 
					String quantidadeText = ""+Quantidade.getText();
					float valorTotal = Integer.parseInt(Preco.getText()) * Integer.parseInt(Quantidade.getText());
					
					modelItem.addRow(new Object[]{codigoText, descricaoText, precoText, quantidadeText, valorTotal});
				
			}
		};

		ActionListener action2 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				//System.out.println(tabela.getSelectedRow());
				if (tabela.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "Selecione o registro a ser apagado","Alerta", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				Object[] options = {"Sim", "Não"};
				int n = JOptionPane.showOptionDialog(null, "Deseja realmente apagar o registro: "+tabela.getSelectedRow(), "Alerta", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,options[0] );
				
				if (n == 1){
					return;
				}
				model.removeRow(tabela.getSelectedRow());
				
				
			}
		};
		
		ActionListener action3 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (tabelaItem.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "Selecione o registro a ser apagado","Alerta", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				Object[] options = {"Sim", "Não"};
				int n = JOptionPane.showOptionDialog(null, "Deseja realmente apagar o registro: "+tabelaItem.getSelectedRow(), "Alerta", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,options[0] );
				
				if (n == 1){
					return;
				}
				modelItem.removeRow(tabelaItem.getSelectedRow());
				
			}
		};
		
		ActionListener action4 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				JTextField numero = new JTextField();
				JTextField modelo = new JTextField();
				JTextField natureza = new JTextField();
				JTextField dataOperacao = new JTextField();
				JTextField dataEmissao = new JTextField();
				
				JTextField identificacaoEmitente = new JTextField();
				JTextField nomeEmitente = new JTextField();
				JTextField inscricaoEmitente = new JTextField();
				JTextField estadoEmitente = new JTextField();
				
				JTextField identificacaoDestinatario = new JTextField();
				JTextField nomeDestinatario = new JTextField();
				JTextField inscricaoDestinatario = new JTextField();
				JTextField estadoDestinatario = new JTextField();
				
				JButton adicionarItem = new JButton("Adicionar");
				JButton removerItem = new JButton("Remover");
				
				JTextField quantidadeItens = new JTextField();
				JTextField valorTotal = new JTextField();
				JTextArea informacoes = new JTextArea(10,10);
				
				
				JButton gravarNf = new JButton("Gravar");
				JButton cancelarNf = new JButton("Cancelar");
				
				
				numero.setColumns(5);
				modelo.setColumns(10);
				natureza.setColumns(5);
				dataOperacao.setColumns(10);
				dataEmissao.setColumns(10);
				
				identificacaoEmitente.setColumns(14);
				nomeEmitente.setColumns(20);
				inscricaoEmitente.setColumns(20);
				estadoEmitente.setColumns(10);
				
				identificacaoDestinatario.setColumns(14);
				nomeDestinatario.setColumns(20);
				inscricaoDestinatario.setColumns(20);
				estadoDestinatario.setColumns(10);
				
				adicionarItem.addActionListener(action1);
				removerItem.addActionListener(action3);
				
				quantidadeItens.setColumns(5);
				valorTotal.setColumns(12);
				informacoes.setColumns(10);
				
				gravarNf.addActionListener(action0);
				
				
				
				JPanel cadastroPanelNf1 = new JPanel();
				//cadastroPanelNf1.setLayout(new BoxLayout(cadastroPanelNf1, BoxLayout.X_AXIS));
				cadastroPanelNf1.setLayout(new FlowLayout(FlowLayout.LEFT));
				cadastroPanelNf1.add(new JLabel("   Numero NF: "));
				cadastroPanelNf1.add(numero);
				cadastroPanelNf1.add(new JLabel("   Modelo: "));
				cadastroPanelNf1.add(modelo);
				cadastroPanelNf1.add(new JLabel("   Natureza: "));
				cadastroPanelNf1.add(natureza);
				
				
				JPanel cadastroPanelNf2 = new JPanel();
				//cadastroPanelNf2.setLayout(new BoxLayout(cadastroPanelNf2, BoxLayout.X_AXIS));
				cadastroPanelNf2.setLayout(new FlowLayout(FlowLayout.LEFT));
				cadastroPanelNf2.add(new JLabel("   Data Operação: "));
				cadastroPanelNf2.add(dataOperacao);
				cadastroPanelNf2.add(new JLabel("   Data Emissão: "));
				cadastroPanelNf2.add(dataEmissao);
				
				
				JPanel cadastroPanelNf = new JPanel();
				cadastroPanelNf.setLayout(new BoxLayout(cadastroPanelNf, BoxLayout.Y_AXIS));
				//cadastroPanelNf.setLayout(new FlowLayout(FlowLayout.LEFT));
				cadastroPanelNf.add(cadastroPanelNf1);
				cadastroPanelNf.add(cadastroPanelNf2);	
				cadastroPanelNf.add(new JLabel(" "));
				
				
				JPanel cadastroPanelEmitente1 = new JPanel();
				cadastroPanelEmitente1.setLayout(new BoxLayout(cadastroPanelEmitente1, BoxLayout.X_AXIS));
				cadastroPanelEmitente1.add(new JLabel("   CPF/CNPJ: "));
				cadastroPanelEmitente1.add(identificacaoEmitente);
				cadastroPanelEmitente1.add(new JLabel("   Inscrição Estadual: "));
				cadastroPanelEmitente1.add(inscricaoEmitente);
				cadastroPanelEmitente1.add(new JLabel("   Estado: "));
				cadastroPanelEmitente1.add(estadoEmitente);
				
				
				JPanel cadastroPanelEmitente2 = new JPanel();
				cadastroPanelEmitente2.setLayout(new BoxLayout(cadastroPanelEmitente2, BoxLayout.X_AXIS));
				cadastroPanelEmitente2.add(new JLabel("   Nome: "));
				cadastroPanelEmitente2.add(nomeEmitente);
				
				
				JPanel cadastroPanelEmitente = new JPanel();
				cadastroPanelEmitente.setBorder(BorderFactory.createTitledBorder(":::::::EMITENTE::::::"));
				cadastroPanelEmitente.setLayout(new BoxLayout(cadastroPanelEmitente, BoxLayout.Y_AXIS));
				cadastroPanelEmitente.add(new JLabel(" "));
				cadastroPanelEmitente.add(cadastroPanelEmitente2);
				cadastroPanelEmitente.add(cadastroPanelEmitente1);
				cadastroPanelEmitente.add(new JLabel(" "));
				
				
				JPanel cadastroPanelDestinatario1 = new JPanel();
				cadastroPanelDestinatario1.setLayout(new BoxLayout(cadastroPanelDestinatario1, BoxLayout.X_AXIS));
				cadastroPanelDestinatario1.add(new JLabel("   CPF/CNPJ: "));
				cadastroPanelDestinatario1.add(identificacaoDestinatario);
				cadastroPanelDestinatario1.add(new JLabel("   Inscrição Estadual: "));
				cadastroPanelDestinatario1.add(inscricaoDestinatario);
				cadastroPanelDestinatario1.add(new JLabel("   Estado: "));
				cadastroPanelDestinatario1.add(estadoDestinatario);
				
				
				JPanel cadastroPanelDestinatario2 = new JPanel();
				cadastroPanelDestinatario2.setLayout(new BoxLayout(cadastroPanelDestinatario2, BoxLayout.X_AXIS));
				cadastroPanelDestinatario2.add(new JLabel("   Nome: "));
				cadastroPanelDestinatario2.add(nomeDestinatario);

				
				JPanel cadastroPanelDestinatario = new JPanel();
				cadastroPanelDestinatario.setBorder(BorderFactory.createTitledBorder(":::::DESTINATARIO::::::"));
				cadastroPanelDestinatario.setLayout(new BoxLayout(cadastroPanelDestinatario, BoxLayout.Y_AXIS));
				cadastroPanelDestinatario.add(new JLabel(" "));
				cadastroPanelDestinatario.add(cadastroPanelDestinatario2);
				cadastroPanelDestinatario.add(cadastroPanelDestinatario1);
				cadastroPanelDestinatario.add(new JLabel(" "));
				
				
				JPanel cadastroPanelItemOpcoes = new JPanel();
				cadastroPanelItemOpcoes.setLayout(new BoxLayout(cadastroPanelItemOpcoes, BoxLayout.X_AXIS));
				cadastroPanelItemOpcoes.add(adicionarItem);
				cadastroPanelItemOpcoes.add(removerItem);
				
				
				JPanel cadastroPanelItem = new JPanel();
				cadastroPanelItem.setLayout(new BoxLayout(cadastroPanelItem, BoxLayout.Y_AXIS));
				cadastroPanelItem.setBorder(BorderFactory.createTitledBorder(":::::ITEM::::::"));
				cadastroPanelItem.add(new JLabel(" "));
				cadastroPanelItem.add(painelTabelaItem);
				cadastroPanelItem.add(cadastroPanelItemOpcoes);
				
				
				JPanel cadastroPanelNff1 = new JPanel();
				cadastroPanelNff1.setLayout(new BoxLayout(cadastroPanelNff1, BoxLayout.X_AXIS));
				cadastroPanelNff1.add(new JLabel("   Quantidade de itens: "));
				cadastroPanelNff1.add(quantidadeItens);
				cadastroPanelNff1.add(new JLabel("   Valor Total: "));
				cadastroPanelNff1.add(valorTotal);
				
				
				JPanel cadastroPanelNff2 = new JPanel();
				cadastroPanelNff2.setLayout(new BorderLayout());
				cadastroPanelNff2.add(new JLabel("   Informações: "));
				
				
				JPanel cadastroPanelNff = new JPanel();
				cadastroPanelNff.setLayout(new BoxLayout(cadastroPanelNff, BoxLayout.Y_AXIS));
				cadastroPanelNff.add(new JLabel(" "));
				cadastroPanelNff.add(cadastroPanelNff1);
				cadastroPanelNff.add(cadastroPanelNff2);
				cadastroPanelNff.add(informacoes);
				
				
				JPanel cadastroPanelControler = new JPanel();
				cadastroPanelControler.setLayout(new BoxLayout(cadastroPanelControler, BoxLayout.X_AXIS));
				cadastroPanelControler.add(gravarNf);
				cadastroPanelControler.add(cancelarNf);
				
				
				JPanel cadastroPanel = new JPanel();
				cadastroPanel.setLayout(new BoxLayout(cadastroPanel, BoxLayout.Y_AXIS));
				cadastroPanel.add(new JLabel(" "));
				cadastroPanel.add(new JLabel(":::::NOTA FISCAL ELETRÔNICA:::::"));
				cadastroPanel.add(new JLabel(" "));
				cadastroPanel.add(cadastroPanelNf);
				cadastroPanel.add(cadastroPanelEmitente);
				cadastroPanel.add(cadastroPanelDestinatario);
				cadastroPanel.add(cadastroPanelItem);
				cadastroPanel.add(cadastroPanelNff);
				cadastroPanel.add(cadastroPanelControler);
				
				
				cadastroFrame.setLayout(new BorderLayout());
				cadastroFrame.add(cadastroPanel);
				//cadastroFrame.setSize(500, 200);
				cadastroFrame.pack();
				cadastroFrame.setVisible(true);
				cadastroFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
							frame.setEnabled(true);
					}
				});
				frame.setEnabled(false);
				
			}
		};
		
		ActionListener action5 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JPanel sobrePanel = new JPanel();
				sobrePanel.setLayout(new BoxLayout(sobrePanel, BoxLayout.Y_AXIS));
				sobrePanel.add(new JLabel("Disciplina: Desenvolvimento Orientado à Objetos e Persistência"));
				sobrePanel.add(new JLabel("<HTML>Equipe: Matheus Peres de Araujo <BR> Alessandro <BR> Frederico</HTML>"));
				sobrePanel.add(new JLabel("Trabalho desenvolvido no intuito de muita coisa."));
				
				
				sobreFrame.setLayout(new BorderLayout());
				sobreFrame.add(sobrePanel);
				sobreFrame.setSize(500, 200);
				sobreFrame.setVisible(true);
				sobreFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
							frame.setEnabled(true);
					}
				});
				frame.setEnabled(false);
				
			}
		};
		
		
		//JButton btn1 = new JButton(new ImageIcon(Main.class.getResource("~IMG/1.png")));
		JButton btn1 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/adicionar.png"));
		JButton btn2 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/remover.png"));
		JButton btn3 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/3.png"));
		JButton btn4 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/4.png"));
		JButton btn5 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/5.png"));

		
		btn1.addActionListener(action1);
		btn2.addActionListener(action2);
		btn3.addActionListener(action3);
		btn4.addActionListener(action4);
		btn5.addActionListener(action5);
		
		
		JMenu arquivo = new JMenu("Arquivo");
		JMenu cadastro = new JMenu("Cadastro");
		JMenuItem sobre = new JMenuItem("Sobre");
		
		
		sobre.addActionListener(action5);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(arquivo);
		menuBar.add(cadastro);
		menuBar.add(sobre);
		
		
		JMenuItem sair = new JMenuItem("Sair");
		JMenuItem adicionar = new JMenuItem("Adicionar");
		
		
		arquivo.add(adicionar);
		arquivo.add(sair);
		
		
		JToolBar toolbar = new JToolBar("Aplicações");
		toolbar.setFloatable(false);
		toolbar.add(btn1);
		toolbar.add(btn2);
		toolbar.add(btn3);
		toolbar.add(btn4);
		toolbar.add(btn5);
		
		
		JPanel telaInicial = new JPanel();
		telaInicial.setLayout(new BoxLayout(telaInicial, BoxLayout.Y_AXIS));
		telaInicial.add(painelTabela);
		
		
		frame.setLayout(new BorderLayout());
		//frame.setResizable(false);
		frame.add(telaInicial);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(toolbar, BorderLayout.PAGE_START);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
//		frame.pack();
		frame.setVisible(true);
	}

}
