package application.enumeradores;

public enum FiltroProdutosEnum {
    REPRESENTADA("Representada"), CODIGO("C�digo"), DESCRICAO("Descri��o"), COMPLEMENTO("Complemento"), IPI("% Ipi"), ATIVO("Ativo");
    private final String display;
    
    private FiltroProdutosEnum(String s) {
        display = s;
    }
    
    @Override
    public String toString() {
        return display;
    }
}
