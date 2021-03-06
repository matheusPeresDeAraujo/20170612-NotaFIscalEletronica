package layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;

import model.Item;
import model.NotaFiscal;
import model.Pessoa;
import sun.util.calendar.JulianCalendar;

public class FrameCadastroNf extends JFrame{

//	Atributos necessários para a manipulação da tabela de itens
	DefaultTableModel modelItem;
	JScrollPane  painelTabelaItem;
	JTable tabelaItem = new JTable();
	
//	Atributos necessários para a manipulação dos campos na nota
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
	
	JTextField quantidadeItens = new JTextField();
	JTextField valorTotal = new JTextField();
	JTextArea informacoes = new JTextArea(10,10);
	
	JButton adicionarItem = new JButton("Adicionar");
	JButton removerItem = new JButton("Remover");

	JButton gravarNf = new JButton("Gravar");
	JButton cancelarNf = new JButton("Cancelar");
	
//	Paineis internos
	JPanel cadastroPanelNf = new JPanel();
	JPanel cadastroPanelEmitente = new JPanel();
	JPanel cadastroPanelDestinatario = new JPanel();
	JPanel cadastroPanelItem = new JPanel();
	JPanel cadastroPanelNff = new JPanel();
	JPanel cadastroPanelControler = new JPanel();
	
//	Acoes dentro do frame
	ActionListener addItem;
	ActionListener remItem;
	ActionListener persistirNf;
	ActionListener cancelar;	
	
//	Atributo necessário para a modificação de uma nota
	NotaFiscal nf = new NotaFiscal();
	
	public FrameCadastroNf(Evento evento){
		
		this.contrutorJanela(evento);
		
	}
	
	public FrameCadastroNf(NotaFiscal nf, Evento evento){
		
		this.contrutorJanela(evento);
		
//		Nota fiscal que esta sendo editada é a nota fiscal que selecionei. Necessário para realizar o merge, principalmente pelo ID.
		this.nf = nf;
		
		numero.setText(""+nf.getNotaFiscalNumero());
		modelo.setText(nf.getModelo());
		natureza.setText(nf.getNatureza());
		
		DateFormat f = DateFormat.getDateInstance();

	    dataOperacao.setText(""+f.format(nf.getDataOperacao().getTime()));
	    dataEmissao.setText(""+f.format(nf.getDataEmissao().getTime()));
	    
	    identificacaoEmitente.setText(nf.getEmitente().getCnpjCpf());
		nomeEmitente.setText(nf.getEmitente().getRazaoSocial());
		inscricaoEmitente.setText(nf.getEmitente().getInscricaoEstadual());
		estadoEmitente.setText(nf.getEmitente().getEstado());
		
		identificacaoDestinatario.setText(nf.getDestinatario().getCnpjCpf());
		nomeDestinatario.setText(nf.getDestinatario().getRazaoSocial());
		inscricaoDestinatario.setText(nf.getDestinatario().getInscricaoEstadual());
		estadoDestinatario.setText(nf.getDestinatario().getEstado());
		
		quantidadeItens.setText(""+nf.getQuantItens());
		valorTotal.setText(""+nf.getValorItens());
		informacoes.setText(nf.getInformacoes());
		
		for(int i = 0; i <nf.getItens().size(); i++){
	
			actionAdicionarItem();
			modelItem.addRow(new Object[]{nf.getItens().get(i).getCodigo(), nf.getItens().get(i).getDescricao(), nf.getItens().get(i).getValor(),nf.getItens().get(i).getQuant(), nf.getItens().get(i).getValor() * nf.getItens().get(i).getQuant()});
			
		}
	}
	
	private void contrutorJanela(Evento evento){
		
		this.construtorTabelaModelItem();
		this.construtorPanelNf();
		this.construtorPanelEmitente();
		this.construtorPanelDestinatario();
		this.construtorPanelItem();
		this.contrutorPanelNff();
		this.construtorPanelControler(evento);
		
	
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
		
		
		setLayout(new BorderLayout());
		add(cadastroPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//cadastroFrame.setSize(500, 200);
		pack();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
					setEnabled(true);
			}
		});
		setEnabled(false);
	}
	
	private void construtorTabelaModelItem(){
		
		Object[] colunasItem = new String[]{"Codigo","Descricao","Preco","Quantidade","Valor Total"};

		Object[][] dadosItem = new Object[][]{
//		       {"100", "Item 1", "100.00", "5", "500.00"},
//		       {"200", "Item 2", "100.00", "5", "500.00"},
//		       {"300", "Item 3", "100.00", "5", "500.00"},
//		       {"400", "Item 4", "100.00", "5", "500.00"},
//		       {"500", "Item 5", "100.00", "5", "500.00"}
		};

		modelItem = new DefaultTableModel(dadosItem , colunasItem ){
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		tabelaItem.setModel(modelItem);
		tabelaItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		painelTabelaItem = new JScrollPane();
		painelTabelaItem.setViewportView(tabelaItem);
		
	}
	
	private void construtorPanelNf(){
		
		numero.setColumns(5);
		modelo.setColumns(10);
		modelo.setText("Modelo 1-A");
		modelo.setEditable(false);
		natureza.setColumns(5);
		natureza.setText("Venda");
		natureza.setEditable(false);
		dataOperacao.setColumns(10);
		dataEmissao.setColumns(10);
		
		
		JPanel cadastroPanelNf1 = new JPanel();
		cadastroPanelNf1.setLayout(new BoxLayout(cadastroPanelNf1, BoxLayout.X_AXIS));
//		cadastroPanelNf1.setLayout(new FlowLayout(FlowLayout.LEFT));
		cadastroPanelNf1.add(new JLabel("   Numero NF: "));
		cadastroPanelNf1.add(numero);
		cadastroPanelNf1.add(new JLabel("   Modelo: "));
		cadastroPanelNf1.add(modelo);
		cadastroPanelNf1.add(new JLabel("   Natureza: "));
		cadastroPanelNf1.add(natureza);
		
		
		JPanel cadastroPanelNf2 = new JPanel();
		cadastroPanelNf2.setLayout(new BoxLayout(cadastroPanelNf2, BoxLayout.X_AXIS));
//		cadastroPanelNf2.setLayout(new FlowLayout(FlowLayout.LEFT));
		cadastroPanelNf2.add(new JLabel("   Data Operação(dd/mm/aaaa): "));
		cadastroPanelNf2.add(dataOperacao);
		cadastroPanelNf2.add(new JLabel("   Data Emissão(dd/mm/aaaa): "));
		cadastroPanelNf2.add(dataEmissao);
		
		
		cadastroPanelNf.setLayout(new BoxLayout(cadastroPanelNf, BoxLayout.Y_AXIS));
		//cadastroPanelNf.setLayout(new FlowLayout(FlowLayout.LEFT));
		cadastroPanelNf.add(cadastroPanelNf1);
		cadastroPanelNf.add(cadastroPanelNf2);	
		cadastroPanelNf.add(new JLabel(" "));
		
	}
	
	private void construtorPanelEmitente(){
		
		identificacaoEmitente.setColumns(14);
		nomeEmitente.setColumns(20);
		inscricaoEmitente.setColumns(20);
		estadoEmitente.setColumns(10);
		
		
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
		
		
		cadastroPanelEmitente.setBorder(BorderFactory.createTitledBorder(":::::::EMITENTE::::::"));
		cadastroPanelEmitente.setLayout(new BoxLayout(cadastroPanelEmitente, BoxLayout.Y_AXIS));
		cadastroPanelEmitente.add(new JLabel(" "));
		cadastroPanelEmitente.add(cadastroPanelEmitente2);
		cadastroPanelEmitente.add(cadastroPanelEmitente1);
		cadastroPanelEmitente.add(new JLabel(" "));
	}
	
	private void construtorPanelDestinatario(){
		
		identificacaoDestinatario.setColumns(14);
		nomeDestinatario.setColumns(20);
		inscricaoDestinatario.setColumns(20);
		estadoDestinatario.setColumns(10);
		
		
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

		
		cadastroPanelDestinatario.setBorder(BorderFactory.createTitledBorder(":::::DESTINATARIO::::::"));
		cadastroPanelDestinatario.setLayout(new BoxLayout(cadastroPanelDestinatario, BoxLayout.Y_AXIS));
		cadastroPanelDestinatario.add(new JLabel(" "));
		cadastroPanelDestinatario.add(cadastroPanelDestinatario2);
		cadastroPanelDestinatario.add(cadastroPanelDestinatario1);
		cadastroPanelDestinatario.add(new JLabel(" "));
		
	}
	
	private void construtorPanelItem(){
		
		quantidadeItens.setColumns(5);
		quantidadeItens.setEditable(false);
		valorTotal.setColumns(12);
		valorTotal.setEditable(false);
		informacoes.setColumns(10);
		this.actionAdicionarItem();
		adicionarItem.addActionListener(addItem);
		this.actionRemoverItem();
		removerItem.addActionListener(remItem);
		
		
		JPanel cadastroPanelItemOpcoes = new JPanel();
		cadastroPanelItemOpcoes.setLayout(new BoxLayout(cadastroPanelItemOpcoes, BoxLayout.X_AXIS));
		cadastroPanelItemOpcoes.add(adicionarItem);
		cadastroPanelItemOpcoes.add(removerItem);
		
		
		cadastroPanelItem.setLayout(new BoxLayout(cadastroPanelItem, BoxLayout.Y_AXIS));
		cadastroPanelItem.setBorder(BorderFactory.createTitledBorder(":::::ITEM::::::"));
		cadastroPanelItem.add(new JLabel(" "));
		cadastroPanelItem.add(painelTabelaItem);
		cadastroPanelItem.add(cadastroPanelItemOpcoes);
	}
	
	private void actionAdicionarItem(){
		
		
		addItem = new ActionListener() {
			
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
				{ 
					JOptionPane.showMessageDialog(null, "Campo em Branco! ");
					
					//return;
				}
				
				
					String codigoText = ""+Codigo.getText(); 
					String descricaoText = ""+Descricao.getText(); 
					String precoText = ""+Preco.getText(); 
					String quantidadeText = ""+Quantidade.getText();
					Double valorFinal = Double.parseDouble(Preco.getText()) * Integer.parseInt(Quantidade.getText());
					
//					Adiciona a linha na tabela
					modelItem.addRow(new Object[]{codigoText, descricaoText, precoText, quantidadeText, valorFinal});
					
//					calcula valor para os campos valorTotal e quantidade
					if(quantidadeItens.getText() == null || quantidadeItens.getText().equals("") ||valorTotal.getText() == null || valorTotal.getText().equals("")){
						quantidadeItens.setText("" + 1);
						valorTotal.setText("" + valorFinal);
						return;
					}
					
					int quant = 1 + Integer.parseInt(quantidadeItens.getText());
					quantidadeItens.setText("" + quant);
					double valorF = valorFinal + Double.parseDouble(valorTotal.getText());
					valorTotal.setText("" + valorF);
			}
		};
		
	}
	
	private void actionRemoverItem(){
		
		remItem = new ActionListener() {
			
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
				
//				Realiza o calculo dos campos valorTotal e quantidade
				int quant = -1 + Integer.parseInt(quantidadeItens.getText());
				quantidadeItens.setText("" + quant);
				String j =""+tabelaItem.getValueAt(tabelaItem.getSelectedRow(), 4);
				double valorF = - Double.parseDouble(j) + Double.parseDouble(valorTotal.getText());
				valorTotal.setText("" + valorF);
				
//				Somente após o calculo é removida a linha da tabela
				modelItem.removeRow(tabelaItem.getSelectedRow());
			}
		};
	}
	
 	private void contrutorPanelNff(){
		
		JPanel cadastroPanelNff1 = new JPanel();
		cadastroPanelNff1.setLayout(new BoxLayout(cadastroPanelNff1, BoxLayout.X_AXIS));
		cadastroPanelNff1.add(new JLabel("   Quantidade de itens: "));
		cadastroPanelNff1.add(quantidadeItens);
		cadastroPanelNff1.add(new JLabel("   Valor Total: "));
		cadastroPanelNff1.add(valorTotal);
		
		
		JPanel cadastroPanelNff2 = new JPanel();
		cadastroPanelNff2.setLayout(new BorderLayout());
		cadastroPanelNff2.add(new JLabel("   Informações: "));
		
		
		cadastroPanelNff.setLayout(new BoxLayout(cadastroPanelNff, BoxLayout.Y_AXIS));
		cadastroPanelNff.add(new JLabel(" "));
		cadastroPanelNff.add(cadastroPanelNff1);
		cadastroPanelNff.add(cadastroPanelNff2);
		cadastroPanelNff.add(informacoes);
		
	}
	
	private void construtorPanelControler(Evento evento){
		
		this.actionPersistirNf(evento);
		gravarNf.addActionListener(persistirNf);
		this.actionCancelar(evento);
		cancelarNf.addActionListener(cancelar);
		
		cadastroPanelControler.setLayout(new BoxLayout(cadastroPanelControler, BoxLayout.X_AXIS));
		cadastroPanelControler.add(gravarNf);
		cadastroPanelControler.add(cancelarNf);
		
	}
	
	private void actionPersistirNf(Evento<NotaFiscal> evento){
		
		
		persistirNf = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				Verificar se todos os campos foram preenchidos
				if(numero.getText() == null || numero.getText().equals("") 
						|| identificacaoEmitente.getText() == null || identificacaoEmitente.getText().equals("") 
						|| nomeEmitente.getText() == null || nomeEmitente.getText().equals("") 
						|| inscricaoEmitente.getText() == null || inscricaoEmitente.getText().equals("") 
						|| estadoEmitente.getText() == null || estadoEmitente.getText().equals("") 
						|| identificacaoDestinatario.getText() == null || identificacaoDestinatario.getText().equals("") 
						|| nomeDestinatario.getText() == null || nomeDestinatario.getText().equals("") 
						|| inscricaoDestinatario.getText() == null || inscricaoDestinatario.getText().equals("") 
						|| estadoDestinatario.getText() == null || estadoDestinatario.getText().equals(""))
					return;
//				Existencia de pelo menos um item
				if(modelItem.getRowCount()<1)
					return;

				Date d = dplData(dataEmissao.getText());
				Date d2 = dplData(dataOperacao.getText());
				
				long a = d.getTime();
				long b = d2.getTime();
				
//				432000000 = 5 dias
				if(a >= b+432000000){
					JOptionPane.showMessageDialog(null, "Data de Emissão não pode ser posterior 5 dias! ");
					return;
				}
				
//				Verificar a natureza das informações preenchidas
				
				Pessoa emitente = new Pessoa();
				emitente.setCnpjCpf(identificacaoEmitente.getText());
				emitente.setEstado(estadoEmitente.getText());
				emitente.setInscricaoEstadual(inscricaoEmitente.getText());
				emitente.setRazaoSocial(nomeEmitente.getText());
				
				
				Pessoa destinatario = new Pessoa();
				destinatario.setCnpjCpf(identificacaoDestinatario.getText());
				destinatario.setEstado(estadoDestinatario.getText());
				destinatario.setInscricaoEstadual(inscricaoDestinatario.getText());
				destinatario.setRazaoSocial(nomeDestinatario.getText());
				
				
				nf.setDataEmissao(dplData(new String(""+dataEmissao.getText())));
				
				nf.setDataOperacao(dplData(new String(""+dataOperacao.getText())));
				
				nf.setEmitente(emitente);
				nf.setDestinatario(destinatario);
				nf.setInformacoes(informacoes.getText());
				List<Item> itens = new ArrayList<>();
				
				Double valorItens = 0.0;
				for(int i = 0; i < modelItem.getRowCount();i++){
					
					Item item = new Item();
					
					String cl0 = ""+tabelaItem.getValueAt(i, 0);
					String cl1 = ""+tabelaItem.getValueAt(i, 1);
					String cl2 = ""+tabelaItem.getValueAt(i, 2);
					String cl3 = ""+tabelaItem.getValueAt(i, 3);
					
					item.setCodigo(Integer.parseInt(cl0));
					item.setDescricao(cl1);
					item.setQuant(Integer.parseInt(cl3));
					item.setValor(Double.parseDouble(cl2));
					valorItens +=item.getValor()*item.getQuant();
					itens.add(item);
					
				}

				String quant = ""+modelItem.getRowCount();
				nf.setQuantItens(Integer.parseInt(quant));
				nf.setValorItens(valorItens);
				nf.setItens(itens);
				nf.setNotaFiscalNumero(Integer.parseInt(numero.getText()));
				
				evento.notificar(nf);
				dispose();
				
			}
		};
		
	}
	
	private void actionCancelar(Evento evento){
		
		cancelar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				evento.notificarCancelar();
				dispose();
				
			}
		};
	}
	
	private Date dplData(String dataText){
		
		List poss = new ArrayList();
		List<Integer> vall = new ArrayList();
		String data = dataText;
		String subData = null;
		
		int pos0 = 0;
		int pos = data.indexOf("/");
		
		while(pos != -1){
			poss.add(new Integer(pos));
			
			subData = data.substring(pos0, pos);
			
			vall.add(new Integer(subData));
			
			data = data.substring(pos+1);
			pos = data.indexOf("/");
				
		}
		vall.add(Integer.parseInt(data));
		
		Calendar c = Calendar.getInstance();
		c.set(vall.get(2), vall.get(1)-1, vall.get(0));
		Date d = c.getTime();
		
		return d;
	}
}
