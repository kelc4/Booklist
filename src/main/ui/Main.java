package ui;

import java.io.FileNotFoundException;

//Runs the app
public class Main {
    public static void main(String[] args) {
        try {
            new BookListApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
