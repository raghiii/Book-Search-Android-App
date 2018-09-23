package com.example.raghiii.booklist;

public class BooksData {
    private String title;
    private String author;


    BooksData(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getBookTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

}
