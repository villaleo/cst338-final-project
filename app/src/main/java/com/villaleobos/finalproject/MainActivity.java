package com.villaleobos.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.villaleobos.finalproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
  public static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
    super.onCreate(savedInstanceState);
    setContentView(mainBinding.getRoot());

    Database db = Database.getInstance(MainActivity.this);
    db.seedCustomers();
    db.seedBooks();

    // Create an account
    mainBinding.createAccountBtn.setOnClickListener(view ->
            startActivity(new Intent(this, Register.class))
    );
    // Place a hold
    mainBinding.placeHoldBtn.setOnClickListener(view ->
            startActivity(new Intent(this, GenreSelection.class))
    );
    // Manage the system
    mainBinding.manageSystemBtn.setOnClickListener(view ->
            startActivity(new Intent(this, ManageSystem.class))
    );
  }
}