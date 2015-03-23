package application.enumeradores;

public enum FiltroTabelaPrecoEnum {
    REPRESENTADA("Representada"), CODIGO("C�digo"), DESCRICAO("Descri��o"), DATAINICIO("Data inicio"), DATAVALIDADE("Data validade"), ATIVO("Ativo");
    private final String display;
    
    private FiltroTabelaPrecoEnum(String s) {
        display = s;
    }
    
    @Override
    public String toString() {
        return display;
    }
}
