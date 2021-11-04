package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String name, String author, String date, boolean status, Book book) {
        assertEquals(name, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(date, book.getDate());
        assertEquals(status, book.getStatus());
    }
}
