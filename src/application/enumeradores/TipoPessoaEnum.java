package application.enumeradores;

public enum TipoPessoaEnum {
	FISICA("F�sica"), JURIDICA("Jur�dica");
	
    private final String display;
    
    private TipoPessoaEnum(String s) {
        display = s;
    }
    
    @Override
    public String toString() {
        return display;
    }
}
