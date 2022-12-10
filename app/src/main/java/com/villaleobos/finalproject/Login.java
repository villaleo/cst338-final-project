package com.villaleobos.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.villaleobos.finalproject.databinding.ActivityLoginBinding;

import java.util.List;

// NOTE: Reuse this activity by using bundles and key-value checking
public class Login extends AppCompatActivity {
  public static final String TAG = "Login";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Database db = Database.getInstance(Login.this);
    super.onCreate(savedInstanceState);
    ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    Bundle received = getIntent().getExtras();
    if (received == null) {
      return;
    }

    String genreSelected = received.getString("genre");
    String titleSelected = received.getString("title");
    binding.loginBtn.setOnClickListener(view -> {
      // Verify the user and checkout the book based off of the fields provided
      String username = binding.loginUserName.getText().toString();
      String password = binding.loginPassword.getText().toString();

      List<Customers> hits = db.customer().getCustomerFromUsername(username);
      if (hits.isEmpty()) {
        // Invalid username
        Log.d(TAG, "Invalid username!");
        return;
      }
      if (hits.get(0).getPassword().equals(password)) {
        // Success
        Intent successRedirect = new Intent(this, Success.class);
        Bundle args = new Bundle();
        args.putString("title", titleSelected);
        args.putString("username", username);

        // Add transaction
        db.transaction().addTransaction(new Transactions(
                Transactions.PLACE_HOLD, username, titleSelected, Transactions.ID)
        );

        // Remove the book
        Books book = db.book().getBookFromGenreAndTitle(genreSelected, titleSelected).get(0);
        book.setAvailable(Books.UNAVAILABLE);
        db.book().updateBook(book);

        successRedirect.putExtras(args);
        startActivity(successRedirect);
      } else {
        Log.d(TAG,"Incorrect password!");
      }
    });

    // TODO: Rename each activity to match the correct action
  }
}
