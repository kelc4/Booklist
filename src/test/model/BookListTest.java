package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookListTest {
    BookList booklist;
    Book b;

    @BeforeEach
    public void setup() {
        booklist = new BookList();
        b = new Book("Title", "Author", "Image");
    }

    @Test
    public void testAddBook() {
        assertTrue(booklist.addBook(b));
        assertFalse(booklist.addBook(b));
    }

    @Test
    public void testDeleteBook() {
        assertFalse(booklist.deleteBook(b));
        booklist.addBook(b);
        assertTrue(booklist.deleteBook(b));
    }

    @Test
    public void testCheckBox() {
        assertTrue(booklist.checkBox(b));
    }

    @Test
    public void testAddRating() {
        assertEquals(5, booklist.addRating(b, 5));
    }

    @Test
    public void testAddNote() {
        assertTrue(booklist.addNote(b, "Good", 1));
    }

    @Test
    public void testBookExists() {
        assertFalse(booklist.bookExists(b));
        booklist.addBook(b);
        assertTrue(booklist.bookExists(b));
    }


}