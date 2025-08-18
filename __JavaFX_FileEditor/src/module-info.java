module __JavaFX_11_08_25 {
	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens app.fx to javafx.base, javafx.controls, javafx.graphics;
}