package persistence;

import model.Book;
import model.BookList;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookList bl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookList.json");
        try {
            BookList bl = reader.read();
            assertEquals("nameJson", bl.getNameJson());
            assertEquals(0, bl.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBookList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBookList.json");
        try {
            BookList bl = reader.read();
            assertEquals("nameJson", bl.getNameJson());
            List<Book> books = bl.getBooks();
            assertEquals(3, books.size());
            checkBook("1", "1", "2021-10-27", false, books.get(0));
            checkBook("2", "2", "2021-10-27", false, books.get(1));
            checkBook("3", "3", "2021-10-27", false, books.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}