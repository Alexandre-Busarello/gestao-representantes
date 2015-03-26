package application.modelo;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "itenspedidos", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="pedido", unique=false, nullable = false)
	@Fetch(FetchMode.SELECT)
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="produto", unique=false, nullable = false)
	@Fetch(FetchMode.SELECT)
	private Produto produto;	
	
	@Column(name = "valorunitario", unique = false, nullable = false)	
	private double valorUnitario;	
	
	@Column(name = "quantidade", unique = false, nullable = false)	
	private double quantidadePedida;
	
	@Column(name = "unidademedida", unique = false, nullable = false)	
	private String unidadeMedida;
	
	@Column(name="peripi", unique = false, nullable = false)
	private double percentualIpi;		
	
	@Column(name="percomissaorep", unique = false, nullable = false)
	private double percentualComissaoRep;			

	@Column(name="percomissaovend", unique = false, nullable = false)
	private double percentualComissaoVend;	
	
	@Column(name = "valorfinal", unique = false, nullable = false)	
	private double valorFinal;	
	
	public int getId() {
		return id;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getQuantidadePedida() {
		return quantidadePedida;
	}

	public void setQuantidadePedida(double quantidadePedida) {
		this.quantidadePedida = quantidadePedida;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public double getPercentualIpi() {
		return percentualIpi;
	}

	public void setPercentualIpi(double percentualIpi) {
		this.percentualIpi = percentualIpi;
	}

	public double getPercentualComissaoRep() {
		return percentualComissaoRep;
	}

	public void setPercentualComissaoRep(double percentualComissaoRep) {
		this.percentualComissaoRep = percentualComissaoRep;
	}

	public double getPercentualComissaoVend() {
		return percentualComissaoVend;
	}

	public void setPercentualComissaoVend(double percentualComissaoVend) {
		this.percentualComissaoVend = percentualComissaoVend;
	}
	
	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	
}
