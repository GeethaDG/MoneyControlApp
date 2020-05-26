package com.example.Bachat;

public class EarningListItem {
    private String Id;
    private String Mode;
    private String Amount;
    private String Date;
    private String Note;

    public EarningListItem( String id,String mode, String amount, String date, String note) {

        Id = id;
        Mode = mode;
        Amount = amount;
        Date = date;
        Note= note;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
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

    public void setNote(String note) { Note = note; }
}
