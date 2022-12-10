package com.villaleobos.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.villaleobos.finalproject.databinding.ActivityBookSelectionBinding;

import java.util.List;

public class BookSelection extends AppCompatActivity {
  public static final String TAG = "BookSelection";

  private String genreSelected;
  private List<String> titlesList;
  private ListView titlesListView;
  private ArrayAdapter<String> titlesArrayAdapter;
  private Database db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityBookSelectionBinding binding =
            ActivityBookSelectionBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    Bundle received = getIntent().getExtras();
    if (received != null) {
      genreSelected = received.getString("genre");
    }

    db = Database.getInstance(BookSelection.this);
    titlesList = db.book().getAvailableTitlesByGenre(genreSelected);
    titlesListView = binding.bookSelectionTitles;
    updateList();

    binding.bookSelectionTitles.setOnItemClickListener((adapterView, view, i, l) -> {
      String itemSelected = (String) adapterView.getItemAtPosition(i);
      Intent loginRedirect = new Intent(this, Login.class);
      Bundle args = new Bundle();
      args.putString("genre", genreSelected);
      args.putString("title", itemSelected);
      loginRedirect.putExtras(args);
      startActivity(loginRedirect);
    });
  }

  private void updateList() {
    titlesList = db.book().getAvailableTitlesByGenre(genreSelected);
    if (titlesArrayAdapter == null) {
      titlesArrayAdapter = new ArrayAdapter<>(
              this,
              R.layout.book_title_item,
              R.id.title_entry,
              titlesList
      );
      titlesListView.setAdapter(titlesArrayAdapter);
    } else {
      titlesArrayAdapter.clear();
      titlesArrayAdapter.addAll(titlesList);
      titlesArrayAdapter.notifyDataSetChanged();
    }
  }
}
