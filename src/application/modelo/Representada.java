package application.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "representadas", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
public class Representada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private int id;		
	
	@Column(name="documento", unique = true, nullable = false)
	private String cnpj;

	@Column(name="nome", unique = false, nullable = false)
	private String nome;

	@Column(name="cidade", unique = false, nullable = false)
	private String cidade;

	@Column(name="uf", unique = false, nullable = false)
	private String estado;

	@Column(name="nomeabreviado", unique = false, nullable = true)
	private String nomeAbreviado;	
	
	@Column(name="endereco", unique = false, nullable = false)
	private String endereco;		
	
	@Column(name="numero", unique = false, nullable = false)
	private int numero;		
	
	@Column(name="bairro", unique = false, nullable = false)
	private String bairro;	
	
	@Column(name="cep", unique = false, nullable = true)
	private int cep;		

	@Column(name="pais", unique = false, nullable = false)
	private String pais;			
	
	@Column(name="telefone", unique = false, nullable = true)
	private String telefone;		
	
	@Column(name="fax", unique = false, nullable = true)
	private String fax;		
	
	@Column(name="celular", unique = false, nullable = true)
	private String celular;	

	@Column(name="email", unique = false, nullable = true)
	private String email;
	
	@OneToMany(mappedBy="representada", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Cliente> clientes;
	
	@OneToMany(mappedBy="representada", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Produto> produtos;
	
	@OneToMany(mappedBy="representada", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<TabelaPreco> tabelasPreco;
	
	public Representada() {
		// Construtor padrão para o HIBERNATE
	}
	
    public Representada(String cnpj, String nome, String cidade, String estado, String endereco, int numero, String bairro, String pais) throws Exception {	    	
    	setNome(nome);
        setCidade(cidade);
        setEstado(estado);        
        setEndereco(endereco);
        setNumero(numero);
        setBairro(bairro);
        setPais(pais);
        setCnpj(cnpj);
    }
 
    public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) throws Exception {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numeroInt) throws Exception {		
		this.numero = numeroInt;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) throws Exception {
    	if (!cnpj.isEmpty()) {
    		if (cnpj.length() != 14) {
    			throw new Exception("O cnpj deve ter 14 dígitos.");
    		}
    	}	
		
		this.cnpj = cnpj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) throws Exception {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) throws Exception {	
		this.estado = estado;
	}    

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) throws Exception {
		this.bairro = bairro;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) throws Exception {
		this.cep = cep;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) throws Exception {
		this.pais = pais;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return getNomeAbreviado();
	}

}
