package application.enumeradores;

public enum TipoSimNaoEnum {
	SIM("Sim"), NAO("N�o");
	
    private final String display;
    
    private TipoSimNaoEnum(String s) {
        display = s;
    }
    
    @Override
    public String toString() {
        return display;
    }
}
