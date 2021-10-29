package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Creates the booklist out of an arraylist and provides the implementation for everything a list for books should have
public class BookList implements Writable {
    //List of books
    private final List<Book> booklist;
    //private String name;            //The name the user wants (Will maybe retry in another part)
    private String nameJson;        //The key Json recognizes

    //Creates booklist
    //NODIFIES: this
    //EFFECTS: sets up list where books will be stored
    public BookList(String nameJson) {
        booklist = new ArrayList<>();
        this.nameJson = nameJson;
    }

    //EFFECTS: returns name of booklist
    public String getNameJson() {
        return nameJson;
    }

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<Book> getBooks() {
        return Collections.unmodifiableList(booklist);
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

    //EFFECTS: Checks if book is in list, returns a boolean
    public boolean bookExists(Book book) {
        for (Book item: booklist) {
            if (item.getTitle().equals(book.getTitle())) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: Checks if index is invalid, returns a boolean
    public boolean isIndexInvalid(int index) {
        return index < 1 || index > length();
    }

    //EFFECTS: Checks list length/size, returns an int
    public int length() {
        return booklist.size();
    }

    //EFFECTS: returns nameJson and list of books as a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("name", name);
        json.put("nameJson", nameJson);
        json.put("booklist", booklistToJson());
        return json;
    }

    // EFFECTS: returns books in this booklist as a JSON array
    private JSONArray booklistToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : booklist) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }

}
