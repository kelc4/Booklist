package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookListTest {
    BookList booklist;
    Book b;

    @BeforeEach
    public void setup() {
        booklist = new BookList("nameJson");
        b = new Book("Title", "Author");
    }

    @Test
    public void testBookConstructor() {
        assertEquals("Title", b.getTitle());
        assertEquals("Author", b.getAuthor());
        LocalDate d = LocalDate.now();
        String day = d.toString();
        assertEquals(day, b.getDate());
        assertFalse(b.getStatus());
    }

    @Test
    public void testBookListConstructor() {
        assertEquals(0, booklist.length());
    }

    @Test
    public void testGetBook() {
        assertEquals(null, booklist.getBook(0));
        booklist.addBook(b);
        assertEquals(b, booklist.getBook(0));
    }

    @Test
    public void testAddBook() {
        assertTrue(booklist.addBook(b));
        assertTrue(booklist.addBook(new Book("Title", "A Different Author")));
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
        assertTrue(booklist.checkBox(1));
        assertFalse(booklist.checkBox(3));
    }
    @Test
    public void testDisplayList() {
        String check = String.format("%-3s %-7s %-25s %-15s %-15s\n\n", "#", "Read", "Title", "Author", "Date Added");
        assertEquals(check, booklist.displayList());
        booklist.addBook(b);
        check = String.format("%-3s %-7s %-25s %-15s %-15s\n\n", "#", "Read", "Title", "Author", "Date Added") +
                String.format("%-3s %-7s %-25s %-15s %-15s\n", 1, false, "Title", "Author", LocalDate.now());
        assertEquals(check, booklist.displayList());
    }

    @Test
    public void testBookExists() {
        Book b2 = new Book("a", "a");
        assertFalse(booklist.bookExists(b));
        booklist.addBook(b);
        assertTrue(booklist.bookExists(b));
        booklist.addBook(b2);
        assertTrue(booklist.bookExists(b2));
    }

    @Test
    public void testIsIndexInvalid() {
        assertTrue(booklist.isIndexInvalid(1));
        assertTrue(booklist.isIndexInvalid(0));
        booklist.addBook(b);
        assertFalse(booklist.isIndexInvalid(1));
        assertTrue(booklist.isIndexInvalid(9));
    }

    @Test
    public void testLength() {
        assertEquals(0, booklist.length());
        booklist.addBook(b);
        assertEquals(1, booklist.length());
    }

    @Test
    public void testMerge() {
        booklist.addBook(b);
        assertEquals(1, booklist.length());

        BookList bl = new BookList("nameJson");
        bl.addBook(new Book("1", "1"));
        bl.addBook(new Book("2", "2"));

        booklist.merge(bl);

        assertEquals(3, booklist.length());
    }

    @Test
    public void testRemoveAllBooks() {
        booklist.addBook(b);
        booklist.addBook(new Book("1", "1"));
        booklist.addBook(new Book("2", "2"));
        assertEquals(3, booklist.length());
        booklist.removeAllBooks();
        assertEquals(0, booklist.length());
    }

    @Test
    public void testBookSetterMethods() {
        assertEquals("Title", b.getTitle());
        b.setTitle("1");
        assertEquals("1", b.getTitle());
        assertEquals("Author", b.getAuthor());
        b.setAuthor("1");
        assertEquals("1", b.getAuthor());
        assertEquals(LocalDate.now().toString(), b.getDate());
        b.setDate("2021-11-18");
        assertEquals("2021-11-18", b.getDate());
    }

}