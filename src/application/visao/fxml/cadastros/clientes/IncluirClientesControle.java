package application.visao.fxml.cadastros.clientes;

import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import application.TelasCadastros;
import application.controle.dao.DaoGenerico;
import application.enumeradores.TipoContatoEnum;
import application.enumeradores.TipoPessoaEnum;
import application.enumeradores.TipoSimNaoEnum;
import application.modelo.Cliente;
import application.modelo.Contato;
import application.modelo.Representada;
import application.util.ImageButton;
import application.visao.fxml.cadastros.ManterCadastroControle;

@SuppressWarnings("deprecation")
public class IncluirClientesControle extends ManterCadastroControle {

	private Cliente cliente = null;
    
	// ABA Contatos
    @FXML
    private TableView<Contato> contatos;	
    private ObservableList<Contato> contatoDados = FXCollections.observableArrayList();
    private ArrayList<Contato> contatosRemovidos = new ArrayList<Contato>();
    @FXML
    private TableColumn<Contato, String> tipoColuna;
    @FXML
    private TableColumn<Contato, String> valorColuna;
    @FXML
    private TableColumn<Contato, String> contatoColuna;
    @FXML
    private ComboBox<TipoContatoEnum> tipoContato;
    @FXML
    private TextField valorContato;    
    @FXML
    private TextField nomeContato;
    @FXML
    private TableColumn<Cliente, String> acoesColuna;       
    
    
    // Aba GERAIS
    @FXML
    private TextField id;
    @FXML
    private ComboBox<Representada> representada;
    private ObservableList<Representada> representadas = FXCollections.observableArrayList();
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
    private ComboBox<TipoPessoaEnum> tipoPessoa;    
    @FXML
    private TextField documento;
    @FXML
    private ComboBox<TipoSimNaoEnum> tributaIPI;    
    @FXML
    private TextField codigoFabrica;   
    
    
    // Rodapé
    @FXML
    private Button btFinalizar;	
    @FXML
    private Button btCancelar;	  
 
    
    private TelasCadastros telasCadastros;
    
    public void setTelasCadastros(TelasCadastros telasCadastros) {
    	this.telasCadastros = telasCadastros;
    }
    
    @FXML
	protected void initialize() {
    	tipoContato.getItems().setAll(TipoContatoEnum.values());
    	contatos.setItems(contatoDados);

    	tipoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo().toString()));
    	tipoColuna.setCellFactory(ComboBoxTableCell.forTableColumn(TipoContatoEnum.CELULAR.toString(), TipoContatoEnum.TELEFONE.toString(), TipoContatoEnum.COMERCIAL.toString(), TipoContatoEnum.EMAIL.toString()));
    	tipoColuna.setOnEditCommit(
    		new EventHandler<TableColumn.CellEditEvent<Contato,String>>() {
    			public void handle(TableColumn.CellEditEvent<Contato,String> event) {
    				((Contato) event.getTableView().getItems().get(event.getTablePosition().getRow())).setTipo(TipoContatoEnum.OBTERTIPO.getTipo(event.getNewValue()));
    				//tipoContato.setValue(TipoContatoEnum.OBTERTIPO.getTipo(event.getNewValue()));
    			};
			}
    	);
    	
    	valorColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValor()));
    	valorColuna.setCellFactory(TextFieldTableCell.forTableColumn());
    	valorColuna.setOnEditCommit(
    		new EventHandler<TableColumn.CellEditEvent<Contato,String>>() {
    			public void handle(TableColumn.CellEditEvent<Contato,String> event) {
    				((Contato) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValor(event.getNewValue());
    				//valorContato.setText(event.getNewValue());
    			}
    		}	
    	); 	
    	
    	contatoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContato()));
    	contatoColuna.setCellFactory(TextFieldTableCell.forTableColumn());
    	contatoColuna.setOnEditCommit(
        	new EventHandler<TableColumn.CellEditEvent<Contato,String>>() {
        		public void handle(TableColumn.CellEditEvent<Contato,String> event) {
        			((Contato) event.getTableView().getItems().get(event.getTablePosition().getRow())).setContato(event.getNewValue());
        			//contatoColuna.setText(event.getNewValue());
        		}
        	}	
        ); 	    	

    	id.setText("");
    	nome.setText("");
    	nomeAbreviado.setText("");
    	endereco.setText("");
    	numero.setText("");
    	bairro.setText("");
    	cidade.setText("");
    	estado.setText("");
    	pais.setText("");
    	cep.setText("");
    	documento.setText("");
    	codigoFabrica.setText("");
    	
    	DaoGenerico<Representada> dao = new DaoGenerico<Representada>(Representada.class);    	
		List<?> representadas = (List<?>) dao.getLista("Representada", "");
		representadas.forEach(r -> this.representadas.add((Representada) r));    	   	
    	representada.setItems(this.representadas);
    	
    	tributaIPI.getItems().addAll(TipoSimNaoEnum.SIM, TipoSimNaoEnum.NAO);    
    	
    	tipoPessoa.getItems().addAll(TipoPessoaEnum.JURIDICA, TipoPessoaEnum.FISICA);
    	
    	super.adicionarAcoes(false, true);
    }
    
    public void setCliente(Cliente cliente) {
    	id.setText(cliente.getId()+"");
    	nome.setText(cliente.getNome());
    	nomeAbreviado.setText(cliente.getNomeAbreviado());
    	endereco.setText(cliente.getEndereco());
    	numero.setText((cliente.getNumero() != -1) ? cliente.getNumero()+"" : "");
    	bairro.setText(cliente.getBairro());
    	cidade.setText(cliente.getCidade());
    	estado.setText(cliente.getEstado());
    	pais.setText(cliente.getPais());
    	cep.setText((cliente.getCep() != -1) ? cliente.getCep()+"" : "");
    	documento.setText(cliente.getDocumento());
    	codigoFabrica.setText(cliente.getCodigoFabrica());
    	representada.getSelectionModel().select(cliente.getRepresentada());
    	if (cliente.isTributaIpi()) {
    		tributaIPI.getSelectionModel().select(TipoSimNaoEnum.SIM);
    	} else {
    		tributaIPI.getSelectionModel().select(TipoSimNaoEnum.NAO);
    	}
    	if (cliente.getTipoPessoa().equals("J")) {
    		tipoPessoa.getSelectionModel().select(TipoPessoaEnum.JURIDICA);
    	} else {
    		tipoPessoa.getSelectionModel().select(TipoPessoaEnum.FISICA);
    	}
    	
    	cliente.getContatos().forEach(c -> contatoDados.add(c));
    	contatos.setItems(contatoDados);
    	
    	this.cliente = cliente;
    }
    
    public void pressionouFinalizar() {
    	final Cliente novoCliente;
    	if (this.cliente != null) {
    		novoCliente = this.cliente;
    	} else {
			try {
				novoCliente = new Cliente();
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
	    	novoCliente.setNome(nome.getText());
	    	novoCliente.setNomeAbreviado(nomeAbreviado.getText());
	    	novoCliente.setEndereco(endereco.getText());
	    	novoCliente.setNumero(numero.getText());
	    	novoCliente.setBairro(bairro.getText());
	    	novoCliente.setCidade(cidade.getText());
	    	novoCliente.setEstado(estado.getText());
	    	novoCliente.setPais(pais.getText());
	    	novoCliente.setCep(cep.getText());
	    	novoCliente.setDocumento(documento.getText());
	    	novoCliente.setCodigoFabrica(codigoFabrica.getText());
	    	novoCliente.setRepresentada(representada.getSelectionModel().getSelectedItem());
	    	if (tributaIPI.getSelectionModel().getSelectedItem() == TipoSimNaoEnum.SIM) {
	    		novoCliente.setTributaIpi(true);
	    	} else {
	    		novoCliente.setTributaIpi(false);
	    	}
	    	if (tipoPessoa.getSelectionModel().getSelectedItem() == TipoPessoaEnum.JURIDICA) {
	    		novoCliente.setTipoPessoa("J");
	    	} else {
	    		novoCliente.setTipoPessoa("F");
	    	}

	    	contatoDados.forEach(c -> {
	    		if (c.getCliente() == null) {
	    			novoCliente.addContato(c);
	    		}
	    	});	   
	    	
	    	contatosRemovidos.forEach(c -> novoCliente.getContatos().remove(c));
		} catch (Exception e) {
			Dialogs.create()
	        .owner(telasCadastros.getStage())
	        .title("Erro")
	        .masthead("Ocorreu um erro ao tentar inserir o cliente.")
	        .message(e.getMessage())
	        .showError();
			return;
		}
    	
    	DaoGenerico<Cliente> dao = new DaoGenerico<Cliente>(Cliente.class);
    	DaoGenerico<Contato> daoContato = new DaoGenerico<Contato>(Contato.class);

    	dao.iniciarTransacao();
    	try {
	    	if (this.cliente != null) {
	    		contatosRemovidos.forEach(c -> daoContato.deletar(c));
	    		dao.atualizar(novoCliente);	    		
	    	} else {
	    		dao.inserir(novoCliente);
	    	}
	    	dao.commit();
    	} catch (Exception e) {
    		dao.rollback();
    		throw e;
    	}
    	
    	contatosRemovidos.clear();
    	telasCadastros.exibeManterClientes();
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
    
    public void pressionouAddContato() {	
    	try {
			contatoDados.add(new Contato(tipoContato.getValue(), valorContato.getText(), nomeContato.getText()));
		} catch (Exception e) {
			Dialogs.create()
	        .owner(telasCadastros.getStage())
	        .title("Erro")
	        .masthead("Ocorreu um erro ao tentar inserir o contato.")
	        .message(e.getMessage())
	        .showError();
		}
    	tipoContato.setValue(null);
    	valorContato.setText("");
    	nomeContato.setText("");
    	tipoContato.requestFocus();
    }
    
    @Override
    protected EventHandler<ActionEvent> pressionouExcluirItem() {
    	return new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				Action response = Dialogs.create()
						.owner(telasCadastros.getStage())
						.title("Operação de exclusão")
						.masthead("A linha selecionado será excluida.")
						.message("Deseja continuar?")
						.actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
						.showConfirm();   	
						
						if (response == Dialog.ACTION_NO) {
							return;
						} 					
				
				// Obtem o botão que foi clicado e na sequencia o HBox que se encontra o botão
				ImageButton imgBt = (ImageButton) event.getSource();
				HBox hb = (HBox) imgBt.getParent();
				// Através do HBox obtem o TableCell em que o HBox esta
				TableCell<?, ?> tblCell = (TableCell<?, ?>) hb.getParent();

				// Com o tablecell em mão conseguimos obter o index do representante configurado para a linha do botão		
				contatosRemovidos.add(contatos.getItems().get(tblCell.getIndex()));
				contatos.getItems().remove(tblCell.getIndex());				
			}
		};
    }
    
}
