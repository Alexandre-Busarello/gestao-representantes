package application.enumeradores;

public enum TipoPessoaEnum {
	FISICA("Física"), JURIDICA("Jurídica");
	
    private final String display;
    
    private TipoPessoaEnum(String s) {
        display = s;
    }
    
    @Override
    public String toString() {
        return display;
    }
}
