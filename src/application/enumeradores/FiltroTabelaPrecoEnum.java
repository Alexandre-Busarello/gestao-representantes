package application.enumeradores;

public enum FiltroTabelaPrecoEnum {
    REPRESENTADA("Representada"), CODIGO("Código"), DESCRICAO("Descrição"), DATAINICIO("Data inicio"), DATAVALIDADE("Data validade"), ATIVO("Ativo");
    private final String display;
    
    private FiltroTabelaPrecoEnum(String s) {
        display = s;
    }
    
    @Override
    public String toString() {
        return display;
    }
}
