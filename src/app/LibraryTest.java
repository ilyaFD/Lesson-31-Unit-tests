
package app;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Library tests - Happy Path + Edge Cases")
public class LibraryTest {

    private Library library;

    @BeforeEach
    public void setup() {
        library = new Library();
    }

    // ADD BOOK TESTS

    @Test
    public void testAddOneBook() {
        // given
        Book niceBook = new Book("nice title", "nice author");

        // when
        library.addBook(niceBook);

        // then
        assertEquals(1, library.getBookCount());
        assertTrue(library.getBooks().contains(niceBook));
    }

    @Test
    public void testAddMultipleBooks() {
        // given
        Book niceBook = new Book("nice title", "nice author");
        Book anotherNiceBook = new Book("another nice title", "another nice author");

        // when
        library.addBook(niceBook);
        library.addBook(anotherNiceBook);

        // then
        assertEquals(2, library.getBookCount());
        assertTrue(library.getBooks().contains(niceBook));
        assertTrue(library.getBooks().contains(anotherNiceBook));
    }

    @Test
    public void testAddNullBook() {
        // when
        library.addBook(null);

        // then
        assertEquals(0, library.getBookCount());
    }

    @Test
    public void testAddDuplicateBook() {
        // given
        Book niceBook = new Book("nice title", "nice author");

        // when
        library.addBook(niceBook);
        library.addBook(niceBook);

        // then
        assertEquals(2, library.getBookCount());
    }

    // REMOVE BOOK TESTS

    @Test
    public void testRemoveBook() {
        // given
        Book niceBook = new Book("nice title", "nice author");
        Book anotherNiceBook = new Book("another nice title", "another nice author");

        library.addBook(niceBook);
        library.addBook(anotherNiceBook);

        // when
        boolean result = library.removeBook(anotherNiceBook);

        // then
        assertTrue(result);
        assertEquals(1, library.getBookCount());
        assertTrue(library.getBooks().contains(niceBook));
        assertFalse(library.getBooks().contains(anotherNiceBook));
    }

    @Test
    public void testRemoveExistingBook() {
        // given
        Book existingBook = new Book("nice title", "nice author");
        library.addBook(existingBook);

        // when
        boolean result = library.removeBook(existingBook);

        // then
        assertTrue(result);
        assertEquals(0, library.getBookCount());
    }

    @Test
    public void testRemoveNonExistingBook() {
        // given
        Book existingBook = new Book("nice title", "nice author");
        Book notExistingBook = new Book("different title", "different author");

        library.addBook(existingBook);

        // when
        boolean result = library.removeBook(notExistingBook);

        // then
        assertFalse(result);
        assertEquals(1, library.getBookCount());
    }

    @Test
    public void testRemoveNullBook() {
        // given
        Book existingBook = new Book("nice title", "nice author");
        library.addBook(existingBook);

        // when
        boolean result = library.removeBook(null);

        // then
        assertFalse(result);
        assertEquals(1, library.getBookCount());
    }

    @Test
    public void testRemoveFromEmptyLibrary() {
        // given
        Book niceBook = new Book("nice title", "nice author");

        // when
        boolean result = library.removeBook(niceBook);

        // then
        assertFalse(result);
        assertEquals(0, library.getBookCount());
    }

    // GET BOOKS TESTS

    @Test
    public void testGetBooks() {
        // given
        Book existingBook = new Book("nice title", "nice author");
        library.addBook(existingBook);

        // when
        List<Book> result = library.getBooks();

        // then
        assertEquals(List.of(existingBook), result);
    }

    @Test
    public void testGetBooksEmptyLibrary() {
        // when
        List<Book> result = library.getBooks();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetBooksReturnsCopy() {
        // given
        Book niceBook = new Book("nice title", "nice author");
        library.addBook(niceBook);

        // when
        List<Book> result = library.getBooks();
        result.clear();

        // then
        assertEquals(1, library.getBookCount());
        assertTrue(library.getBooks().contains(niceBook));
    }

    // GET BOOK COUNT TESTS

    @Test
    public void testGetBookCountEmptyLibrary() {
        // when
        int result = library.getBookCount();

        // then
        assertEquals(0, result);
    }

    @Test
    public void testGetBookCountAfterAdding() {
        // given
        Book niceBook = new Book("nice title", "nice author");

        // when
        library.addBook(niceBook);

        // then
        assertEquals(1, library.getBookCount());
    }

    @Test
    public void testGetBookCountAfterRemoving() {
        // given
        Book niceBook = new Book("nice title", "nice author");
        library.addBook(niceBook);

        // when
        library.removeBook(niceBook);

        // then
        assertEquals(0, library.getBookCount());
    }
}
