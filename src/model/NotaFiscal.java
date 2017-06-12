package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotaFiscal {
	// inteiro, unico , não nulo
	private long notaFiscalNumero;
	// Modelo 1-A: valor somente leitura
	private String modelo = "Modelo 1-A";
	//valor somente leitura "Venda"
	private String natureza = "Venda";
	//Obrigatório
	private Date dataOperacao = new Date();
	//Obrigatório. Não pode ser mais que 5 dias após operacao
	private Date dataEmissao;
	
	private Pessoa emitente;
	private Pessoa destinatario;
	
	private List<Item> itens = new ArrayList<>();
	
	private int quantItens;
	private double valorItens;
	private String informacoes;
	
}
