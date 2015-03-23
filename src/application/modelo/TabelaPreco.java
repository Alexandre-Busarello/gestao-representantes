package application.modelo;

import java.util.ArrayList;
import java.util.Date;
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

@Entity
@Table(name = "tabelaspreco", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
public class TabelaPreco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private int id;	
	
	@ManyToOne
	@JoinColumn(name="representada", unique=false, nullable = false)
	@Fetch(FetchMode.JOIN)
	private Representada representada;		
	
	@Column(name="codigotabela", length=15, unique = true, nullable = false)
	private String codigoTabela;		
	
	@Column(name="datainicio", unique = false, nullable = false)
	private Date dataInicio;

	@Column(name="datavalidade", unique = false, nullable = false)
	private Date dataValidade;	
	
	@Column(name="descricao", length=80, unique = false, nullable = false)
	private String descricao;
	
	@Column(name="ativo", unique = false, nullable = false)
	private boolean ativo;
	
	@OneToMany(mappedBy="tabelaPreco", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ItemTabelaPreco> itensTabelaPreco = new ArrayList<ItemTabelaPreco>();		

	public TabelaPreco() {
		// TODO Auto-generated constructor stub
	}
	
	public void addItem(ItemTabelaPreco item) {
		itensTabelaPreco.add(item);
	}
	
	public List<ItemTabelaPreco> getItensTabelaPreco() {
		return itensTabelaPreco;
	}

	public void setItensTabelaPreco(List<ItemTabelaPreco> itensTabelaPreco) {
		this.itensTabelaPreco = itensTabelaPreco;
	}

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

	public String getCodigoTabela() {
		return codigoTabela;
	}

	public void setCodigoTabela(String codigoTabela) {
		this.codigoTabela = codigoTabela;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}	
	
	
}
