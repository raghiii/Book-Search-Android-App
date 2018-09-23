package com.example.raghiii.booklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BooksDataAdapter extends ArrayAdapter<BooksData> {

    BooksDataAdapter(@NonNull Context context, ArrayList<BooksData> E) {
        super(context, 0, E);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        BooksData booksData = getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.book_title);
        assert booksData != null;
        titleTextView.setText(booksData.getBookTitle());

        TextView authorTextView = convertView.findViewById(R.id.book_author);
        authorTextView.setText(booksData.getAuthor());

        return convertView;
    }
}
