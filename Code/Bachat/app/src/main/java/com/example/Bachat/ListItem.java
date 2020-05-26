package com.example.Bachat;

public class ListItem {
    private String Id;
    private String Category;
    private String Subcategory;
    private String Modeofpayment;
    private String Amount;
    private String Date;
    private String Note;

    public ListItem(String id, String category, String subcategory, String modeofpayment, String amount, String date, String note) {
        Id = id;
        Category = category;
        Subcategory = subcategory;
        Modeofpayment = modeofpayment;
        Amount = amount;
        Date = date;
        Note = note;
    }
    public ListItem(String id, String category, String subcategory, String modeofpayment, String amount, String date) {
        Id = id;
        Category = category;
        Subcategory = subcategory;
        Modeofpayment = modeofpayment;
        Amount = amount;
        Date = date;
    }
    public ListItem( String category, String amount) {
        Id = null;
        Category = category;
        Subcategory = null;
        Amount = amount;
        Date = null;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSubcategory() {
        return Subcategory;
    }

    public void setSubcategory(String subcategory) {
        Subcategory = subcategory;
    }

    public String getModeofpayment() {
        return Modeofpayment;
    }

    public void setModeofpayment(String modeofpayment) {
        Modeofpayment = modeofpayment;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Date = note;
    }
}
