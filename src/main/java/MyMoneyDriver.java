package main.java;

import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.dao.TransactionDao;
import main.java.models.Transaction;

/**
 * Main class for the application. Initializes the MainView and displays the
 * application.
 * 
 * @author Matthew Ferderber
 *
 */
public class MyMoneyDriver extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create and Insert a sample element into the Transaction table
		TransactionDao dao = new TransactionDao();
		dao.insert(new Transaction("Sample Transaction", "Sample Description", new Date(),
				Math.round(Math.random() * 50)));
		// Load MainView fxml object
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main/resources/view/MainView.fxml"));
		// Add the fxml Object to a new scene
		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets().add("/main/resources/css/application.css");
		// Set the title of the application
		primaryStage.setTitle("MyMoney Application");
		// Set the scene of the application to the new Scene
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		// Display the Stage
		primaryStage.show();
	}
}
