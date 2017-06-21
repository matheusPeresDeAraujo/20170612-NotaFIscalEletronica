package layout;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameSobre extends JFrame{
	
	JPanel panelSobre = new JPanel();
	
	JLabel titulo = new JLabel("<HTML>  TRABALHO MODULAR  </HTML>");
	JLabel equipe = new JLabel("<HTML>  Alessandro, <BR>Frederico, <BR>Matheus  </HTML>");
	JLabel conteudo = new JLabel("<HTML>  Trabalho desonvolvido aplicando as tecnicas aprendidas em sala de aula e pesquisas relacionadas.  </HTML>");
	
	public FrameSobre(){
		
		panelSobre.setLayout(new BoxLayout(panelSobre, BoxLayout.Y_AXIS));
		panelSobre.add(titulo);
		panelSobre.add(equipe);
		panelSobre.add(conteudo);
		panelSobre.add(new JTextField());
		
		add(panelSobre);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setVisible(true);
		pack();
	}
	
	public static void main(String[] args){
		new FrameSobre();
	}
}
