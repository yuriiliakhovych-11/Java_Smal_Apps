module __JavaFX_MemoryGame {
	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens memory.app to javafx.base, javafx.controls, javafx.graphics;
}