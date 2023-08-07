package com.example.mylibrary;

public class MyBook {
    private String imgURL;
    private String bookName;
    private int bookPages;
    private String bookAuthor;
    private String shortDes;
    private Boolean isExpanded = false;

    private int bookID;
    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public MyBook(int bookID, String bookName,String bookAuthor,int bookPages,String imgURL, String shortDes) {
        this.imgURL = imgURL;
        this.bookName = bookName;
        this.bookPages = bookPages;
        this.bookAuthor = bookAuthor;
        this.shortDes = shortDes;
        this.bookID = bookID;
    }

    public Boolean getExpanded() {
        return isExpanded;
    }

    public void setExpanded(Boolean expanded) {
        isExpanded = expanded;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(Integer bookPages) {
        this.bookPages = bookPages;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }
}
