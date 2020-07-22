package com.asm_haind_pd03241_newsapp.Model;

public class Category {
    private String _id;
    private String catName;
    private String des;

    public Category() {
    }

    public Category(String _id, String catName, String des) {
        this._id = _id;
        this.catName = catName;
        this.des = des;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String get_id() {
        return _id;
    }

    public String getCatName() {
        return catName;
    }

    public String getDes() {
        return des;
    }
}
