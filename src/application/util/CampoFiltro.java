package application.util;

public class CampoFiltro {
	
	private Class<?> entidade;
	private String campoLigacao;
	private String nomeCampo;
	private Class<?> tipoCampo;
	private String alias;
	
	public CampoFiltro(String ent, String nome, Class<?> tipo, String alias) {
		this.campoLigacao = ent;
		this.nomeCampo = nome;
		this.tipoCampo = tipo;
		this.alias = alias;
	}
	
	public CampoFiltro() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCampoLigacao() {
		return campoLigacao;
	}
	public void setCampoLigacao(String campoLigacao) {
		this.campoLigacao = campoLigacao;
	}
	public String getNomeCampo() {
		return nomeCampo;
	}
	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}
	public Class<?> getTipoCampo() {
		return tipoCampo;
	}
	public void setTipoCampo(Class<?> tipoCampo) {
		this.tipoCampo = tipoCampo;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Class<?> getEntidade() {
		return entidade;
	}

	public void setEntidade(Class<?> entidade) {
		this.entidade = entidade;
	}
	
}
