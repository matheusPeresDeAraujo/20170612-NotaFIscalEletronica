package layout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
				
		
		JFrame frame = new JFrame("null");
		JFrame sobreFrame = new JFrame("Sobre");
		JFrame cadastroFrame = new JFrame("Cadastro");
		
		
		ActionListener action1 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				JTextField Numero = new JTextField();
				JTextField DateEmissao = new JTextField();
				JTextField DateOperacao = new JTextField();
				JTextField Emitente = new JTextField();
				JTextField Destinatario = new JTextField();
				JTextField Informacoes = new JTextField();
				
				JComponent[] inputs = new JComponent[] {
				new JLabel("Numero"),
				Numero,
				new JLabel("DateEmissao"),
				DateEmissao,
				new JLabel("DateOperacao"),
				DateOperacao,
				new JLabel("Emitente"),
				Emitente,
				new JLabel("Destinatario"),
				Destinatario,
				new JLabel("Informacoes"),
				Informacoes};

				int result = JOptionPane.showConfirmDialog(null, inputs, "Insira as informações!", JOptionPane.PLAIN_MESSAGE);

				if (Numero.getText() == null || Numero.getText().equals("") || DateEmissao.getText() == null || DateEmissao.getText().equals("") || DateOperacao.getText() == null || DateOperacao.getText().equals("") || Emitente.getText() == null || Emitente.getText().equals("") || Destinatario.getText() == null || Destinatario.getText().equals(""))
			           return;
				
					String numeroText = ""+Numero.getText(); 
					String dateEmissaoText = ""+DateEmissao.getText(); 
					String dateOperacaoText = ""+DateOperacao.getText(); 
					String emitenteText = ""+Emitente.getText(); 
					String destinatarioText = ""+Destinatario.getText(); 
					String informacoesText = ""+Informacoes.getText(); 
					
					model.addRow(new Object[]{numeroText, dateEmissaoText,dateOperacaoText,emitenteText,destinatarioText,informacoesText});
				
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
				
				
			}
		};
		
		ActionListener action4 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				JPanel cadastroPanel = new JPanel();
				cadastroPanel.setLayout(new BoxLayout(cadastroPanel, BoxLayout.Y_AXIS));
				cadastroPanel.add(new JLabel("Disciplina: Desenvolvimento Orientado à Objetos e Persistência"));;
				
				
				cadastroFrame.setLayout(new BorderLayout());
				cadastroFrame.add(cadastroPanel);
				cadastroFrame.setSize(500, 200);
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
				sobrePanel.add(new JLabel("Trabalho desenvolvido no intuito de muitas coisa."));
				
				
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
		frame.add(telaInicial);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(toolbar, BorderLayout.PAGE_START);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
//		frame.pack();
		frame.setVisible(true);
	}

}
