package app.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ResultGame {
	
	private Label value;
	private Button finish;
	private Button newGame;
	
	private GridPane pane;
	
	
	public ResultGame(Stage stage) {
		value = new Label();
		value.setTextFill(Color.RED);
		finish = new Button("Finish Game");
		newGame = new Button("Continue Playing");
		
		
		pane = new GridPane();
		pane.add(value, 1, 1);
		pane.add(finish, 1, 2);
		pane.add(newGame, 2, 2);
		pane.setMargin(value, new Insets(10));
		pane.setMargin(finish, new Insets(10));
		pane.setMargin(newGame, new Insets(10));
		
		pane.setAlignment(Pos.TOP_LEFT);
		
		Scene scene = new Scene(pane, 300, 310);
			
		stage.setScene(scene);
		
		finish.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle (ActionEvent ev) {
				stage.close();
			}
		});
		
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle (ActionEvent ev) {
				new StageGame(stage);
			}
		});
	}


	public void setMessage(String string, String resultButton) {
		this.value.setText(string);
		if(resultButton.equals("X")) {
			this.value.setTextFill(Color.GREEN);
		} else {
			this.value.setTextFill(Color.RED);
		}
		this.value.setFont(Font.font("Arial", FontWeight.BOLD, 19));
	}


	public void setMessage(String string) {
		this.value.setText(string);
		this.value.setTextFill(Color.BLUEVIOLET);
		this.value.setFont(Font.font("Arial", FontWeight.BOLD, 19));
	}

}
