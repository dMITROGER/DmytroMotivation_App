package com.androiddev.dmitr.dmytromotivationapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.androiddev.dmitr.dmytromotivationapp.controller.AppController;
import com.androiddev.dmitr.dmytromotivationapp.model.Quote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class QuoteData {

    ArrayList<Quote> quoteArrayList = new ArrayList<Quote>();

    public void getQuotes(final QuoteListAsyncResponse callBack) {
        String url = "https://raw.githubusercontent.com/dMITROGER/Work-Samples/master/Assets/Quotes.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject quoteObject = response.getJSONObject(i);
                                Quote quote = new Quote();
                                quote.setQuote(quoteObject.getString("quote"));
                                quote.setAuthor(quoteObject.getString("name"));

                                Log.d("STUFFF::", quoteObject.getString("name") + i);

                                quoteArrayList.add(quote);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (callBack != null) callBack.processFinished(quoteArrayList);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });

// Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}