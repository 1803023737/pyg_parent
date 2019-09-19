package com.spring4.study_day2;

public class LogBook  extends Book
{

    private Book book;
    public LogBook(Book book) {
        this.book=book;
    }

    @Override
    public void show() {
        System.out.println("log start!");
        book.show();
        System.out.println("log end!");
    }
}
