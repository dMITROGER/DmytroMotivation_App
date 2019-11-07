package com.androiddev.dmitr.dmytromotivationapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {
    Button shareBtn;

    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View quoteView = inflater.inflate(R.layout.fragment_quote, container, false);
        TextView quoteText = quoteView.findViewById(R.id.quote_text);
        TextView byAuthor = quoteView.findViewById(R.id.byAuthor);
        CardView cardView = quoteView.findViewById(R.id.cardview);
        final String quote = getArguments().getString("quote");
        String author = getArguments().getString("author");
        int[] colors = new int[] {R.color.blue_500_Opacity50, R.color.pink_900_Opacity50,
                R.color.green_500_Opacity50, R.color.lime_700_Opacity50,
                R.color.orange_700_Opacity50, R.color.amber_800_Opacity50,
                R.color.cyan_900_Opacity50, R.color.dark_purple_500_Opacity50};
        quoteText.setText(quote);
        byAuthor.setText(author);
        cardView.setBackgroundResource(getRandomQuote(colors));

        shareBtn = quoteView.findViewById(R.id.buttonShare);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"MyApp");
                shareIntent.putExtra(Intent.EXTRA_TEXT,quote);
                startActivity(Intent.createChooser(shareIntent,"Share via ..."));
            }
        });
        return quoteView;
    }

    public static final QuoteFragment newInstance (String quote, String author){
        QuoteFragment fragment = new QuoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("author",author);
        fragment.setArguments(bundle);
        return fragment;
    }

    int getRandomQuote(int[] colorArray) {
        int color;
        int quotesArrayLen = colorArray.length;
        int randomNum = ThreadLocalRandom.current().nextInt(quotesArrayLen);
        color = colorArray[randomNum];
        return  color;
    }
}
