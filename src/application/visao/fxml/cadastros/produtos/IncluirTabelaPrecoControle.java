package application.visao.fxml.cadastros.produtos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import application.TelasCadastros;
import application.controle.dao.DaoGenerico;
import application.controle.dao.ProdutoDao;
import application.controle.dao.TabelaPrecoDao;
import application.enumeradores.TipoSimNaoEnum;
import application.modelo.ItemTabelaPreco;
import application.modelo.Produto;
import application.modelo.Representada;
import application.modelo.TabelaPreco;
import application.util.JavaFX;

@SuppressWarnings("deprecation")
public class IncluirTabelaPrecoControle {
	
	private TabelaPreco tabelaPreco = new TabelaPreco();
	private boolean isEdicao = false;
	
	// GUIA DADOS GERAIS
    @FXML
    private TextField id;
    @FXML
    private ComboBox<Representada> representada;
    private ObservableList<Representada> dadosRepresentadas = FXCollections.observableArrayList();
    @FXML
    private TextField codigoTabelaPreco;
    @FXML
    private TextField descricao;    
    @FXML
    private TextField dataInicio;
    @FXML
    private TextField dataValidade;
    @FXML
    private ComboBox<TipoSimNaoEnum> ativo; 
    
    // GUIA PRODUTOS
    private ArrayList<ItemTabelaPreco> itensRemovidos = new ArrayList<ItemTabelaPreco>();
    private ObservableList<ItemTabelaPreco> dadosItemTabPreco = FXCollections.observableArrayList();
    @FXML
    private TableView<ItemTabelaPreco> tabelaItemTabPreco;
    @FXML
    private TableColumn<ItemTabelaPreco, String> colunaCodigoItem;
    @FXML 
    private TableColumn<ItemTabelaPreco, String> colunaDescricaoItem;
    @FXML
    private TextField filtroItens;
    @FXML
    private TextField codigoItem;
    @FXML
    private TextField descricaoItem;
    @FXML
    private TextField qtdMinimaItem;
    @FXML
    private TextField precoVendaItem;
    @FXML
    private ComboBox<TipoSimNaoEnum> ativoItem;
    
    
    // Rodapé
    @FXML
    private Button btFinalizar;	
    @FXML
    private Button btCancelar;	  
 
    
    private TelasCadastros telasCadastros;	
    
    public void setTelasCadastros(TelasCadastros telasCadastros) {
    	this.telasCadastros = telasCadastros;
    }
    
    private void resetarInformacoes() {
    	codigoItem.setText("");
    	descricaoItem.setText("");
    	qtdMinimaItem.setText("");
    	precoVendaItem.setText("");
    	ativo.getSelectionModel().clearSelection();
    	
    	codigoItem.requestFocus();
    }
    
    private void desativaCamposGuiaProdutos() {
    	codigoItem.setEditable(false);
    	descricaoItem.setEditable(false);
    	qtdMinimaItem.setEditable(false);
    	precoVendaItem.setEditable(false);
    }
    
    private void ativaCamposGuiaProdutos() {
    	codigoItem.setEditable(true);
    	descricaoItem.setEditable(true);
    	qtdMinimaItem.setEditable(true);
    	precoVendaItem.setEditable(true);
    }    
    
    @FXML
	protected void initialize() {
    	ativo.getItems().setAll(TipoSimNaoEnum.values());
    	
    	DaoGenerico<Representada> dao = new DaoGenerico<Representada>(Representada.class);    	
		List<?> representadas = (List<?>) dao.getLista("Representada", "");
		representadas.forEach(r -> this.dadosRepresentadas.add((Representada) r));    	   	
    	representada.setItems(this.dadosRepresentadas);   	
    	
    	tabelaItemTabPreco.setItems(dadosItemTabPreco);	
    	
    	colunaCodigoItem.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduto().getCodigoProduto()));
    	colunaDescricaoItem.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduto().getDescricao()));		    	    	    	
    	
    	// Evento ao perder o foco do campo
    	codigoItem.focusedProperty().addListener((observable, oldValue, newValue) -> {
    		// Se perdeu o foco
    		if (!newValue) {
    			if (!codigoItem.getText().isEmpty()) {
	    			ProdutoDao daoProduto = new ProdutoDao();
	    			Produto p = daoProduto.buscarProdutoCodigo(codigoItem.getText());
	    			if (p != null) {
	    				descricaoItem.setText(p.getDescricao());
	    			} else {
	    				Dialogs.create()
	    		        .owner(telasCadastros.getStage())
	    		        .title("Erro")
	    		        .masthead("Produto não localizado.")
	    		        .message("O produto de código " + codigoItem.getText() + " não foi localizado na base de dados.")
	    		        .showError();   
	    				
	    				resetarInformacoes();
	    			}
    			}
    		}
    	});
    	
    	tabelaItemTabPreco.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if (tabelaItemTabPreco.getSelectionModel().getSelectedItem() != null) {
    			codigoItem.setText(newValue.getProduto().getCodigoProduto());
    			descricaoItem.setText(newValue.getProduto().getDescricao());
    			qtdMinimaItem.setText(newValue.getQtdMinima()+"");
    			precoVendaItem.setText(newValue.getPrecoUnitario()+"");
    			ativoItem.getSelectionModel().select(newValue.isAtivo()? TipoSimNaoEnum.SIM: TipoSimNaoEnum.NAO);
    		}
    		ativaCamposGuiaProdutos();
    	});
    	
    	ativoItem.getItems().setAll(TipoSimNaoEnum.values());
    	
    	desativaCamposGuiaProdutos();
    }
    
    public void setTabelaPreco(TabelaPreco tabPreco) {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
    	id.setText(tabPreco.getId()+"");
    	codigoTabelaPreco.setText(tabPreco.getCodigoTabela());
    	descricao.setText(tabPreco.getDescricao());
    	dataInicio.setText(sdf.format(tabPreco.getDataInicio()));
    	dataValidade.setText(sdf.format(tabPreco.getDataValidade()));	
    	representada.getSelectionModel().select(tabPreco.getRepresentada());    	
    	ativo.getSelectionModel().select(tabPreco.isAtivo()? TipoSimNaoEnum.SIM : TipoSimNaoEnum.NAO);
    	
    	dadosItemTabPreco = new TabelaPrecoDao().buscarItensTabelaPreco(tabPreco.getId());
    	tabelaItemTabPreco.setItems(dadosItemTabPreco);	
    	
		this.tabelaPreco = tabPreco;
		this.isEdicao = true;
	}
    
    public void pressionouFiltrarItens() {
    	final ObservableList<ItemTabelaPreco> dadosItemNovo = new TabelaPrecoDao().buscarItensTabelaPreco(tabelaPreco.getId());
    	if (!filtroItens.getText().equals("")) {
    		dadosItemNovo.clear();
	    	dadosItemTabPreco.forEach(i -> {
	    		if ((i.getProduto().getCodigoProduto().contains(filtroItens.getText())) ||
	    			(i.getProduto().getDescricao().contains(filtroItens.getText()))){
	    			dadosItemNovo.add(i);
	    		}
	    	});
    	}
    	dadosItemTabPreco = dadosItemNovo;
    	tabelaItemTabPreco.setItems(dadosItemNovo);
    }
    
    public void pressionouAddItem() {
    	ItemTabelaPreco item = new ItemTabelaPreco();
    	Produto p = new Produto();
    	p.setCodigoProduto("");
    	p.setDescricao("");
    	item.setProduto(p);
    	dadosItemTabPreco.add(item);
    	
    	tabelaItemTabPreco.getSelectionModel().selectLast();
    	
    	ativaCamposGuiaProdutos();
    	// Ele reseta as informações para iniciar uma nova inclusão
    	resetarInformacoes();  	
    }
    
    public void pressionouGravarItem() {
    	try {
    		Produto p = new ProdutoDao().buscarProdutoCodigo(codigoItem.getText());
    		ItemTabelaPreco item = tabelaItemTabPreco.getSelectionModel().getSelectedItem();
			item.setProduto(p);
			item.setTabelaPreco(tabelaPreco);
			item.setQtdMinima(Double.parseDouble(qtdMinimaItem.getText()));
			item.setPrecoUnitario(Double.parseDouble(precoVendaItem.getText()));
			item.setAtivo((ativoItem.getValue() == TipoSimNaoEnum.SIM)? true: false);	    				
			
			JavaFX.refreshTableView(tabelaItemTabPreco);		
			//desativaCamposGuiaProdutos();
			
			tabelaItemTabPreco.getSelectionModel().select(item);	
		} catch (Exception e) {
			Dialogs.create()
	        .owner(telasCadastros.getStage())
	        .title("Erro")
	        .masthead("Ocorreu um erro ao tentar inserir o produto.")
	        .message(e.getMessage())
	        .showError();
		}    	
    }
    
    public void pressionouExcluirItem() {
		Action response = Dialogs.create()
				.owner(telasCadastros.getStage())
				.title("Operação de exclusão")
				.masthead("A item selecionado será excluido.")
				.message("Deseja continuar?")
				.actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
				.showConfirm();   	
				
		if (response == Dialog.ACTION_NO) {
			return;
		} 					
		
		itensRemovidos.add(tabelaItemTabPreco.getSelectionModel().getSelectedItem());
		tabelaItemTabPreco.getItems().remove(tabelaItemTabPreco.getSelectionModel().getSelectedItem());
    }
    
	public void pressionouFinalizar() {		
		if (dadosItemTabPreco.isEmpty()) {
			Dialogs.create()
	        .owner(telasCadastros.getStage())
	        .title("Erro")
	        .masthead("Ocorreu um erro ao tentar inserir a tabela de preço.")
	        .message("É necessário informar ao menos um produto para cadastrar a tabela de preço.")
	        .showError();
			return;			
		}
		
    	// Atribui dados opcionais
    	try {   			
	    	tabelaPreco.setCodigoTabela(codigoTabelaPreco.getText());
	    	tabelaPreco.setDescricao(descricao.getText());
	    	tabelaPreco.setDataInicio((Date)new SimpleDateFormat("dd/MM/yyyy").parse(dataInicio.getText()));
	    	tabelaPreco.setDataValidade((Date)new SimpleDateFormat("dd/MM/yyyy").parse(dataValidade.getText()));
	    	tabelaPreco.setAtivo(ativo.getSelectionModel().getSelectedItem() == TipoSimNaoEnum.SIM? true : false);
	    	tabelaPreco.setRepresentada(representada.getSelectionModel().getSelectedItem());
	    	
	    	dadosItemTabPreco.forEach(i -> {
	    		if (i.getId() == 0) {
	    			tabelaPreco.addItem(i);
	    		}
	    	});
	    	
	    	itensRemovidos.forEach(i -> tabelaPreco.getItensTabelaPreco().remove(i));
		} catch (Exception e) {
			Dialogs.create()
	        .owner(telasCadastros.getStage())
	        .title("Erro")
	        .masthead("Ocorreu um erro ao tentar inserir a tabela de preço .")
	        .message(e.getMessage())
	        .showError();
			e.printStackTrace();
			return;
		}
    	
    	DaoGenerico<TabelaPreco> dao = new DaoGenerico<TabelaPreco>(TabelaPreco.class);
    	DaoGenerico<ItemTabelaPreco> daoItem = new DaoGenerico<ItemTabelaPreco>(ItemTabelaPreco.class);
	    if (this.isEdicao) {
	    	dao.iniciarTransacao();
	    	try {
	    		itensRemovidos.forEach(i -> daoItem.deletar(i));
	    		dao.atualizar(tabelaPreco);	    
	    		dao.commit();
	    	} catch (Exception e) {
	    		dao.rollback();
	    		e.printStackTrace();
	    	}
	    } else {
	    	dao.inserirTransacionado(tabelaPreco);
	    }

    	telasCadastros.exibeManterTabelasPreco();
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
			dadosItemTabPreco.clear();
			telasCadastros.exibeManterTabelasPreco();
		} 		
    }    
	
}
