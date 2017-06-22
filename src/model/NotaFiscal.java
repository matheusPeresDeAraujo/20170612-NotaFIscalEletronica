package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="Nf")
public class NotaFiscal {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true, nullable=false)
	private long notaFiscalNumero;
	
	private String modelo = "Modelo 1-A";
	
	private String natureza = "Venda";

	@Column(nullable=false)
	private Date dataOperacao = new Date();

	@Column(nullable=false)//Não pode ser mais que 5 dias após operacao
	private Date dataEmissao;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "emitente_id", nullable=false)
	private Pessoa emitente;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "destinatario_id", nullable=false)
	private Pessoa destinatario;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Item> itens = new ArrayList<>();
	
	private int quantItens; 
	private double valorItens; 

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

	public long getId() {
		return id;
	}
	
	public int getQuantItens() {
		return quantItens;
	}

	public void setQuantItens(int quantItens) {
		this.quantItens = quantItens;
	}

	public double getValorItens() {
		return valorItens;
	}

	public void setValorItens(double valorItens) {
		this.valorItens = valorItens;
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
