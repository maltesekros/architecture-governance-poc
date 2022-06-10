package com.chrismicallef.poc.bookservice.model;

public class Book {

    private final long id;
    private final String bookName;
    private final String author;

    public Book(final long id, final String bookName, final String author) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }
}
