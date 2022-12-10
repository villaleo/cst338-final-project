package com.villaleobos.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.villaleobos.finalproject.databinding.ActivitySuccessBinding;

public class Success extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivitySuccessBinding binding = ActivitySuccessBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    Bundle received = getIntent().getExtras();
    if (received == null) {
      return;
    }

    binding.successTitle.setText(received.getString("title"));
    binding.successUsername.setText(received.getString("username"));
    binding.successFinishBtn.setOnClickListener(view ->
            startActivity(new Intent(this, MainActivity.class)));
  }
}
