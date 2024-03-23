package project_4;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * This method serves as the main method for the program and initiates the menucontroller page
 * 
 * @author KJ Wang, Mehdi Kamal
 */
public class Main extends Application {
	
	private static Stage primaryStage;
	
	/**
	 * This method is the start of the program
	 * @param primaryStage this is the main stage being used
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage; 
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root,900,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method launches the arguments
	 * @param args args to be launched
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
