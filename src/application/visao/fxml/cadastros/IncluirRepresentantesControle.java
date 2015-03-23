package application.visao.fxml.cadastros;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import application.TelasCadastros;
import application.controle.dao.DaoGenerico;
import application.enumeradores.TipoPessoaEnum;
import application.modelo.Representante;

@SuppressWarnings("deprecation")
public class IncluirRepresentantesControle {

	private Representante representante = null;
	
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
    private ComboBox<TipoPessoaEnum> tipoPessoa;
    @FXML
    private TextField cnpjcpf;    
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
    	fax.setText("");
    	celular.setText("");
    	tipoPessoa.getItems().addAll(TipoPessoaEnum.FISICA, TipoPessoaEnum.JURIDICA);
    	cnpjcpf.setText("");
    	email.setText("");
    	cep.setText("");
    	
    	lblTitulo.setText("Incluir representante");
    }
    
    public void setRepresentante(Representante representante) {
    	nome.setText(representante.getNome());
    	nomeAbreviado.setText(representante.getNomeAbreviado());
    	endereco.setText(representante.getEndereco());
    	bairro.setText(representante.getBairro());
    	cidade.setText(representante.getCidade());
    	estado.setText(representante.getEstado());
    	pais.setText(representante.getPais());
    	telefone.setText(representante.getTelefone());
    	fax.setText(representante.getFax());
    	numero.setText(representante.getNumero()+"");
    	celular.setText(representante.getCelular());
    	if (representante.getTipoPessoa().equals("F")) {
    		tipoPessoa.getSelectionModel().select(TipoPessoaEnum.FISICA);
    	} else {
    		tipoPessoa.getSelectionModel().select(TipoPessoaEnum.JURIDICA);
    	}
    	cnpjcpf.setText(representante.getCnpjCpf());
    	email.setText(representante.getEmail());
    	cep.setText(representante.getCep()+"");
    	
    	this.representante = representante;
    }
    
    public void pressionouFinalizar() {
    	TipoPessoaEnum tipoPessoaEnum = tipoPessoa.getSelectionModel().getSelectedItem();
    	String tipoPessoaString = "J";
    	if (tipoPessoaEnum == TipoPessoaEnum.FISICA) {
    		tipoPessoaString = "F";
    	} 
    	
    	Representante novoRep = null;
    	if (representante != null) {
    		novoRep = representante;
    	} else {
			try {
				novoRep = new Representante();
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
	    	novoRep.setTelefone(telefone.getText());
	    	novoRep.setFax(fax.getText());
	    	novoRep.setCelular(celular.getText());
	    	novoRep.setTipoPessoa(tipoPessoaString);
	    	novoRep.setCnpjCpf(cnpjcpf.getText());
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
    	
    	DaoGenerico<Representante> dao = new DaoGenerico<Representante>(Representante.class);

    	if (representante != null) {
    		dao.atualizarTransacionado(novoRep);
    	} else {
    		dao.inserirTransacionado(novoRep);
    	}
    	
    	telasCadastros.exibeManterRepresentantes();
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
