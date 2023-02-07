package chapter3.task2;

import java.util.Date;

public class BookParser {
    public static void main(String[] args) {
        Book book = new Book("John", "Doe", 123456789, "My Book", new Date(), 10);
        BookParser parser = new BookParser(book);
        parser.printAll();
    }

    public Book book;

    public BookParser(Book book) {
        this.book = book;
        if (this.book.pages() < 10) {
            throw new IllegalArgumentException("Book must have at least 10 pages to be valid.");
        }
    }

    public void printTitle() {
        System.out.println(this.book.title());
    }

    public void printAuthor() {
        System.out.println(this.book.authorfirstname() + " " + this.book.authorlastname());
    }

    public void printISBN() {
        System.out.println(this.book.isbn());
    }

    public void printDate() {
        System.out.println(this.book.date());
    }

    public void printPages() {
        System.out.println(this.book.pages());
    }

    public void printAll() {
        printTitle();
        printAuthor();
        printISBN();
        printDate();
        printPages();
    }
}
