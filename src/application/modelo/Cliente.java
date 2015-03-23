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

@Entity
@Table(name = "clientes", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private int id;		
	
	@ManyToOne
	@JoinColumn(name="representada", nullable = false)
	@Fetch(FetchMode.JOIN)
	private Representada representada;	

	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Contato> contatos;	
	
	@Column(name="tipopessoa", unique = false, nullable = false)
	private String tipoPessoa;			
	
	@Column(name="documento", unique = true, nullable = false)
	private String documento;

	@Column(name="nomefantasia", unique = false, nullable = false)
	private String nome;
	
	@Column(name="nomeabreviado", unique = false, nullable = true)
	private String nomeAbreviado;	
	
	@Column(name="endereco", unique = false, nullable = false)
	private String endereco;		
	
	@Column(name="numero", unique = false, nullable = false)
	private int numero;			
	
	@Column(name="bairro", unique = false, nullable = false)
	private String bairro;		
	
	@Column(name="cidade", unique = false, nullable = false)
	private String cidade;

	@Column(name="uf", unique = false, nullable = false)
	private String estado;
	
	@Column(name="cep", unique = false, nullable = true)
	private int cep;		

	@Column(name="pais", unique = false, nullable = false)
	private String pais;			
	
	@Column(name="tibrutaipi", unique = false, nullable = true)
	private boolean tributaIpi;		

	@Column(name="codigofabrica", unique = false, nullable = true)
	private String codigoFabrica;		
	
	@Column(name="celular", unique = false, nullable = true)
	private String celular;	

	@Column(name="ie", unique = false, nullable = true)
	private String ie;
	
    
	public Cliente() {
		// Construtor padrão para o HIBERNATE
	}
	
    public Cliente(String tipoPessoa, String documento, String nome, String nomeAbreviado, String endereco, String numero, String cidade, String estado, String pais, String cep) throws Exception {	    	
    	setTipoPessoa(tipoPessoa);
    	setNome(nome);
        setCidade(cidade);
        setEstado(estado);        
        setEndereco(endereco);
        setNumero(numero);
        setBairro(bairro);
        setPais(pais);
        setDocumento(documento);
    }

    public void addContato(Contato c) {
    	c.setCliente(this);
    	contatos.add(c);
    }
    
	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
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

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}	
	
	public void setNumero(String numero) {
		this.numero = Integer.parseInt(numero);
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getCep() {
		return cep;
	}
	
	public void setCep(int cep) {
		this.cep = cep;
	}

	public void setCep(String cep) {
		this.cep = Integer.parseInt(cep);
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public boolean isTributaIpi() {
		return tributaIpi;
	}

	public void setTributaIpi(boolean tributaIpi) {
		this.tributaIpi = tributaIpi;
	}

	public String getCodigoFabrica() {
		return codigoFabrica;
	}

	public void setCodigoFabrica(String codigoFabrica) {
		this.codigoFabrica = codigoFabrica;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}
 
}
