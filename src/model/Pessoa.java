package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable=false, length=14)
	private String cnpjCpf;
	
	@Column(nullable=false, length=200)
	private String razaoSocial;
	
	@Column(nullable=false, length=20)
	private String inscricaoEstadual;

	@Column(nullable=false, length=2)
	private String estado;

	public Pessoa(String cnpjCpf, String razaoSocial, String inscricaoEstadual, String estado) {
		super();
		this.cnpjCpf = cnpjCpf;
		this.razaoSocial = razaoSocial;
		this.inscricaoEstadual = inscricaoEstadual;
		this.estado = estado;
	}

	public Pessoa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
		Pessoa other = (Pessoa) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [cnpjCpf=" + cnpjCpf + ", razaoSocial=" + razaoSocial + ", inscricaoEstadual="
				+ inscricaoEstadual + ", estado=" + estado + "]";
	}
	
	
}
