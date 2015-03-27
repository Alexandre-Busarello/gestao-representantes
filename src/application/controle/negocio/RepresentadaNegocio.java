package application.controle.negocio;

import java.util.ArrayList;
import java.util.List;

import application.controle.dao.RepresentadaDao;
import application.modelo.Representada;


public class RepresentadaNegocio {

	private Representada representada;
	
	public RepresentadaNegocio() {
		representada = new Representada();
	}
	
	public RepresentadaNegocio(Representada representada) {
		this.representada = new Representada();
	}

	public static RepresentadaNegocio ObterRepresentadaNegocio(int idRep) {
		RepresentadaDao dao = new RepresentadaDao();
		RepresentadaNegocio representadaNegocio = new RepresentadaNegocio(dao.obter(idRep));
		return representadaNegocio;
	}
	
    public RepresentadaNegocio(String cnpj, String nome, String cidade, String estado, String endereco, String numero, String bairro, String pais) throws Exception {	    	
    	setNome(nome);
        setCidade(cidade);
        setEstado(estado);        
        setEndereco(endereco);
        setNumero(numero);
        setBairro(bairro);
        setPais(pais);
        setCnpj(cnpj);
    }	
	
	public static List<RepresentadaNegocio> ObterRepresentadaNegocioLista() {
		RepresentadaDao dao = new RepresentadaDao();
		List<Representada> lista = dao.getLista();
		List<RepresentadaNegocio> retorno = new ArrayList<RepresentadaNegocio>();
		for (Representada representada : lista) {
			retorno.add(ObterRepresentadaNegocio(representada.getId()));
		}
		return retorno;
	}	
	
 
    public String getEndereco() {
		return this.representada.getEndereco();
	}

	public void setEndereco(String endereco) throws Exception {
		representada.setEndereco(endereco);
	}

	public int getNumero() {
		return representada.getNumero();
	}

	public void setNumero(String numero) throws Exception {		
    	int numeroInt = -1;
		if (!numero.isEmpty()) {
    		try {
    			numeroInt = Integer.parseInt(numero);
    		} catch (Exception e) {
    			throw new Exception("O número informado não é válido.");
    		}
		}
		
		this.representada.setNumero(numeroInt);
	}

	public String getCnpj() {
		return this.representada.getCnpj();
	}

	public void setCnpj(String cnpj) throws Exception {
    	if (!cnpj.isEmpty()) {
    		if (cnpj.length() != 14) {
    			throw new Exception("O cnpj deve ter 14 dígitos.");
    		}
    	}	
		
    	this.representada.setCnpj(cnpj);
	}

	public int getId() {
		return this.representada.getId();
	}

	public void setId(int id) {
		this.representada.setId(id);
	}

	public String getNome() {
		return this.representada.getNome();
	}

	public void setNome(String nome) throws Exception {
    	if (nome.isEmpty()) {
    		throw new Exception("O campo nome é obrigatório para o cadastro de representantes.");
    	}
    	
    	this.representada.setNome(nome);
	}

	public String getCidade() {
		return this.representada.getCidade();
	}

	public void setCidade(String cidade) throws Exception {
		this.representada.setCidade(cidade);
	}

	public String getEstado() {
		return this.representada.getEstado();
	}

	public void setEstado(String estado) throws Exception {	
		this.representada.setEstado(estado);
	}    

	public String getNomeAbreviado() {
		return this.representada.getNomeAbreviado();
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.representada.setNomeAbreviado(nomeAbreviado);
	}

	public String getBairro() {
		return this.representada.getBairro();
	}

	public void setBairro(String bairro) throws Exception {
		this.representada.setBairro(bairro);
	}

	public int getCep() {
		return this.representada.getCep();
	}

	public void setCep(String cep) throws Exception {
		int cepInt = -1;
		if (!cep.isEmpty()) {
			try {
				cepInt = Integer.parseInt(cep);
			} catch (Exception e) {
				throw new Exception("O cep informado não é válido.");
			}
		}	
		
		this.representada.setCep(cepInt);
	}

	public String getPais() {
		return this.representada.getPais();
	}

	public void setPais(String pais) throws Exception {
		this.representada.setPais(pais);
	}

	public String getTelefone() {
		return this.representada.getTelefone();
	}

	public void setTelefone(String telefone) {
		this.representada.setTelefone(telefone);
	}

	public String getFax() {
		return this.representada.getFax();
	}

	public void setFax(String fax) {
		this.representada.setFax(fax);
	}

	public String getCelular() {
		return this.representada.getCelular();
	}

	public void setCelular(String celular) {
		this.representada.setCelular(celular);
	}

	public String getEmail() {
		return representada.getEmail();
	}

	public void setEmail(String email) {
		this.representada.setEmail(email);
	}
	
	@Override
	public String toString() {
		return getNomeAbreviado();
	}	
	
}
