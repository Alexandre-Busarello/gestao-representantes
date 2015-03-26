package application.visao.fxml.cadastros;

import java.util.List;

import org.hibernate.envers.internal.tools.MutableInteger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import application.controle.dao.ClienteDao;
import application.controle.dao.ProdutoDao;
import application.controle.negocio.SelecionarRegistroItem;
import application.interfaces.ISelecionavel;
import application.modelo.Cliente;
import application.modelo.Produto;

@SuppressWarnings("rawtypes")
public class SelecionarRegistroControle {
	
	private ObservableList<SelecionarRegistroItem> dados  = FXCollections.observableArrayList();;
	private ISelecionavel dao;
	private String codigoLinhaSelecionada = "";
	private Stage tela;
	
    @FXML
    private TableView<SelecionarRegistroItem> tabela;	
	
    @FXML
    protected void initialize() {   	
		tabela.setItems(dados);
    }
    
    private void inicializaTableProduto() {
		tabela.getColumns().add(new TableColumn<SelecionarRegistroItem, String>("Código"));	
		tabela.getColumns().add(new TableColumn<SelecionarRegistroItem, String>("Descrição"));	
		tabela.getColumns().add(new TableColumn<SelecionarRegistroItem, String>("Complemento"));
		
		for (int indice = 0; indice < tabela.getColumns().size(); indice++) {
			final int indFinal = indice;
			tabela.getColumns().get(indice).setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getColuna(indFinal).getValorColuna()));
		}	    	
    }
    
    private void inicializaTableCliente() {
		tabela.getColumns().add(new TableColumn<SelecionarRegistroItem, String>("Nome"));
		tabela.getColumns().add(new TableColumn<SelecionarRegistroItem, String>("Bairro"));
		
		for (int indice = 0; indice < tabela.getColumns().size(); indice++) {
			final int indFinal = indice;
			tabela.getColumns().get(indice).setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getColuna(indFinal).getValorColuna()));
		}			    	
    }

	public ISelecionavel getDao() {
		return dao;
	}

	public void setDao(ISelecionavel dao) {
		this.dao = dao;
		if (dao.getClass() == ProdutoDao.class) {		
			inicializaTableProduto();
			
			List<?> lista = dao.getDados();
			lista.forEach(i -> {
				SelecionarRegistroItem sel = new SelecionarRegistroItem(1);
				sel.addColuna("Código", ((Produto) i).getCodigoProduto());
				sel.addColuna("Descrição", ((Produto) i).getDescricao());
				sel.addColuna("Complemento", ((Produto) i).getComplemento());
				
				dados.add(sel);	
			});				
		}
		else if (dao.getClass() == ClienteDao.class) {
			inicializaTableCliente();
			
			List<?> lista = dao.getDados();
			lista.forEach(i -> {
				SelecionarRegistroItem sel = new SelecionarRegistroItem(1);
				sel.addColuna("Nome", ((Cliente) i).getNome());
				sel.addColuna("Bairro", ((Cliente) i).getBairro());
				
				dados.add(sel);	
			});
		}
				
	} 
	
	public String getCodigoSelecionado() {
		return codigoLinhaSelecionada;
	}
	
	public void selecionouLinha() {
		codigoLinhaSelecionada = tabela.getSelectionModel().getSelectedItem().getColunaCodigo().getValorColuna();
		tela.close();		
	}

	public Stage getTela() {
		return tela;
	}

	public void setTela(Stage tela) {
		this.tela = tela;
	}
	
}
