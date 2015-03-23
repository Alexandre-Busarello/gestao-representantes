package application.enumeradores;

public enum FiltroRepresentadasEnum {
	//CNPJ, NOME, CIDADE, ESTADO;
    CNPJCPF("Documento"), NOME("Nome"), CIDADE("Cidade"), ESTADO("Estado");
    private final String display;
    
    private FiltroRepresentadasEnum(String s) {
        display = s;
    }
    
    @Override
    public String toString() {
        return display;
    }
}
