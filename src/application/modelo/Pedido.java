package application.modelo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import application.enumeradores.StatusPedido;

@Entity
@Table(name = "pedidos", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name="numeropedido", unique = true, nullable = false)
	private int numeroPedido;	
	
	@ManyToOne
	@JoinColumn(name = "representada", unique = false, nullable = false)
	@Fetch(FetchMode.SELECT)
	private Representada representada;	

	@ManyToOne
	@JoinColumn(name = "cliente", unique = false, nullable = false)
	@Fetch(FetchMode.SELECT)
	private Cliente cliente;

	@Column(name = "dataemissao", unique = false, nullable = true)
	private Date dataEmissao;

	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
	
	@ManyToOne
	@JoinColumn(name = "tabelapreco", unique = false, nullable = false)
	@Fetch(FetchMode.SELECT)
	private TabelaPreco tabelaPreco;	
	
	/*
	@ManyToOne
	@JoinColumn(name = "vendedor", unique = false, nullable = false)
	@Fetch(FetchMode.SELECT)
	private Vendedor vendedor;		

	@ManyToOne
	@JoinColumn(name = "usuario", unique = false, nullable = false)
	@Fetch(FetchMode.SELECT)
	private Usuario usuario;		
	*/
	@Transient
	private Vendedor vendedor;
	@Transient
	private Usuario usuario;	
	
	@Column(name = "status", unique = false, nullable = true)
	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Representada getRepresentada() {
		return representada;
	}

	public void setRepresentada(Representada representada) {
		this.representada = representada;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public TabelaPreco getTabelaPreco() {
		return tabelaPreco;
	}

	public void setTabelaPreco(TabelaPreco tabelaPreco) {
		this.tabelaPreco = tabelaPreco;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}			


	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	
}
