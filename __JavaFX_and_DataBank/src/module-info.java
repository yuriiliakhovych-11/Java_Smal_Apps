module __JavaFX_und_DB {
	
	requires java.sql;
	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	
	opens app.fx to java.sql, javafx.base, javafx.controls, javafx.graphics;
}