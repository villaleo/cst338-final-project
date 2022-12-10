package com.villaleobos.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.villaleobos.finalproject.databinding.ActivityAddBookBinding;

public class AddBook extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityAddBookBinding binding = ActivityAddBookBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    Database db = Database.getInstance(AddBook.this);
    binding.newBookBtn.setOnClickListener(view -> {
      String title = binding.newBookTitle.getText().toString();
      String author = binding.newBookAuthor.getText().toString();
      String genre = binding.newBookGenre.getText().toString();

      if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
        return;
      }
      db.book().addBook(new Books(title, genre, Books.AVAILABLE, author));
      Toast.makeText(AddBook.this, "New book added!", Toast.LENGTH_LONG).show();
      startActivity(new Intent(this, MainActivity.class));
    });
  }
}
