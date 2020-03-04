package it.polito.tdp.spellchecker.controller;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Dictionary model =new Dictionary(); // creo l'oggetto MODELLO
			FXMLLoader loader =new FXMLLoader(getClass().getResource("SpellChecker.fxml")); // creo l'oggetto loader, che lavora per la VISTA
			// FXMloader classe del framework JavaFX che ci permette di creare la nostra scena a partire da un file .fxml
			// questa classe ci permette di utilizzare lo sceneBuilder, creando il codice dalla scena da noi impostata
			// altrimenti dovremmo noi ogni volta creare il codice per formare la scena
			
			BorderPane root = (BorderPane)loader.load();
			SpellCheckerController controller= loader.getController(); // la vista crea il controller, quindi lo conosce
			controller.setModel(model); //imposto il modello al controller

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
