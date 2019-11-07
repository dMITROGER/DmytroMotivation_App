package com.androiddev.dmitr.dmytromotivationapp.model;

public class Quote {
    private String name;
    private String quote;

    public String getAuthor() {
        return name;
    }

    public void setAuthor(String name) {
        this.name = name;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
