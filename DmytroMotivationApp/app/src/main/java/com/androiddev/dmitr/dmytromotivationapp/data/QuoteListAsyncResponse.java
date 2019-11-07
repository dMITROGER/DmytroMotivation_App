package com.androiddev.dmitr.dmytromotivationapp.data;

import com.androiddev.dmitr.dmytromotivationapp.model.Quote;

import java.util.List;

public interface QuoteListAsyncResponse {
    void processFinished (List<Quote> quotes);
}
