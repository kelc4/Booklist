package ui;

import model.Book;
import model.BookList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//This class puts everything together and decides how the app will take in information and how it will react
public class BookListApp {
    private static final String JSON_STORE = "./data/booklist.json";
    private String name;
    private int action;
    private BookList userList;

    private final Scanner in;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //Creates new scanner, assigns action to -1
    //EFFECTS: runs the booklist app
    public BookListApp() throws FileNotFoundException {
        action = -1;
        in = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setup();
        while (action != 0) {
            run();
        }
    }

    //Sets up taking input from user for what they want to do
    //MODIFIES: this
    public void setup() {
        System.out.println("Welcome! What shall we name your list of books? (Name not be saved to file)");
        name = in.nextLine();
        System.out.println("Great! " + name + " is ready to be used. To get started, please choose one of "
                + "the following options:");
        userList = new BookList("nameJson");
    }

    //Receives action user wants to take and runs based on their choice
    //MODIFIES: this
    //EFFECTS: prints user's choice, reacts based on it
    public void run() {
        action = requestAction();
        switch (action) {
            case 0:
                terminate();
            case 1:
                addABook();
                break;
            case 2:
                removeABook();
                break;
            case 3:
                checkABookOff();
                break;
//            case 4:
//                addARating();
//                break;
//            case 5:
//                addANote();
//                break;
            case 4:
                viewList();
                break;
            case 5:
                saveBookList();
                break;
            case 6:
                loadBookList();

        }
    }

    //REQUIRES: choice to be a valid int, (not a string, boolean, etc.)
    //EFFECTS: returns an integer indicating which action user wants to take
    // ** If I decide to add addrating and addnote --> change view list to #6, change choice range to choice > 6 **
    //              "  + "[4] Add a rating \n [5] Add a note \n
    public int requestAction() {
        System.out.println("\n [0] Quit \n [1] Add a book \n [2] Remove a book \n [3] Check a book off as read \n "
                + "[4] View list \n [5] Save list \n [6] Load list");

        int choice  = in.nextInt();
        while (choice < 0 || choice > 6) {
            System.out.println("Invalid choice, please choose again");
            choice = in.nextInt();
        }
        in.nextLine();
        return choice;
    }

    //Ends program, stops program from running
    //EFFECTS: stops the program
    public void terminate() {
        System.out.println("Thank you for using " + name + "!");
        System.exit(0);
    }

    //Adds a book to the list unless a book with the same title is already there
    //MODIFIES: this
    //EFFECTS: adds book to userList
    public void addABook() {
        System.out.print("You have chosen to add a book! What is its title? ");
        String title = in.nextLine();
        System.out.println("Great! Who is " + title + "'s author? ");
        String author = in.nextLine();
        if (userList.addBook(new Book(title, author))) {
            System.out.println("Book added!");
        } else {
            System.out.println("Book is already in " + name);
        }
    }

    //REQUIRES: index to be a valid int, (not a string, symbol, etc.)
    //MODIFIES: this
    //EFFECTS: removes a book from userList
    public void removeABook() {
        System.out.println("You have chosen to remove a book! Which number is it? (If you don't know what number "
                + "it is, enter 0 now and choose the view list option) ");
        int index = in.nextInt();
        if (userList.deleteBook(index)) {
            System.out.println("Book removed!");
        } else {
            System.out.println("Book number is invalid.");
        }
    }

    //REQUIRES: index to be a valid int, (not a string, symbol, etc.)
    //MODIFIES: this
    //EFFECTS: marks a book off as read/unread
    public void checkABookOff() {
        System.out.println("You have chosen to check a book off as read! Which book number would you like to check? "
                + "(If you don't know what number it is,enter 0 now and choose the view list option)");
        int index = in.nextInt();
        if (userList.checkBox(index)) {
            System.out.println("Book has been checked");
        } else {
            System.out.println("Book number is invalid");
        }

    }

//    public void addARating() {
//
//    }
//
//    public void addANote() {
//
//    }

    //MODIFIES: this
    // EFFECTS: saves userList to file
    private void saveBookList() {
        try {
            jsonWriter.open();
            jsonWriter.write(userList);
            jsonWriter.close();
            System.out.println("Saved " + userList.getNameJson() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads booklist from file
    private void loadBookList() {
        try {
            userList = jsonReader.read();
            System.out.println("Loaded " + userList.getNameJson() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //Prints out the list
    public void viewList() {
        System.out.println("-----------------------------------------------------------------");
        System.out.print(name + "\n" + userList.displayList());
        System.out.print("-----------------------------------------------------------------\nClick enter to continue");
        in.nextLine();

    }

}
