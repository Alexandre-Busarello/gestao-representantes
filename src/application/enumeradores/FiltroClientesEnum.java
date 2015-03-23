package application.enumeradores;

public enum FiltroClientesEnum {
	//CNPJ, NOME, CIDADE, ESTADO;
    REPRESENTADA("Representada"), NOME("Nome"), CIDADE("Cidade"), ESTADO("Estado"), CNPJCPF("Documento"), FABRICA("Código fabrica");
    private final String display;
    
    private FiltroClientesEnum(String s) {
        display = s;
    }
    
    @Override
    public String toString() {
        return display;
    }
}
