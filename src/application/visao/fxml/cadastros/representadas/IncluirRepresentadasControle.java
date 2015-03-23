package application.visao.fxml.cadastros.representadas;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import application.TelasCadastros;
import application.controle.dao.DaoGenerico;
import application.modelo.Representada;
import application.util.JavaFxMask;

@SuppressWarnings("deprecation")
public class IncluirRepresentadasControle {

	private Representada representada = null;
	
    @FXML
    private Label lblTitulo;
    
    @FXML
    private TextField nome;
    @FXML
    private TextField nomeAbreviado;
    @FXML
    private TextField endereco;
    @FXML
    private TextField numero;    
    @FXML
    private TextField bairro;
    @FXML
    private TextField cidade;
    @FXML
    private TextField estado;
    @FXML
    private TextField cep;    
    @FXML
    private TextField pais;
    @FXML
    private TextField telefone;
    @FXML
    private TextField fax;
    @FXML
    private TextField celular;
    @FXML
    private TextField cnpj;    
    @FXML
    private TextField email;    
    
    @FXML
    private Button btFinalizar;	
    @FXML
    private Button btCancelar;	  
    
    private TelasCadastros telasCadastros;
    
    public void setTelasCadastros(TelasCadastros telasCadastros) {
    	this.telasCadastros = telasCadastros;
    }
    
    @FXML
    private void initialize() {
    	nome.setText("");
    	nomeAbreviado.setText("");
    	endereco.setText("");
    	bairro.setText("");
    	cidade.setText("");
    	estado.setText("");
    	pais.setText("");
    	telefone.setText("");
    	JavaFxMask.addMask(telefone, "(  )    -     ");
    	//telefone.setMask("NNNNNNNNNNN");
    	//telefone.textProperty().addListener(new InputMaskChecker(InputMaskChecker.TELEFONE, telefone));
    	fax.setText("");
    	JavaFxMask.addMask(fax, "(  )    -     ");
    	//fax.setMask("NNNNNNNNNNN");
    	celular.setText("");
    	JavaFxMask.addMask(celular, "(  )    -     ");
    	//celular.setMask("NNNNNNNNNNN");
    	cnpj.setText("");
    	JavaFxMask.addMask(cnpj, "  .   .   /    -  ");
    	email.setText("");
    	cep.setText("");
    	
    	lblTitulo.setText("Incluir representada");
    }
    
    public void setRepresentada(Representada representada) {
    	nome.setText(representada.getNome());
    	nomeAbreviado.setText(representada.getNomeAbreviado());
    	endereco.setText(representada.getEndereco());
    	bairro.setText(representada.getBairro());
    	cidade.setText(representada.getCidade());
    	estado.setText(representada.getEstado());
    	pais.setText(representada.getPais());
    	telefone.setText(representada.getTelefone());
    	fax.setText(representada.getFax()); 	
    	numero.setText((representada.getNumero() != -1) ? representada.getNumero()+"" : "");
    	celular.setText(representada.getCelular());
    	cnpj.setText(representada.getCnpj());
    	email.setText(representada.getEmail());
    	cep.setText((representada.getCep() != -1) ? representada.getCep()+"" : "");
    	
    	this.representada = representada;
    }
    
    public void pressionouFinalizar() {
    	Representada novoRep = null;
    	if (representada != null) {
    		novoRep = representada;
    	} else {
			try {
				novoRep = new Representada();
			} catch (Exception e) {
				Dialogs.create()
		        .owner(telasCadastros.getStage())
		        .title("Erro")
		        .masthead("Ocorreu um erro ao tentar inserir o representante.")
		        .message(e.getMessage())
		        .showError();
				return;
			}
    	}
		
    	// Atribui dados opcionais
    	try {   		
    		novoRep.setNome(nome.getText());
	    	novoRep.setNomeAbreviado(nomeAbreviado.getText());
	    	novoRep.setEndereco(endereco.getText());
	    	novoRep.setNumero(numero.getText());
	    	novoRep.setBairro(bairro.getText());
	    	novoRep.setCidade(cidade.getText());
	    	novoRep.setEstado(estado.getText());
	    	novoRep.setPais(pais.getText());
	    	novoRep.setTelefone(JavaFxMask.stripMask(telefone.getText(), "(~~)~~~~-~~~~~"));
	    	novoRep.setFax(JavaFxMask.stripMask(fax.getText(), "(~~)~~~~-~~~~~"));
	    	novoRep.setCelular(JavaFxMask.stripMask(celular.getText(), "(~~)~~~~-~~~~~"));
	    	novoRep.setCnpj(cnpj.getText());
	    	novoRep.setEmail(email.getText());
			novoRep.setCep(cep.getText());
		} catch (Exception e) {
			Dialogs.create()
	        .owner(telasCadastros.getStage())
	        .title("Erro")
	        .masthead("Ocorreu um erro ao tentar inserir o representante.")
	        .message(e.getMessage())
	        .showError();
			return;
		}
    	
    	DaoGenerico<Representada> dao = new DaoGenerico<Representada>(Representada.class);

    	if (representada != null) {
    		dao.atualizarTransacionado(novoRep);
    	} else {
    		dao.inserirTransacionado(novoRep);
    	}
    	
    	telasCadastros.exibeManterRepresentadas();
    }
    
    public void pressionouCancelar() {
		Action response = Dialogs.create()
		.owner(telasCadastros.getStage())
		.title("Operação de cancelamento")
		.masthead("Os dados digitados serão cancelados.")
		.message("Deseja continuar?")
		.actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
		.showConfirm();   	
		
		if (response == Dialog.ACTION_YES) {
			telasCadastros.exibeNodeAnterior();
		} 		
    }
    
}
