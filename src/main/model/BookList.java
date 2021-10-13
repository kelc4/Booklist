package model;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    //List of books
    private List<Book> booklist;

    //Creates booklist
    //EFFECTS: sets up list where books will be stored
    public BookList() {
        booklist = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Adds book to booklist if title and author are not the same
    public boolean addBook(Book book) {
        //stub
        return false;
    }

    //MODIFIES: this
    //EFFECTS: Removes book from booklist
    public boolean deleteBook(Book book) {
        //stub
        return false;
    }

    //Checks if unchecked, unchecks if checked
    //MODIFIES: book
    public boolean checkBox(Book book) {
        //stub
        return false;
    }

    //Adds personal rating to the book
    //MODIFIES: book
    public int addRating(Book book, int rating) {
        //stub
        return -1;
    }

    //Add any comment, notes about the book
    //MODIFIES: book
    public boolean addNote(Book book, String note) {
        //stub
        return false;
    }

    //EFFECTS: Displays whole list
    public List<Book> displayList() {
        //stub
        return booklist;
    }


}
