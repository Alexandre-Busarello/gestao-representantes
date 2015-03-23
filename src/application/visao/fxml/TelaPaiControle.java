package application.visao.fxml;

import application.TelasCadastros;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class TelaPaiControle {
	
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem menuItemCadastros;
    @FXML
    private MenuItem menuItemVendas;
    
    private TelasCadastros telaCadastros;
    

    @FXML
    private void initialize() {
    	menuButton.setText("Gest�o de cadastros");
    }
    
    public void selecionouMenuItemCadastros() {
    	menuButton.setText("Gest�o de cadastros");
    	telaCadastros.exibeCadastros();
    }
    
    public void selecionouMenuItemVendas() {
    	menuButton.setText("Gest�o de vendas");
    	telaCadastros.exibeVendas();
    }

	public TelasCadastros getTelaCadastros() {
		return telaCadastros;
	}

	public void setTelaCadastros(TelasCadastros telaCadastros) {
		this.telaCadastros = telaCadastros;
	}
}
