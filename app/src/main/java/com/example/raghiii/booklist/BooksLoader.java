package com.example.raghiii.booklist;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class BooksLoader extends AsyncTaskLoader<List<BooksData>> {
    private static final String LOG_TAG = BooksLoader.class.getName();
    private String Url;

    BooksLoader(Context context, String Url) {
        super(context);
        this.Url = Url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<BooksData> loadInBackground() {
        if (Url == null) {
            return null;
        }
        Log.i(LOG_TAG, Url);
        List<BooksData> result = QueryUtils.fetchBooksData(Url);
        Log.v(LOG_TAG, "loading in background");
        return result;
    }


}