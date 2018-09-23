package com.example.raghiii.booklist;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BooksData>> {

    private static final int booksloaderid = 1;
    private TextView emptyTextView;
    private String query = "";
    private String bookurl = "https://www.googleapis.com/books/v1/volumes?q=";
    private String booksurl = "";
    private BooksDataAdapter booksDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView booksListView = findViewById(R.id.list);
        emptyTextView = findViewById(R.id.empty_text_view);
        booksListView.setEmptyView(emptyTextView);
        booksDataAdapter = new BooksDataAdapter(this,
                new ArrayList<BooksData>());
        booksListView.setAdapter(booksDataAdapter);

        final Button searchButton = findViewById(R.id.search_button);
        final EditText searchEditText = findViewById(R.id.search_bar);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchEditText.getText().toString();
                search(query);
            }
        });
    }

    private void search(String query) {
        if (query != null && !query.equals("")) {
            booksurl = bookurl + query + "&maxResults=40";

            ConnectivityManager cm =
                    (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = null;
            if (cm != null) {
                activeNetwork = cm.getActiveNetworkInfo();
            }
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if (isConnected) {

                LoaderManager loaderManager = getLoaderManager();
                loaderManager.restartLoader(booksloaderid, null, this);


            } else {
                Log.e("sdlfkfj", "No internet connection");
                emptyTextView.setText("No Internet Connection");
            }

        }
    }

    @Override
    public Loader<List<BooksData>> onCreateLoader(int id, Bundle args) {

        return new BooksLoader(this, booksurl);
    }

    @Override
    public void onLoadFinished(Loader<List<BooksData>> loader, List<BooksData> data) {
        booksDataAdapter.clear();
        booksDataAdapter.addAll(data);
        emptyTextView.setText("Book not found");
    }

    @Override
    public void onLoaderReset(Loader<List<BooksData>> loader) {
        booksDataAdapter.clear();
    }
}
