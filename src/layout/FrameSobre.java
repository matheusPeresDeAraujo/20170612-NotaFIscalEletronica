package layout;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameSobre extends JFrame{
	
	JLabel titulo = new JLabel(
			"<HTML> "
			+ "<style> "
			+ "H1 {color: rgb(0,0,255)"
			+ "}"
			+ "</style>"
			+ "<H1> TRABALHO MODULAR  "
			+ "</HTML>");
	JLabel equipe = new JLabel("<HTML>  Alessandro, <BR>Frederico, <BR>Matheus  </HTML>");
	JLabel conteudo = new JLabel("<HTML>  Trabalho desonvolvido aplicando as tecnicas aprendidas em sala de aula e pesquisas relacionadas.  "
			+ "Sistema desenvolvido para cadastro de notas fiscais, utilizando a linguagem java persistindo as informações em um banco de dados."
			+ "Para a criação dos layouts, forma aplicados os coceitos de java swing."
			+ "Tratamento de exceções, utilizando os comandos TRY e CATCH."
			+ "Aplicado a todo o projeto os conceitos de orientação a objeto."
			+ "Utilizado o SGBD PostgreSQL"
			+ "Manipulação de dados, foram utilizados os principais comandos SQL, 'insert', 'select', 'update' e 'delete'</HTML>");
	
//	Metodo construtor da frame sobre.
	public FrameSobre(){
		
		this.construtorJanela();
		
	}
	
//	Metodo construtor da janela. Contém configurações de apresentação e executa a construção individual dos Panel
	private void construtorJanela(){
		
		this.construtorPanelSobre();
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setVisible(true);
		pack();
		
	}

//	Construtor do Panel Sobre
	private void construtorPanelSobre(){

		JPanel panelSobre = new JPanel();
		panelSobre.setLayout(new BoxLayout(panelSobre, BoxLayout.Y_AXIS));
		
		panelSobre.add(titulo);
		panelSobre.add(equipe);
		panelSobre.add(conteudo);
		
		add(panelSobre);
		
	}
	
}
