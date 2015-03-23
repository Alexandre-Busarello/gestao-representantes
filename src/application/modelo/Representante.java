package application.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "representantes", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
public class Representante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private int id;
	
	@Column(name="tipopessoa", unique = false, nullable = false)
	private String tipoPessoa;			
	
	@Column(name="documento", unique = true, nullable = false)
	private String cnpjCpf;

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
	
    
	public Representante() {
		// Construtor padrão para o HIBERNATE
	}
	
    public Representante(String cnpjcpf, String nome, String cidade, String estado, String endereco, String numero, String bairro, String pais, String tipoPessoa) throws Exception {	    	
    	setNome(nome);
        setCidade(cidade);
        setEstado(estado);        
        setEndereco(endereco);
        setNumero(numero);
        setBairro(bairro);
        setPais(pais);
        setTipoPessoa(tipoPessoa);  
        setCnpjCpf(cnpjcpf);
    }
 
    public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) throws Exception {
    	if (endereco.isEmpty()) {
    		throw new Exception("O campo endereço é obrigatório para o cadastro de representantes.");
    	}		
		
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(String numero) throws Exception {		
    	int numeroInt = -1;
		if (numero.isEmpty()) {
    		throw new Exception("O campo número é obrigatório para o cadastro de representantes.");
    	} else {
    		try {
    			numeroInt = Integer.parseInt(numero);
    		} catch (Exception e) {
    			throw new Exception("O número informado não é válido.");
    		}
    	}		
		
		this.numero = numeroInt;
	}

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) throws Exception {
    	if (cnpjCpf.isEmpty()) {
    		throw new Exception("O campo cnpj/cpf é obrigatório para o cadastro de representantes.");
    	} else {
    		if (tipoPessoa == "F") {
    			if (cnpjCpf.length() != 11) {
    				throw new Exception("O cpf deve ter 11 dígitos.");
    			} 
    		} else {
    			if (cnpjCpf.length() != 14) {
    				throw new Exception("O cnpj deve ter 14 dígitos.");
    			}
			}
    	}		
		
		this.cnpjCpf = cnpjCpf;
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
    	if (nome.isEmpty()) {
    		throw new Exception("O campo nome é obrigatório para o cadastro de representantes.");
    	}
    	
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) throws Exception {
    	if (cidade.isEmpty()) {
    		throw new Exception("O campo cidade é obrigatório para o cadastro de representantes.");
    	}		
		
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) throws Exception {
    	if (estado.isEmpty()) {
    		throw new Exception("O campo estado é obrigatório para o cadastro de representantes.");
    	}		
		
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
    	if (bairro.isEmpty()) {
    		throw new Exception("O campo bairro é obrigatório para o cadastro de representantes.");
    	}		
		
		this.bairro = bairro;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(String cep) throws Exception {
		int cepInt = -1;
		try {
			cepInt = Integer.parseInt(cep);
		} catch (Exception e) {
			throw new Exception("O cep informado não é válido.");
		}
		
		this.cep = cepInt;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) throws Exception {
    	if (pais.isEmpty()) {
    		throw new Exception("O campo país é obrigatório para o cadastro de representantes.");
    	}		
		
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

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) throws Exception {
    	if (tipoPessoa.isEmpty()) {
    		throw new Exception("O campo tipo de pessoa é obrigatório para o cadastro de representantes.");
    	}		
		
		this.tipoPessoa = tipoPessoa;
	}

}
