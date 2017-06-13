package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {
	
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable=false)
	private int codigo;

	@Column(nullable=false, length=200)
	private String descricao;

	@Column(scale=5)//maior que zero
	private float valor;
	
	private int quant;// maior que zero

	//private float valorTotal; - calculado
	
	public Item(int codigo, String descricao, float valor, int quant) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.quant = quant;
	}

	public long getId() {
		return id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [codigo=" + codigo + ", valor=" + valor + ", quant=" + quant + "]";
	}
	
	
	
}
