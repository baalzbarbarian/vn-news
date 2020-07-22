package com.asm_haind_pd03241_newsapp.Model;

public class News {
    private String _id;
    private String NewsCat;
    private String Matter;
    private String Content;
    private String Author;
    private String Date;
    private String Image;

    public News() {
    }

    public News(String _id, String newsCat, String matter, String content, String author, String date, String image) {
        this._id = _id;
        NewsCat = newsCat;
        Matter = matter;
        Content = content;
        Author = author;
        Date = date;
        Image = image;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setNewsCat(String newsCat) {
        NewsCat = newsCat;
    }

    public void setMatter(String matter) {
        Matter = matter;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String get_id() {
        return _id;
    }

    public String getNewsCat() {
        return NewsCat;
    }

    public String getMatter() {
        return Matter;
    }

    public String getContent() {
        return Content;
    }

    public String getAuthor() {
        return Author;
    }

    public String getDate() {
        return Date;
    }

    public String getImage() {
        return Image;
    }
}
