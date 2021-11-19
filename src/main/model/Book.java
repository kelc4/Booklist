package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;

//Creates a Book and initializes it with a title, author, date added and read/not read
public class Book implements Writable {

    //Field
    private String title;       //Title of book
    private String author;      //Author of book
    private String date;        //Date book was added
    private boolean status;     //Read/Not read

    //Constructor for when adding books to the list
    public Book(String title, String author) {
        this(title, author, LocalDate.now().toString(), false);
    }

    //Constructor for when reading from JSON
    public Book(String title, String author, String date, boolean status) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.status = status;
    }

    //EFFECTS: returns title
    public String getTitle() {
        return title;
    }

    //EFFECTS: returns author
    public String getAuthor() {
        return author;
    }

    //EFFECTS: returns date
    public String getDate() {
        return date;
    }

    //EFFECTS: returns status
    public boolean getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //EFFECTS: Saves elements of book to json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("date", date);
        json.put("status", status);
        return json;
    }

}
