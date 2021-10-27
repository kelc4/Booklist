package persistence;

import model.Book;
import model.BookList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            BookList bl = new BookList("nameJson");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBookList() {
        try {
            BookList bl = new BookList("nameJson");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookList.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookList.json");
            bl = reader.read();
            assertEquals("nameJson", bl.getNameJson());
            assertEquals(0, bl.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBookList() {
        try {
            BookList bl = new BookList("nameJson");
            bl.addBook(new Book("1", "1", "2021-10-27", false));
            bl.addBook(new Book("2", "2", "2021-10-27", false));
            bl.addBook(new Book("3", "3", "2021-10-27", false));
            bl.addBook(new Book("4", "4", "2021-10-27", false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookList.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBookList.json");
            bl = reader.read();
            assertEquals("nameJson", bl.getNameJson());
            List<Book> books = bl.getBooks();
            assertEquals(4, books.size());
            checkBook("1", "1", "2021-10-27", false, books.get(0));
            checkBook("2", "2", "2021-10-27", false, books.get(1));
            checkBook("3", "3", "2021-10-27", false, books.get(2));
            checkBook("4", "4", "2021-10-27", false, books.get(3));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}