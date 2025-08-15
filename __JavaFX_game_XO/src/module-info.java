module __JavaFX_game_XO {
	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens app.fx to javafx.base, javafx.controls, javafx.graphics;
}