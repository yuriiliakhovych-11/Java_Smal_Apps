package app.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StageGame {
	
	private boolean round;
	private Button[]buttons;
	private int counter;
	private Stage stage;
	private Scene scene;
	
	public StageGame (Stage stage) {
		counter = 0;
		round = false;
		buttons = new Button [9];
		
		createNewButtons(buttons);
		
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(16));
		
		int c = 0;
		for (int i = 0; i<9; i++) {
			for (int j = 0; j<3; j++) {
				if (c>8) {
					break;
				}
				pane.add(buttons[c], i, j);
				c++;
			}
		}
		
		scene = new Scene (pane, 300, 305);
		
		stage.setScene(scene);
		stage.show();
		this.stage = stage;
	}
	
	
	private void createNewButtons(Button[] buttons) {
		for (int i = 0; i<9; i++) {
			Button button = new Button();
			buttons[i] = button;
			button.setPrefWidth(90);
			button.setMaxWidth(90);
			button.setPrefHeight(90);
			button.setMaxHeight(90);
			checkButton(button);
		}
	}


	private void checkButton(Button button) {
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle (ActionEvent event) {
				
				String resultButton = "";
				
				if(button.getText().isEmpty()) {
					if(!round) {
						button.setText("X");
						button.setTextFill(Color.GREEN);
						round = true;
					} else {
						button.setText("O");
						button.setTextFill(Color.RED);
						round = false;
					}
					button.setFont(Font.font("Arial", FontWeight.BOLD, 18));
					
					resultButton = button.getText();
					counter ++;
				}
				
				if (buttons[0].getText().equals(resultButton) && buttons[1].getText().equals(resultButton) && buttons[2].getText().equals(resultButton) || 
					buttons[3].getText().equals(resultButton) && buttons[4].getText().equals(resultButton) && buttons[5].getText().equals(resultButton) ||
				    buttons[6].getText().equals(resultButton) && buttons[7].getText().equals(resultButton) && buttons[8].getText().equals(resultButton) ||
				    buttons[0].getText().equals(resultButton) && buttons[3].getText().equals(resultButton) && buttons[6].getText().equals(resultButton) || 
					buttons[1].getText().equals(resultButton) && buttons[4].getText().equals(resultButton) && buttons[7].getText().equals(resultButton) ||
				    buttons[2].getText().equals(resultButton) && buttons[5].getText().equals(resultButton) && buttons[8].getText().equals(resultButton) ||
				    buttons[0].getText().equals(resultButton) && buttons[4].getText().equals(resultButton) && buttons[8].getText().equals(resultButton) || 
					buttons[2].getText().equals(resultButton) && buttons[4].getText().equals(resultButton) && buttons[6].getText().equals(resultButton) ) 
				{
					ResultGame resultGamt = new ResultGame (stage);
					resultGamt.setMessage(resultButton + " wins the game", resultButton);
				} else if (counter >= 9){
					ResultGame resultGamt = new ResultGame (stage);
					resultGamt.setMessage("This is Draw!");
					
				}
				
			}
		});
		
	}

}
