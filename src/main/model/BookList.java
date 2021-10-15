package model;

import java.util.ArrayList;
import java.util.List;

//Creates the booklist out of an arraylist and provides the implementation for everything a list for books should have
public class BookList {
    //List of books
    private final List<Book> booklist;

    //Creates booklist
    //NODIFIES: this
    //EFFECTS: sets up list where books will be stored
    public BookList() {
        booklist = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: returns true if book is added to booklist successfully (book (check from the title) is not already
    // in the list)
    public boolean addBook(Book book) {
        if (bookExists(book)) {
            return false;
        }
        booklist.add(book);
        return true;
    }

    //MODIFIES: this
    //EFFECTS: Returns true if book is removed from booklist successfully
    public boolean deleteBook(int index) {
        if (isIndexInvalid(index)) {
            return false;
        }
        booklist.remove(index - 1);
        return true;
    }

    //Checks if unchecked, unchecks if checked
    //MODIFIES: book, this
    //EFFECTS: returns true if status is successfully updated
    public boolean checkBox(int index) {
        if (isIndexInvalid(index)) {
            return false;
        }
        Book book = booklist.get(index - 1);
        book.setStatus(!book.getStatus());
        return true;

    }

//    //Adds personal rating to the book
//    //MODIFIES: book
//    public int addRating(Book book, int rating) {
//        book.setRating(rating);
//        return rating;
//    }
//
//    //Add any comment, notes about the book
//    //MODIFIES: book
//    public boolean addNote(Book book, String note, int addOrOverride) {
//        if (addOrOverride == 1) {                                         //It adds notes to current note
//            book.setNote(book.getNote() + " " + note);
//        } else if (addOrOverride == 2) {                                  //Wipes out the current note, adds a new one
//            book.setNote(note);
//        } else {
//            return false;
//        }
//        return true;
//    }

    //EFFECTS: Returns string of the whole list formatted nicely
    public String displayList() {
        String display = String.format("%-3s %-7s %-25s %-15s %-15s\n\n", "#", "Read", "Title", "Author", "Date Added");
        for (Book item: booklist) {
            display += String.format("%-3s %-7s %-25s %-15s %-15s\n", booklist.indexOf(item) + 1, item.getStatus(),
                    item.getTitle(), item.getAuthor(), item.getDate());
        }
        return display;
    }

    //Checks if book is in list
    public boolean bookExists(Book book) {
        for (Book item: booklist) {
            if (item.getTitle().equals(book.getTitle())) {
                return true;
            }
        }
        return false;
    }

    //Checks if index is invalid
    public boolean isIndexInvalid(int index) {
        return index < 1 || index > length();
    }

    //Checks list length/size
    public int length() {
        return booklist.size();
    }

}
