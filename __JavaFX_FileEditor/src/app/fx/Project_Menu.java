package app.fx;

import java.io.File;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Project_Menu extends Application{

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		
		// MenuBar
		MenuBar menuBar = new MenuBar();
		
		Menu mFile = new Menu("File");
		Menu mAbout = new Menu("About");
		
		Menu mSubMenu = new Menu("Help");
		mAbout.getItems().add(mSubMenu);
		
		MenuItem itemOpen = new MenuItem("Open File");
		MenuItem itemWrite = new MenuItem("Write to File");
		MenuItem itemClose = new MenuItem("Exit");
		
		MenuItem itemContact = new MenuItem("Contact");
		
		MenuItem itemAbout = new MenuItem("About");
		
		mFile.getItems().add(itemOpen);
		mFile.getItems().add(itemWrite);
		mFile.getItems().add(new SeparatorMenuItem());
		mFile.getItems().add(itemClose);
		
		mAbout.getItems().add(itemAbout);
		mSubMenu.getItems().add(itemContact);
		
		menuBar.getMenus().add(mFile);
		menuBar.getMenus().add(mAbout);
		
		TextArea tArea = new TextArea();
		Label statusLabel = new Label("Status: ");
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open JFX File");
		fileChooser.setInitialFileName("jfxText.txt");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Files", "*.*"),
				new FileChooser.ExtensionFilter("Text Files", "*.txt"),
				new FileChooser.ExtensionFilter("HTML Files", "*.html")
				);
		
		//EventHandler
		mAbout.setOnAction(event -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About JFX Editor");
			alert.setHeaderText(null);
			alert.setContentText("JFX Text Editor\nVersion:0.0.1\nAuthor:Abdullah Alkhataib");
			alert.showAndWait();
		});
		
		itemOpen.setOnAction(event -> {
			File selectedFile = fileChooser.showOpenDialog(stage);
			if(selectedFile != null) {
				String filePath = selectedFile.getAbsolutePath();
				statusLabel.setText("Status: OpenFile, Path"+filePath);
			}
		});
		
		itemClose.setOnAction(event ->{
			stage.close();
		});
		

		BorderPane borderPane = new BorderPane();
		
		borderPane.setTop(menuBar);
		borderPane.setCenter(tArea);
		borderPane.setBottom(statusLabel);
		

		// Scene
		Scene scene = new Scene(borderPane, 600,500);
		stage.setScene(scene);
		
		stage.setTitle("New JFX APP!");
		stage.show();
	}

}