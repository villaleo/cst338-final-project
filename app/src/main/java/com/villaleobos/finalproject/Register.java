package com.villaleobos.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.villaleobos.finalproject.databinding.ActivityRegisterBinding;

import java.util.List;

public class Register extends AppCompatActivity {
  Database db = Database.getInstance(Register.this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ActivityRegisterBinding binding = ActivityRegisterBinding.inflate(getLayoutInflater());
    super.onCreate(savedInstanceState);
    setContentView(binding.getRoot());

    EditText username = binding.registerUserName;
    EditText password = binding.registerPassword;

    binding.registerBtn.setOnClickListener(view -> {
      Intent redirect = new Intent(this, MainActivity.class);

      // Extract the text from the EditText object
      String parsedUsername = username.getText().toString();
      String parsedPassword = password.getText().toString();

      List<Customers> hits = db.customer().getCustomerFromUsername(parsedUsername);
      if (!hits.isEmpty()) {
        // User already exists
        Toast.makeText(
                Register.this,
                String.format("Username \"%s\" already exists!", parsedUsername),
                Toast.LENGTH_LONG
        ).show();

        startActivity(redirect);
        return;
      }

      // Add the new customer
      db.customer().addCustomer(new Customers(parsedUsername, parsedPassword));
      // Create a transaction
      db.transaction().addTransaction(
              new Transactions(Transactions.NEW_ACCOUNT, parsedUsername, null, Transactions.ID)
      );

      Toast.makeText(Register.this, "Account created successfully!", Toast.LENGTH_LONG).show();
      startActivity(redirect);
    });
  }
}
