package app.fx;

	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.scene.control.*;
	import javafx.scene.layout.BorderPane;
	import javafx.stage.Stage;
	import javafx.stage.FileChooser;
	import javafx.scene.control.Alert.AlertType;
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.Scanner;

	public class FileEditor extends Application {

	    private TextArea textArea;
	    private Label statusLabel;
	    private File currentFile;

	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage stage) {
	        // Створення елементів інтерфейсу
	        textArea = new TextArea();
	        statusLabel = new Label("Status: Ready");
	        
	        // Меню
	        MenuBar menuBar = new MenuBar();
	        
	        // Меню File
	        Menu fileMenu = new Menu("File");
	        MenuItem openItem = new MenuItem("Open");
	        MenuItem saveItem = new MenuItem("Save");
	        MenuItem saveAsItem = new MenuItem("Save As");
	        MenuItem exitItem = new MenuItem("Exit");
	        
	        fileMenu.getItems().addAll(openItem, saveItem, saveAsItem, new SeparatorMenuItem(), exitItem);
	        
	        // Меню Help
	        Menu helpMenu = new Menu("Help");
	        MenuItem aboutItem = new MenuItem("About");
	        helpMenu.getItems().add(aboutItem);
	        
	        menuBar.getMenus().addAll(fileMenu, helpMenu);
	        
	        // FileChooser
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.getExtensionFilters().addAll(
	            new FileChooser.ExtensionFilter("Text Files", "*.txt"),
	            new FileChooser.ExtensionFilter("All Files", "*.*")
	        );
	        
	        // Обробники подій
	        openItem.setOnAction(e -> {
	            File file = fileChooser.showOpenDialog(stage);
	            if (file != null) {
	                try {
	                    currentFile = file;
	                    String content = readFile(file);
	                    textArea.setText(content);
	                    statusLabel.setText("Status: Opened - " + file.getName());
	                } catch (Exception ex) {
	                    showAlert("Error", "Could not open file", ex.getMessage());
	                }
	            }
	        });
	        
	        saveItem.setOnAction(e -> {
	            if (currentFile != null) {
	                try {
	                    writeFile(currentFile, textArea.getText());
	                    statusLabel.setText("Status: Saved - " + currentFile.getName());
	                } catch (Exception ex) {
	                    showAlert("Error", "Could not save file", ex.getMessage());
	                }
	            } else {
	                saveAsItem.fire(); // Якщо файл ще не вибраний, викликаємо "Save As"
	            }
	        });
	        
	        saveAsItem.setOnAction(e -> {
	            File file = fileChooser.showSaveDialog(stage);
	            if (file != null) {
	                try {
	                    currentFile = file;
	                    writeFile(file, textArea.getText());
	                    statusLabel.setText("Status: Saved As - " + file.getName());
	                } catch (Exception ex) {
	                    showAlert("Error", "Could not save file", ex.getMessage());
	                }
	            }
	        });
	        
	        exitItem.setOnAction(e -> stage.close());
	        
	        aboutItem.setOnAction(e -> {
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("About");
	            alert.setHeaderText("Text Editor");
	            alert.setContentText("Simple Text Editor\nVersion 1.0");
	            alert.showAndWait();
	        });
	        
	        // Розміщення
	        BorderPane root = new BorderPane();
	        root.setTop(menuBar);
	        root.setCenter(textArea);
	        root.setBottom(statusLabel);
	        
	        // Налаштування сцени
	        Scene scene = new Scene(root, 800, 600);
	        stage.setScene(scene);
	        stage.setTitle("Text Editor");
	        stage.show();
	    }
	    
	    // Методи для роботи з файлами (з FileIOWriteRead)
	    private String readFile(File file) throws IOException {
	        StringBuilder content = new StringBuilder();
	        try (Scanner scanner = new Scanner(file)) {
	            while (scanner.hasNextLine()) {
	                content.append(scanner.nextLine()).append("\n");
	            }
	        }
	        return content.toString();
	    }
	    
	    private void writeFile(File file, String content) throws IOException {
	        try (FileWriter writer = new FileWriter(file)) {
	            writer.write(content);
	        }
	    }
	    
	    private void showAlert(String title, String header, String content) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(header);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	}

