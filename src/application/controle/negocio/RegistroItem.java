package application.controle.negocio;

public class RegistroItem {
	
	private String nomeColuna;
	private String valorColuna;
	
	public RegistroItem(String nome, String valor) {
		this.nomeColuna = nome;
		this.valorColuna = valor;
	}

	public String getNomeColuna() {
		return nomeColuna;
	}

	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}

	public String getValorColuna() {
		return valorColuna;
	}

	public void setValorColuna(String valorColuna) {
		this.valorColuna = valorColuna;
	}
	
	
	
}
