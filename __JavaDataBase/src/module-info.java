module __JavaDB {
	requires java.sql;

	opens app.db to java.sql;
}
