package model;

public class Item {
	//obrigatorio
	private int codigo;
	//obrigatorio, 200 caracteres
	private String descricao;
	//5 casas decimais, maior que zero
	private float valor;
	// maior que zero
	private int quant;
	// campo calculado
	private float valorTotal;
}
