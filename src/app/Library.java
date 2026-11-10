package app;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if (book != null) {
            this.books.add(book);
        }
    }

    public boolean removeBook(Book book) {
        if (book != null) {
            return this.books.remove(book);
        } else {
            return false;
        }
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public int getBookCount() {
        return books.size();
    }
}

