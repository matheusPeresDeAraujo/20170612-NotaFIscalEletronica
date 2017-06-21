package layout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class FrameInicial extends JFrame{
	
	JButton btn1 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/adicionar.png"));
	JButton btn2 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/remover.png"));
	JButton btn3 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/3.png"));
	JButton btn4 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/4.png"));
	JButton btn5 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/5.png"));

	JMenu arquivo = new JMenu("Arquivo");
	JMenu cadastro = new JMenu("Cadastro");
	JMenuItem sobre = new JMenuItem("Sobre");
	
	JMenuBar menuBar = new JMenuBar();
	
	JMenuItem sair = new JMenuItem("Sair");
	JMenuItem adicionar = new JMenuItem("Adicionar");
	
	JToolBar toolbar = new JToolBar("Aplicações");

	JPanel telaInicial = new JPanel();
	
	public FrameInicial(){
		
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
		
		menuBar.add(arquivo);
		menuBar.add(cadastro);
		menuBar.add(sobre);
		
		sobre.addActionListener (new ActionListener() {
			
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
		});
		
		arquivo.add(adicionar);
		arquivo.add(sair);
		
		toolbar.setFloatable(false);
		toolbar.add(btn1);
		toolbar.add(btn2);
		toolbar.add(btn3);
		toolbar.add(btn4);
		toolbar.add(btn5);
		
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				FrameCadastroNf cadastro = new FrameCadastroNf();
				
				cadastro.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
							setEnabled(true);
					}
				});
				setEnabled(false);
				
			}
		});
		
		telaInicial.setLayout(new BoxLayout(telaInicial, BoxLayout.Y_AXIS));
		telaInicial.add(painelTabela);
		
		add(telaInicial);
		setJMenuBar(menuBar);
		getContentPane().add(toolbar, BorderLayout.PAGE_START);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 200);
//		frame.pack();
		setVisible(true);
		
	}
	
	public static void main(String[] args){
		new FrameInicial();
	}
	
}
