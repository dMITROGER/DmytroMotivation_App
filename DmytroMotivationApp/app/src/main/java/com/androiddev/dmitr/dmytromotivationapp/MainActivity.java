package com.androiddev.dmitr.dmytromotivationapp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.androiddev.dmitr.dmytromotivationapp.data.QuoteData;
import com.androiddev.dmitr.dmytromotivationapp.data.QuoteListAsyncResponse;
import com.androiddev.dmitr.dmytromotivationapp.data.QuoteViewPagerAdapter;
import com.androiddev.dmitr.dmytromotivationapp.model.Quote;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    private QuoteViewPagerAdapter quoteViewPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        quoteViewPagerAdapter = new QuoteViewPagerAdapter( getSupportFragmentManager() , getFragments());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(quoteViewPagerAdapter);
    }

    private List<Fragment> getFragments (){
        final List<Fragment> fragmentList = new ArrayList<>();
        new QuoteData().getQuotes(new QuoteListAsyncResponse() {
            @Override
            public void processFinished(List<Quote> quotes) {
                int i;
                int size = quotes.size();
                for (i = 0; i < size; i++) {
                    QuoteFragment quoteFragment = QuoteFragment.newInstance(
                            quotes.get(i).getQuote(),
                            quotes.get(i).getAuthor()
                    );
                    fragmentList.add(quoteFragment);

                }
                quoteViewPagerAdapter.notifyDataSetChanged();
            }
        });

        return fragmentList;
    }
}
