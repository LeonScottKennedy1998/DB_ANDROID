package com.example.db;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditBookActivity extends AppCompatActivity {

    private EditText editBookName, editBookAuthor;
    private Button btnSaveChanges;
    private DatabaseHelper dbHelper;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        dbHelper = new DatabaseHelper(this);

        editBookName = findViewById(R.id.edit_book_name);
        editBookAuthor = findViewById(R.id.edit_book_author);
        btnSaveChanges = findViewById(R.id.edit_book_button);

        bookId = getIntent().getIntExtra("BOOK_ID", -1);

        loadBookDetails();

        btnSaveChanges.setOnClickListener(v -> updateBook());
    }

    private void loadBookDetails() {
        Book book = dbHelper.getBookById(bookId);
        if (book != null) {
            editBookName.setText(book.getBook_Name());
            editBookAuthor.setText(book.getAuthor());
        } else {
            Toast.makeText(this, "Книга не найдена", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateBook() {
        String newName = editBookName.getText().toString();
        String newAuthor = editBookAuthor.getText().toString();

        if (newName.isEmpty() || newAuthor.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isUpdated = dbHelper.updateBook(bookId, newName, newAuthor);

        if (isUpdated) {
            Toast.makeText(this, "Книга обновлена", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(EditBookActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(this, "Ошибка обновления", Toast.LENGTH_SHORT).show();
        }
    }

}

