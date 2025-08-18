module __JavaFX_SlotMachine {
	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens slot.app to javafx.base, javafx.controls, javafx.graphics;
}