package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookListTest {
    BookList bookList;
    Book b;

    @BeforeEach
    public void setup() {
        bookList = new BookList();
        b = new Book("Title", "Author", "Image");
    }

    @Test
    public void testAddBook() {
        assertTrue(bookList.addBook(b));
        assertFalse(bookList.addBook(b));
    }

    @Test
    public void testDeleteBook() {
        assertFalse(bookList.deleteBook(b));
        bookList.addBook(b);
        assertTrue(bookList.deleteBook(b));
    }

    @Test
    public void testCheckBox() {
        assertTrue(bookList.checkBox(b));
    }

    @Test
    public void testAddRating() {
        assertEquals(5, bookList.addRating(b, 5));
    }

    @Test
    public void testAddNote() {
        assertTrue(bookList.addNote(b, "Good"));
    }

    @Test
    public void testDisplayList() {
        assertEquals(new ArrayList<>(), bookList.displayList());

    }

}