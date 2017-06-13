package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NotaFiscal {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true, nullable=false)
	private long notaFiscalNumero;
	// Modelo 1-A: valor somente leitura
	private String modelo = "Modelo 1-A";
	//valor somente leitura "Venda"
	private String natureza = "Venda";

	@Column(nullable=false)
	private Date dataOperacao = new Date();

	@Column(unique=true, nullable=false)//Não pode ser mais que 5 dias após operacao
	private Date dataEmissao;
	
	private Pessoa emitente;
	private Pessoa destinatario;
	
	private List<Item> itens = new ArrayList<>();
	
	//private int quantItens; - calculado
	//private double valorItens; - calculado

	@Column(length=2048)
	private String informacoes;

	public NotaFiscal(long notaFiscalNumero, Date dataOperacao, Date dataEmissao, Pessoa emitente, Pessoa destinatario,
			List<Item> itens, String informacoes) {
		super();
		this.notaFiscalNumero = notaFiscalNumero;
		this.dataOperacao = dataOperacao;
		this.dataEmissao = dataEmissao;
		this.emitente = emitente;
		this.destinatario = destinatario;
		this.itens = itens;
		this.informacoes = informacoes;
	}

	public NotaFiscal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getNotaFiscalNumero() {
		return notaFiscalNumero;
	}

	public void setNotaFiscalNumero(long notaFiscalNumero) {
		this.notaFiscalNumero = notaFiscalNumero;
	}

	public String getModelo() {
		return modelo;
	}

	public String getNatureza() {
		return natureza;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Pessoa getEmitente() {
		return emitente;
	}

	public void setEmitente(Pessoa emitente) {
		this.emitente = emitente;
	}

	public Pessoa getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Pessoa destinatario) {
		this.destinatario = destinatario;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
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
		NotaFiscal other = (NotaFiscal) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NotaFiscal [notaFiscalNumero=" + notaFiscalNumero + ", dataOperacao=" + dataOperacao + ", dataEmissao="
				+ dataEmissao + ", emitente=" + emitente + ", destinatario=" + destinatario + "]";
	}
	
	
}
