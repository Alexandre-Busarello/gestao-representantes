package application.visao.fxml.cadastros.produtos;

import java.util.List;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import application.TelasCadastros;
import application.controle.dao.DaoGenerico;
import application.enumeradores.TipoSimNaoEnum;
import application.modelo.Produto;
import application.modelo.Representada;

@SuppressWarnings("deprecation")
public class IncluirProdutoControle {

	private Produto produto = null;
	
    @FXML
    private TextField id;
    @FXML
    private ComboBox<Representada> representada;
    private ObservableList<Representada> representadas = FXCollections.observableArrayList();
    @FXML
    private TextField codigoProduto;
    @FXML
    private TextField descricao;    
    @FXML
    private TextField complemento;
    @FXML
    private TextField ipi;
    @FXML
    private TextField percentualRepresentante;
    @FXML
    private TextField percentualVendedor;    
    @FXML
    private ComboBox<TipoSimNaoEnum> ativo; 
    
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
    	ativo.getItems().setAll(TipoSimNaoEnum.values());
    	
    	DaoGenerico<Representada> dao = new DaoGenerico<Representada>(Representada.class);    	
		List<?> representadas = (List<?>) dao.getLista("Representada", "");
		representadas.forEach(r -> this.representadas.add((Representada) r));    	   	
    	representada.setItems(this.representadas);   	
    }
    
    public void setProduto(Produto produto) {
    	id.setText(produto.getId()+"");
    	codigoProduto.setText(produto.getCodigoProduto());
    	descricao.setText(produto.getDescricao());
    	complemento.setText(produto.getComplemento());
    	percentualRepresentante.setText(produto.getPercentualComissaoRep()+"");
    	percentualVendedor.setText(produto.getPercentualComissaoVend()+"");
    	ipi.setText(produto.getPercentualIpi()+"");
    	representada.getSelectionModel().select(produto.getRepresentada());    	
    	ativo.getSelectionModel().select(produto.isAtivo()? TipoSimNaoEnum.SIM : TipoSimNaoEnum.NAO);
    	
		this.produto = produto;
	}
    
	public void pressionouFinalizar() {
    	final Produto novoProduto;
    	if (this.produto != null) {
    		novoProduto = this.produto;
    	} else {
			try {
				novoProduto = new Produto();
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
	    	novoProduto.setCodigoProduto(codigoProduto.getText());
	    	novoProduto.setDescricao(descricao.getText());
	    	novoProduto.setComplemento(complemento.getText());
	    	novoProduto.setPercentualComissaoRep(Double.parseDouble(percentualRepresentante.getText()));
	    	novoProduto.setPercentualComissaoVend(Double.parseDouble(percentualVendedor.getText()));
	    	novoProduto.setPercentualIpi(Double.parseDouble(ipi.getText()));
	    	novoProduto.setAtivo(ativo.getSelectionModel().getSelectedItem() == TipoSimNaoEnum.SIM? true : false);
	    	novoProduto.setRepresentada(representada.getSelectionModel().getSelectedItem());
		} catch (Exception e) {
			Dialogs.create()
	        .owner(telasCadastros.getStage())
	        .title("Erro")
	        .masthead("Ocorreu um erro ao tentar inserir o cliente.")
	        .message(e.getMessage())
	        .showError();
			return;
		}
    	
    	DaoGenerico<Produto> dao = new DaoGenerico<Produto>(Produto.class);
	    if (this.produto != null) {
	    	dao.atualizarTransacionado(novoProduto);	    		
	    } else {
	    	dao.inserirTransacionado(novoProduto);
	    }

    	telasCadastros.exibeManterProdutos();
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
