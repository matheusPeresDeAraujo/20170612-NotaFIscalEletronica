package layout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		Object[] colunas = new String[]{"Numero","Date Emissão","Date Operacao","Emitente","Destinatario"};

		Object[][] dados = new Object[][]{
		       {"100", "2017-06-13", "2017-06-13", "Matheus", "Empresa"},
		       {"200", "2017-06-13", "2017-06-13", "Matheus", "Empresa"},
		       {"300", "2017-06-13", "2017-06-13", "Matheus", "Empresa"},
		       {"400", "2017-06-13", "2017-06-13", "Matheus", "Empresa"},
		       {"500", "2017-06-13", "2017-06-13", "Matheus", "Empresa"}
		};

		DefaultTableModel model = new DefaultTableModel(dados , colunas );
		JTable tabela = new JTable();
		tabela.setModel(model);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane  painelTabela = new JScrollPane();
		painelTabela.setViewportView(tabela);
				
		
		ActionListener action1 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				JTextField Name = new JTextField();
				JTextField Idade = new JTextField();
				JComponent[] inputs = new JComponent[] {
				new JLabel("Nome"),
				Name,
				new JLabel("Idade"),
				Idade};

				int result = JOptionPane.showConfirmDialog(null, inputs, "Insira as informações!", JOptionPane.PLAIN_MESSAGE);

				if (Name.getText() == null || Name.getText().equals("") || Idade.getText() == null || Idade.getText().equals(""))
			           return;
				for(int i = 0; i < Idade.getText().length(); i++){
					char t = Idade.getText().charAt(i);
					if(!Character.isDigit(t) || Character.isLetter(t)){
						JOptionPane.showMessageDialog(null, "Informe valores interios para o campo Idade","Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
					String nomeText = ""+Name.getText(); 
					Integer idadeText = Integer.parseInt(Idade.getText());
					
					model.addRow(new Object[]{nomeText, idadeText});
				
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
				
				
			}
		};
		
		ActionListener action5 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		};
		
		
		//JButton btn1 = new JButton(new ImageIcon(Main.class.getResource("~IMG/1.png")));
		JButton btn1 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/1.png"));
		JButton btn2 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/2.png"));
		JButton btn3 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/3.png"));
		JButton btn4 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/4.png"));
		JButton btn5 = new JButton(new ImageIcon("/home/matheus/20170612-NotaFIscalEletronica/src/IMG/5.png"));

		
		btn1.addActionListener(action1);
		btn2.addActionListener(action2);
		btn3.addActionListener(action3);
		btn4.addActionListener(action4);
		btn5.addActionListener(action5);
		
		
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
		
		
		JFrame frame = new JFrame("null");
		frame.setLayout(new BorderLayout());
		frame.add(telaInicial);
		frame.getContentPane().add(toolbar, BorderLayout.PAGE_START);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
//		frame.pack();
		frame.setVisible(true);
	}

}
