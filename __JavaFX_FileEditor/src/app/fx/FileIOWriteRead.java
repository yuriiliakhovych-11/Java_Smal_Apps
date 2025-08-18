package app.fx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIOWriteRead {
    public static void main(String[] args) {

    	// Створення файлу
        File file = new File("C:\\Users\\YurriLiakhovych\\Desktop\\10 - JavaFX\\nioWriteRead\\U27.txt");
        File file_1 = new File("C:\\Users\\YurriLiakhovych\\Desktop\\10 - JavaFX\\nioWriteRead\\U27_1.txt");
        
        
        
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        // Читаємо вміст файлу
        String fileData = readFileData(file);
        
        // Виводимо вміст у консоль
        System.out.println(fileData);
        
        // Додаємо текст до вмісту
        String data = fileData + "Hello U27!";
        
        // Записуємо оновлений вміст у файл
        writeDataToFile(file, data);
        
       
        
        
    	// Читання файлу
        try {	
        	Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
    } // Main

    
	private static String readFileData(File file) {
	    String data = "";
	    try {
	        Scanner scanner = new Scanner(file);
	        while(scanner.hasNextLine()) {
	            data = data + scanner.nextLine() + "\n";  // Додано перенесення рядка
	        }
	        scanner.close();  // Додано закриття Scanner
	        return data;
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    return data;
	} // readFileData
	
	
	private static void writeDataToFile(File file, String data) {
		try {	
        	FileWriter fileWriter = new FileWriter(file);
        	fileWriter.write(data);
        	fileWriter.close();
        } catch (IOException e) {        
            e.printStackTrace();
        } // writeDataToFile
	}
        
 
} // FileIOWriteRead
        
        
        
        

