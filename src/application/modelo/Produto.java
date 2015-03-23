package application.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import application.controle.negocio.SelecionarRegistroItem;

@Entity
@Table(name = "produtos", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private int id;	
	
	@ManyToOne
	@JoinColumn(name="representada", unique=false, nullable = false)
	@Fetch(FetchMode.SELECT)
	private Representada representada;	

	@Column(name="codigoproduto", length=25, unique = true, nullable = false)
	private String codigoProduto;		
	
	@Column(name="descricao", length=80, unique = false, nullable = false)
	private String descricao;	
	
	@Column(name="complemento", length=60, unique = false, nullable = true)
	private String complemento;				
	
	@Column(name="peripi", unique = false, nullable = true)
	private double percentualIpi;		
	
	@Column(name="percomissaorep", unique = false, nullable = true)
	private double percentualComissaoRep;			

	@Column(name="percomissaovend", unique = false, nullable = true)
	private double percentualComissaoVend;
	
	@Column(name="ativo", unique = false, nullable = false)
	private boolean ativo;
	
	@OneToMany(mappedBy="produto", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ItemTabelaPreco> itensTabelaPreco;	

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

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}	
	
	public SelecionarRegistroItem getDadosSeleciornarRegistro() {
		return new SelecionarRegistroItem(id)
				.addColuna("Código produto", codigoProduto)
				.addColuna("Descrição", descricao);
	}
	
}
