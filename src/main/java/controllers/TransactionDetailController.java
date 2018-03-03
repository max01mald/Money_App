package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.java.dao.TransactionDao;
import main.java.models.Transaction;

/**
 * Displays a detailed view of a transaction. Allows the user to update the
 * transaction's details.
 * 
 * @author Matthew Ferderber
 *
 */
public class TransactionDetailController implements Initializable {

	@FXML
	private ObjectProperty<Transaction> transactionProperty = new SimpleObjectProperty<>();
	@FXML
	private Button editTransactionButton;
	@FXML
	private Button saveTransactionButton;
	@FXML
	private GridPane editPane;
	@FXML
	private GridPane detailPane;

	@FXML
	private TextField nameField;
	@FXML
	private TextField typeField;
	@FXML
	private TextField descriptionField;
	@FXML
	private TextField amountField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setTransaction(Transaction transaction) {
		this.transactionProperty.set(transaction);
		// When the transaction is set, reset view
		detailPane.setVisible(true);
		editPane.setVisible(false);

	}

	@FXML
	private void saveTransaction() {
		TransactionDao dao = new TransactionDao();
		Transaction t = transactionProperty.get();
		String name = nameField.getText();
		String type = typeField.getText();
		double amount = 0.0;
		String description = descriptionField.getText();
		try {
			amount = Double.parseDouble(amountField.getText());
		} catch (Exception ex) {
			// TODO: Display validation error
		}
		t.setName(name);
		t.setType(type);
		t.setAmount(amount);
		t.setDescription(description);

		if (transactionProperty.get() == null) {
			dao.insert(t);
		} else {
			dao.updateTransaction(t);
		}
		editPane.setVisible(false);
		detailPane.setVisible(true);
	}

	@FXML
	private void cancelUpdate() {
		editPane.setVisible(false);
		detailPane.setVisible(true);
	}

	@FXML
	private void editTransaction() {
		editPane.setVisible(true);
		detailPane.setVisible(false);
		Transaction t = transactionProperty.get();
		if (t != null) {
			nameField.setText(t.getName());
			typeField.setText(t.getType());
			amountField.setText(String.valueOf(t.getAmount()));
			descriptionField.setText(t.getDescription());
		}
	}

	public ObjectProperty<Transaction> transactionProperty() {
		return transactionProperty;
	}

	public Transaction getTransaction() {
		return transactionProperty.get();
	}
}
