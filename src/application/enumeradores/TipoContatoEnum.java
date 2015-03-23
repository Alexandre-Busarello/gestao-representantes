package application.enumeradores;

public enum TipoContatoEnum {
	TELEFONE("Telefone"), COMERCIAL("Comercial"), CELULAR("Celular"), EMAIL("E-mail"), OBTERTIPO("");
	
    private final String display;
    
    private TipoContatoEnum(String s) {
        display = s;
    }
    
    public TipoContatoEnum getTipo(String s) {
    	if (s.toUpperCase().equals(TELEFONE.toString().toUpperCase())) {
    		return TELEFONE;
    	} else if (s.toUpperCase().equals(COMERCIAL.toString().toUpperCase())) {
    		return COMERCIAL;
    	} else if (s.toUpperCase().equals(CELULAR.toString().toUpperCase())) {
    		return CELULAR;
    	} else { 
    		return EMAIL;
    	} 
    }        
    
    @Override
    public String toString() {
        return display;
    }
}
