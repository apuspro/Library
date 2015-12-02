package mpp.aed.view.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;
import mpp.aed.library.Author;
import mpp.aed.library.Book;

public class AuthorController {

	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField address;
	@FXML
	private TextField phoneNumber;
	@FXML
	private TextField credentials;
	@FXML
	private TextArea bio;
	
	@FXML
	private TableView<Author> authorTable;
	@FXML
	private TableColumn<Author, String> tNameColumn;
	@FXML
	private TableColumn<Author, String> tAddressColumn;
	@FXML
	private TableColumn<Author, String> tPhoneColumn;
	
	private Stage authorStage;
	
	private Book book;
	
	private ObservableList<Author> authorData = FXCollections.observableArrayList();
	
	
	@FXML
	protected void initialize(){
		preJava8();
    }
	
	@FXML
	public void addAuthor(){
		Author aAuthor = new Author(this.firstName.getText(), this.lastName.getText(), this.address.getText(),
				Integer.parseInt(this.phoneNumber.getText()), this.credentials.getText(), this.bio.getText());
		this.book.addAuthor(aAuthor);
		
		this.authorData.add(aAuthor);
	}
	
	@FXML
	public void cancelBtn(){
		this.authorStage.hide();
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setAuthorStage(Stage authorStage) {
		this.authorStage = authorStage;
	}
	
	public void setData(){
		if(this.book.getAuthors()!=null || !this.book.getAuthors().isEmpty()){
			authorData.addAll(book.getAuthors());
			authorTable.setItems(authorData);
		}
	}
	
	private void preJava8() {
		tNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Author, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Author, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().getFirstName()+" "+param.getValue().getLastName());
				return prop;
			}
		});

		tAddressColumn.setCellValueFactory(new Callback<CellDataFeatures<Author, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Author, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().getAddress());
				return prop;
			}
		});
		
		tPhoneColumn.setCellValueFactory(new Callback<CellDataFeatures<Author, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Author, String> param) {
				StringProperty prop = new SimpleStringProperty(param.getValue().getPhoneNumber()+"");
				return prop;
			}
		});
	}
}
