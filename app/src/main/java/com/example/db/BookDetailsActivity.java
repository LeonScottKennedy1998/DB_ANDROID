package com.example.db;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookDetailsActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        bookId = intent.getIntExtra("BOOK_ID", -1);

        if (bookId == -1) {
            Toast.makeText(this, "Ошибка: ID книги не передан", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        TextView bookName = findViewById(R.id.book_name);
        TextView bookAuthor = findViewById(R.id.book_author);
        Button deleteButton = findViewById(R.id.btn_delete);

        Book book = dbHelper.getBookById(bookId);
        if (book != null) {
            bookName.setText(book.getBook_Name());
            bookAuthor.setText(book.getAuthor());
        } else {
            Toast.makeText(this, "Ошибка: книга не найдена", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        deleteButton.setOnClickListener(v -> {
            dbHelper.deleteBookById(bookId);
            Toast.makeText(this, "Книга удалена", Toast.LENGTH_SHORT).show();
            finish();
        });

        Button btnEdit = findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(BookDetailsActivity.this, EditBookActivity.class);
            editIntent.putExtra("BOOK_ID", bookId);
            startActivity(editIntent);
        });

    }
    
}
