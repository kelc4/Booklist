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
        b = new Book("Title", "Author");
    }

    @Test
    public void testAddBook() {
        assertTrue(booklist.addBook(b));
        assertFalse(booklist.addBook(b));
    }

    @Test
    public void testDeleteBook() {
        assertFalse(booklist.deleteBook(1));
        booklist.addBook(b);
        assertTrue(booklist.deleteBook(1));
    }

    @Test
    public void testCheckBox() {
        booklist.addBook(new Book("Title", "Author"));
        assertTrue(booklist.checkBox(1));
    }

//    @Test
//    public void testAddRating() {
//        assertEquals(5, booklist.addRating(b, 5));
//    }
//
//    @Test
//    public void testAddNote() {
//        assertTrue(booklist.addNote(b, "Good", 1));
//    }

    @Test
    public void testBookExists() {
        Book b2 = new Book("a", "a");
        assertFalse(booklist.bookExists(b));
        booklist.addBook(b);
        assertTrue(booklist.bookExists(b));
        booklist.addBook(b2);
        assertTrue(booklist.bookExists(b2));
    }

}