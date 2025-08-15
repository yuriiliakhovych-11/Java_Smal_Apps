package app.fx;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game_XO_JavaFX extends Application{

	public static void main(String[] args) {
		launch (args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.BASELINE_CENTER);
		pane.setPadding(new Insets(20));
		
		Label playerOne = new Label("Player 1");
		Label playerTwo = new Label("Player 2");
		
		TextField playerOneText = new TextField();
		TextField playerTwoText = new TextField();
		
		Label needAnName = new Label("");
		
		Button letsPlayButton = new Button("Go!");
		letsPlayButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent ev) {
				if(playerOneText.getText().isEmpty() || playerTwoText.getText().isEmpty()) {
					needAnName.setText("Please, enter the name(s)!");
					needAnName.setTextFill(Color.RED);
					return;
				}
				
				new StageGame(stage);			
			}
		});
		
		
		playerOneText.setOnKeyPressed(new EventHandler<KeyEvent> () {
			
			@Override
			public void handle(KeyEvent ev) {
				needAnName.setText("");
			}
			
		});
		
		playerTwoText.setOnKeyPressed(new EventHandler<KeyEvent> () {
			
			@Override
			public void handle(KeyEvent ev) {
				needAnName.setText("");
			}
			
		});
		
		pane.add(playerOne, 0, 0);
		pane.add(playerOneText, 1, 0);
		pane.add(playerTwo, 0, 1);
		pane.add(playerTwoText, 1, 1);
		pane.add(needAnName, 0, 2);
		pane.add(letsPlayButton, 0, 3);
		
		GridPane.setMargin(playerOne, new Insets(10));
		GridPane.setMargin(playerOneText, new Insets(10));
		GridPane.setMargin(playerTwo, new Insets(10));
		GridPane.setMargin(playerTwoText, new Insets(10));
		GridPane.setMargin(needAnName, new Insets(10));
		GridPane.setMargin(letsPlayButton, new Insets(10));
		
		
		
		Scene scene = new Scene(pane, 300, 310);
		
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
		
		stage.getIcons().add(new Image(new FileInputStream("src/icon.png")));
		stage.setTitle("Tic Tac Toe");

		
	}

}
