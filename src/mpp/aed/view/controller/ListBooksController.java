package mpp.aed.view.controller;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;
import mpp.aed.library.Book;
import mpp.aed.library.SystemController;

public class ListBooksController {
	
	private Stage listBooksStage;
	
	@FXML
	private TableView<Book> bookTable;
	@FXML
	private TableColumn<Book, String> tISBN;
	@FXML
	private TableColumn<Book, String> tTitle;
	@FXML
	private TableColumn<Book, String> tMaxChckLength;
	@FXML
	private TableColumn<Book, String> tNumCopies;
	@FXML
	private TableColumn<Book, String> tAuthor;
	
	private ObservableList<Book> bookData = FXCollections.observableArrayList();
	
	
	@FXML
	protected void initialize(){
		preJava8();
    }
	
	public void setData(){
		List<Book> tempList = SystemController.getInstance().getLibrary().getBooks();
		if(tempList!=null && !tempList.isEmpty()){
			bookData.addAll(tempList);
			bookTable.setItems(bookData);
		}
	}
	
	private void preJava8() {
		tISBN.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().getISBN()+"");
				return prop;
			}
		});

		tTitle.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().getTitle());
				return prop;
			}
		});
		
		tMaxChckLength.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().getMaxCheckoutDays()+" days");
				return prop;
			}
		});
		
		tNumCopies.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().getNumberOfCopies()+" copies");
				return prop;
			}
		});
		
		tAuthor.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().getAuthors().get(0).toString());
				return prop;
			}
		});
	}
	
	@FXML
	public void printBooks(){
		SystemController.getInstance().printBooks();
	}
	
	@FXML
	public void cancelBtn(){
		this.listBooksStage.hide();
	}

	public void setListBooksStage(Stage listBooksStage) {
		this.listBooksStage = listBooksStage;
	}
}
