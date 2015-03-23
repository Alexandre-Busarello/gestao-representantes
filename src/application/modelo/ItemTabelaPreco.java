package application.modelo;

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
@Table(name = "itenstabelaspreco", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
public class ItemTabelaPreco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private int id;		
	
	@ManyToOne
	@JoinColumn(name="produto", unique=false, nullable = false)
	@Fetch(FetchMode.SELECT)
	private Produto produto;	
	
	@ManyToOne
	@JoinColumn(name="tabelapreco", unique=false, nullable = false)
	@Fetch(FetchMode.SELECT)
	private TabelaPreco tabelaPreco;
	
	@Column(name="qtdminima", unique = false, nullable = false)
	private double qtdMinima;
	
	@Column(name="precounitario", unique = false, nullable = false)
	private double precoUnitario;
	
	@Column(name="ativo", unique = false, nullable = false)
	private boolean ativo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TabelaPreco getTabelaPreco() {
		return tabelaPreco;
	}

	public void setTabelaPreco(TabelaPreco tabelaPreco) {
		this.tabelaPreco = tabelaPreco;
	}

	public double getQtdMinima() {
		return qtdMinima;
	}

	public void setQtdMinima(double qtdMinima) {
		this.qtdMinima = qtdMinima;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}		
	
	
	
}
