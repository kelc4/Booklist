package model;

import java.time.LocalDate;

public class Book {

    //Field
    private String title;       //Title of book
    private String author;      //Author of book
    private LocalDate date;     //Date book was added
    private int rating;        //Rating out of 5
    private String image;       //Image of book cover
    private boolean status;     //Read/Not read
    private String note;

    public Book(String title, String author, String image) {
        setTitle(title);
        setAuthor(author);
        date = LocalDate.now();
        setImage(image);
        setStatus(status);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getRating() {
        return rating;
    }

    public String getImage() {
        return image;
    }

    public  boolean getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setRating(int stars) {
        this.rating = stars;
    }

    public void setNote(String notes) {
        this.note = notes;
    }

}
