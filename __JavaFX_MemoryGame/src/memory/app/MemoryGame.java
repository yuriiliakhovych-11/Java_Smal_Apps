package memory.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MemoryGame extends Application {
    private int firstOpenedIndex = -1;
    private int pairsFound = 0;
    private Button[] buttons;
    private ImageView[] animals;
    private ImageView[] imgClose;
    private Image imgStart;
    
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Image imgBackground = new Image(new FileInputStream("src/img/img_background.png"));
        imgStart = new Image(new FileInputStream("src/img/img0_back.png"));
        Image imgHorse = new Image(new FileInputStream("src/img/img1_horse.png"));
        Image imgCow = new Image(new FileInputStream("src/img/img2_cow.png"));
        Image imgDog = new Image(new FileInputStream("src/img/img3_dog.png"));
        Image imgCat = new Image(new FileInputStream("src/img/img4_cat.png"));
        Image imgPig = new Image(new FileInputStream("src/img/img5_Pig.png"));
        Image imgGoat = new Image(new FileInputStream("src/img/img6_Goat.png"));

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();
        hBox4.setAlignment(Pos.CENTER);

        // Ініціалізація масивів (використовуємо поля класу)
        animals = new ImageView[] {
            new ImageView(imgHorse), new ImageView(imgHorse),
            new ImageView(imgCow), new ImageView(imgCow),
            new ImageView(imgDog), new ImageView(imgDog),
            new ImageView(imgCat), new ImageView(imgCat),
            new ImageView(imgPig), new ImageView(imgPig),
            new ImageView(imgGoat), new ImageView(imgGoat)
        };

        imgClose = new ImageView[12];
        for (int i = 0; i < 12; i++) {
            imgClose[i] = new ImageView(imgStart);
            imgClose[i].setFitHeight(150);
            imgClose[i].setFitWidth(150);
        }

        buttons = new Button[12];
        for (int i = 0; i < 12; i++) {
            buttons[i] = new Button("", imgClose[i]);
            final int index = i;
            buttons[i].setOnAction(event -> handleCardClick(index));
            animals[i].setFitHeight(150);
            animals[i].setFitWidth(150);
        }

        Button btnNewGame = new Button("New Game");
        btnNewGame.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        btnNewGame.setTextFill(Color.RED);

        hBox1.getChildren().addAll(buttons[0], buttons[1], buttons[2], buttons[3]);
        hBox1.setSpacing(20);
        hBox2.getChildren().addAll(buttons[4], buttons[5], buttons[6], buttons[7]);
        hBox2.setSpacing(20);
        hBox3.getChildren().addAll(buttons[8], buttons[9], buttons[10], buttons[11]);
        hBox3.setSpacing(20);
        hBox4.getChildren().add(btnNewGame);

        gridPane.setStyle(
            "-fx-background-image: url('file:src/img/img_background.png');" +
            "-fx-background-size: cover;"
        );

        gridPane.add(hBox1, 0, 1);
        gridPane.add(hBox2, 0, 2);
        gridPane.add(hBox3, 0, 3);
        gridPane.add(hBox4, 0, 4);

        btnNewGame.setOnAction(event -> {
            btnNewGame.setDisable(true);
            Collections.shuffle(Arrays.asList(animals));
            
            for (int i = 0; i < 12; i++) {
                buttons[i].setGraphic(animals[i]);
                buttons[i].setDisable(false);  // Дозволяємо кліки на всі кнопки
            }

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> {
                for (int i = 0; i < 12; i++) {
                    buttons[i].setGraphic(imgClose[i]);
                }
                btnNewGame.setDisable(false);
                firstOpenedIndex = -1;  // Скидаємо стан гри
                pairsFound = 0;
            });
            pause.play();
        });

        Scene scene = new Scene(gridPane, 760, 610);
        stage.setScene(scene);
        stage.getIcons().add(new Image(new FileInputStream("src/img/logo.png")));
        stage.setResizable(false);
        stage.setTitle("Memory Game!");
        stage.show();
    }

    private void handleCardClick(int index) {
        if (buttons[index].getGraphic() != imgClose[index] || buttons[index].isDisable()) {
            return;
        }

        buttons[index].setGraphic(animals[index]);

        if (firstOpenedIndex == -1) {
            firstOpenedIndex = index;
        } else {
            if (animals[firstOpenedIndex].getImage().equals(animals[index].getImage())) {
                buttons[firstOpenedIndex].setDisable(true);
                buttons[index].setDisable(true);
                pairsFound++;
                
                if (pairsFound == 6) {
                    showWinWindow(); // Викликаємо метод для вікна перемоги
                }
            } else {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                int firstIndex = firstOpenedIndex;
                pause.setOnFinished(e -> {
                    buttons[firstIndex].setGraphic(imgClose[firstIndex]);
                    buttons[index].setGraphic(imgClose[index]);
                });
                pause.play();
            }
            firstOpenedIndex = -1;
        }
    }

    private void showWinWindow() {
        Stage winStage = new Stage();
        winStage.setTitle("You Win!");
        
        Label message = new Label("Congratulations! You found all pairs!");
        Button okButton = new Button("OK");
        okButton.setTextFill(Color.RED);
        okButton.setOnAction(e -> winStage.close());
        
        VBox root = new VBox(10, message, okButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(15));
        
        try {
			winStage.getIcons().add(new Image(new FileInputStream("src/img/winlogo.png")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        winStage.setScene(new Scene(root, 300, 100));
        winStage.show();
    }

}
	
	





