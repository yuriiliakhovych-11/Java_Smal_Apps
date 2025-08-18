package app.fx;


import java.io.FileInputStream;
import java.sql.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DBAppFX extends Application{

	public static void main(String[] args) {
		
		launch(args);
		


	}

	private Connection conn = null;
	@Override
	public void start(Stage stage) throws Exception {
		
		// create a TableView
		TableView tableView = new TableView();
		tableView.setEditable(true);
		
		// layout
		BorderPane borderPane = new BorderPane();
		VBox vBox = new VBox(tableView);
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		borderPane.setCenter(vBox);
		borderPane.setTop(gridPane);
		
		
		
		Label guestIdLabel = new Label("Guest ID");
		TextField guestIdTF = new TextField();
		Font customFont = Font.font("Arial", FontWeight.BOLD, 15);
		guestIdLabel.setFont(customFont);
	
		
		Label nameLabel = new Label("Name");
		TextField nameTF = new TextField();
		nameLabel.setFont(customFont);
		
		
		Label phoneLabel = new Label("Phone");
		TextField phoneTF = new TextField();
		phoneLabel.setFont(customFont);
		
		
		Label emailLabel = new Label("Email");
		TextField emailTF = new TextField();
		emailLabel.setFont(customFont);
		
		
		Button addBtn = new Button("Add");
		
		gridPane.add(guestIdLabel, 1, 1);
		gridPane.add(guestIdTF, 1, 2);
		
		gridPane.add(nameLabel, 2, 1);
		gridPane.add(nameTF, 2, 2);
		
		gridPane.add(phoneLabel, 3, 1);
		gridPane.add(phoneTF, 3, 2);
		
		gridPane.add(emailLabel, 4, 1);
		gridPane.add(emailTF, 4, 2);
		
		HBox hBox = new HBox ();
		hBox.getChildren().add(addBtn);	
		
		hBox.setPadding(new Insets(0, 0, 13, 10));
		hBox.setAlignment(Pos.TOP_RIGHT);
		gridPane.add(hBox, 0,3 , 5,1);
		
		
		
		
		// columns guest_id, name, phone, email
		// Add TableColomn to the TableView
		TableColumn <Guest, String> guest_id = new TableColumn <>("Guests ID");
		TableColumn <Guest, String> name = new TableColumn <>("Name");
		TableColumn <Guest, String> phone = new TableColumn <>("Phone");
		TableColumn <Guest, String> email = new TableColumn <>("Email");
				
		guest_id.setMinWidth(160);
		name.setMinWidth(160);
		phone.setMinWidth(160);
		email.setMinWidth(160);
		
		
		
		guest_id.setCellValueFactory(new PropertyValueFactory<>("guest_id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		
		tableView.getColumns().addAll(guest_id, name, phone, email);
		
		tableView.getItems().add(new Guest(13, "Yurii Liakhovych", "0100-100-100-10", "abcd@gmaol.com"));		
		tableView.getItems().add(new Guest(12, "Fgf FDGF", "0100-150-560-10", "855d@gmaol.com"));
		
		
		// add DATA from DataBase
		//loadGuestsFromDatabase(tableView);
		
		
		// DatenBank connection
		String dbUrl = "jdbc:sqlite:src/hotel.db";

		try {
		    conn = DriverManager.getConnection(dbUrl);
		    if (conn != null) {
		        System.out.println("Connected to the database.");	     
		    }

		    String sql = "SELECT guest_id, name, phone, email FROM Guests";

		    Statement stmt = conn.createStatement(); // Can open only one ResultSet at a time.
		    ResultSet rs = stmt.executeQuery(sql);

		    while (rs.next()) {
		        int idDB = rs.getInt("guest_id");
		        String nameDB = rs.getString("name");
		        String phoneDB = rs.getString("phone");
		        String emailDB = rs.getString("email");
		        tableView.getItems().add(new Guest(idDB, nameDB, phoneDB, emailDB));
		    }
		} catch (SQLException e) {
		    System.out.println("Database connection failed.");
		    e.printStackTrace();
		}
		
		
	
		addBtn.setOnAction(event ->{
			String sqlInsertString = "INSERT INTO Guests(guest_id, name, phone, email) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement ptsm = conn.prepareStatement(sqlInsertString);
			ptsm.setInt(1, (Integer.parseInt(guestIdTF.getText())));
			ptsm.setString(2, nameTF.getText());
			ptsm.setString(3, phoneTF.getText());
			ptsm.setString(4, emailTF.getText());
		} catch (SQLException e) {	
			System.out.println("Text Fields should not be empty.");
			e.printStackTrace();
		}
			
			tableView.getItems().add(new Guest((Integer.parseInt(guestIdTF.getText())),
					nameTF.getText(),phoneTF.getText(),emailTF.getText()));
		guestIdTF.setText("");
		nameTF.setText("");
		phoneTF.setText("");
		emailTF.setText("");
		});
		
		Scene scene = new Scene(borderPane, 800, 600);
		
		stage.setScene(scene);
		stage.setTitle("DataBase FX App");
		stage.getIcons().add(new Image(new FileInputStream("src/icon_1.png")));
		stage.show();
	}

	
//	private void loadGuestsFromDatabase(TableView<Guest> tableView) {
//		
//		String dbUrl = "jdbc:sqlite:src/hotel.db";
//		
//
//		try (Connection conn = DriverManager.getConnection(dbUrl)) {
//			if (conn !=null) {
//			System.out.println("Connected to the database.");
//			}
//			
//			String sql = "SELECT guest_id, name, phone, email FROM Guests";
//
//			
//			
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);	
//			
//					
//			while(rs.next()) {
//				int id = rs.getInt("guest_id");
//				String name = rs.getString("name");
//				String phone = rs.getString("phone");
//				String email = rs.getString("email");
//				
//				tableView.getItems().add(new Guest(id, name, phone, email));
//			}
//			
//			
//			
//		} catch (SQLException e) {
//			System.out.println("Database connected failed.");
//			e.printStackTrace();
//		}
//	}
	
	
}
