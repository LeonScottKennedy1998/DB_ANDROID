package com.example.db;

public class Book {
    private int ID_Book;
    private String Book_Name;
    private String Author;

    public Book(int ID_Book, String Book_Name, String Author)
    {
        this.ID_Book= ID_Book;
        this.Book_Name = Book_Name;
        this.Author = Author;
    }

    public int getID_Book(){
        return ID_Book;
    }

    public void setID_Book(int ID_Book){
        this.ID_Book = ID_Book;
    }

    public String getBook_Name(){
        return  Book_Name;
    }

    public void setBook_Name(String book_Name){
        this.Book_Name = book_Name;
    }

    public String getAuthor(){
        return Author;
    }

    public void setAuthor(String author){
        this.Author = author;
    }
}
