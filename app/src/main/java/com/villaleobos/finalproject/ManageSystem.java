package com.villaleobos.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.villaleobos.finalproject.databinding.ActivityManageSystemBinding;

public class ManageSystem extends AppCompatActivity {
  static final String TAG = "ManageSystem@onCreate";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ActivityManageSystemBinding binding =
            ActivityManageSystemBinding.inflate(getLayoutInflater());

    super.onCreate(savedInstanceState);
    setContentView(binding.getRoot());

    binding.manageSystemLogin.setOnClickListener(view -> {
      String username = binding.manageSystemUsername.getText().toString();
      String password = binding.manageSystemUsername.getText().toString();

      if (username.equals("!admin2") && password.equals("!admin2")) {
        // Login the librarian
        Log.d(TAG, "Managing system!");
        startActivity(new Intent(this, ViewTransactions.class));
      } else {
        Toast.makeText(
                ManageSystem.this,
                "Invalid username or password!",
                Toast.LENGTH_LONG
        ).show();
        startActivity(new Intent(this, MainActivity.class));
      }
    });
  }
}