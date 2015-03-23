package application.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import application.enumeradores.TipoContatoEnum;

@Entity
@Table(name = "contatos", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private int id;		
	
	@Enumerated(EnumType.STRING) 
	@Column(name="tipo", columnDefinition="character varying(255)", unique = true, nullable = false)
	private TipoContatoEnum tipo;

	@Column(name="valor", unique = false, nullable = false)
	private String valor;

	@Column(name="contato", unique = false, nullable = false)
	private String contato;
	
	@ManyToOne(optional=true, cascade=CascadeType.DETACH)
	@JoinColumn(name="cliente_id")
	private Cliente cliente;		

	public Contato() {
		// Construtor para o HIBERNATE
	}
	
	public Contato(TipoContatoEnum tipo, String valor, String contato) throws Exception {
		if (tipo == null) {
			throw new Exception("O tipo de contato é obrigatório.");
		}
		
		if (valor.isEmpty()) {
			throw new Exception("O valor do contato é obrigatório.");
		}
		
		this.tipo = tipo;
		this.valor = valor;
		this.contato = contato;
	}
	

	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo.toString();
	}	

	public void setTipo(TipoContatoEnum tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	
}
