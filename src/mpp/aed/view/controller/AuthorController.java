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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import mpp.aed.library.Author;
import mpp.aed.library.Book;
import mpp.aed.view.rulsets.RuleException;
import mpp.aed.view.rulsets.RuleSet;
import mpp.aed.view.rulsets.RuleSetFactory;

public class AuthorController {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField phoneNumberField;
	@FXML
	private TextField credentialsField;
	@FXML
	private TextArea bioArea;
	
	@FXML
	private TableView<Author> authorTable;
	@FXML
	private TableColumn<Author, String> tNameColumn;
	@FXML
	private TableColumn<Author, String> tAddressColumn;
	@FXML
	private TableColumn<Author, String> tPhoneColumn;
	@FXML
	private Text resultMsg;
	
	private Stage authorStage;
	
	private Book book;
	
	private ObservableList<Author> authorData = FXCollections.observableArrayList();
	
	
	@FXML
	protected void initialize(){
		preJava8();
    }
	
	@FXML
	public void addAuthor(){
		RuleSet authorRules = RuleSetFactory.getRuleSet(this);
		try{
			authorRules.applyRules(this);
			Author aAuthor = new Author(this.firstNameField.getText(), this.lastNameField.getText(), this.addressField.getText(),
					Long.parseLong(this.phoneNumberField.getText()), this.credentialsField.getText(), this.bioArea.getText());
			this.book.addAuthor(aAuthor);
			
			this.authorData.add(aAuthor);
			authorTable.setItems(authorData);
			
			resultMsg.setFill(Color.GREEN);
			resultMsg.setText("Author "+this.firstNameField.getText()+" Added");
			System.out.println("Author "+this.firstNameField.getText()+" Added");
		}catch(RuleException e) {
			resultMsg.setFill(Color.RED);
			resultMsg.setText(e.getMessage());
		}
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
		if(this.book.getAuthors()!=null && !this.book.getAuthors().isEmpty()){
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

	public TextField getFirstNameField() {
		return firstNameField;
	}

	public TextField getLastNameField() {
		return lastNameField;
	}

	public TextField getAddressField() {
		return addressField;
	}

	public TextField getPhoneNumberField() {
		return phoneNumberField;
	}

	public TextField getCredentialsField() {
		return credentialsField;
	}

	public TextArea getBioArea() {
		return bioArea;
	}

	public Book getBook() {
		return book;
	}
}
