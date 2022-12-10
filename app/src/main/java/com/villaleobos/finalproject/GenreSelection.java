package com.villaleobos.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.villaleobos.finalproject.databinding.ActivityGenreSelectionBinding;

import java.util.List;

public class GenreSelection extends AppCompatActivity {
  public static final String TAG = "GenreSelection";

  private List<String> genresList;
  private ListView genresListView;
  private ArrayAdapter<String> genresArrayAdapter;
  private Database db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityGenreSelectionBinding binding =
            ActivityGenreSelectionBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    db = Database.getInstance(GenreSelection.this);
    // Initialize lists
    genresList = db.book().getAllGenres();
    genresListView = binding.genreSelectionGenres;
    updateList();

    // Choose the genre
    binding.genreSelectionGenres.setOnItemClickListener((adapterView, view, i, l) -> {
      String itemSelected = (String) adapterView.getItemAtPosition(i);
      Intent bookSelectionRedirect = new Intent(this, BookSelection.class);

      // No books available
      if (db.book().getAvailableTitlesByGenre(itemSelected).isEmpty()) {
        Toast.makeText(
                GenreSelection.this,
                "There are no books available for this genre.",
                Toast.LENGTH_LONG
        ).show();
        startActivity(new Intent(this, MainActivity.class));
        return;
      }

      Bundle args = new Bundle();
      args.putString("genre", itemSelected);
      bookSelectionRedirect.putExtras(args);
      startActivity(bookSelectionRedirect);
    });
  }

  private void updateList() {
    genresList = db.book().getAllGenres();
    if (genresArrayAdapter == null) {
      genresArrayAdapter = new ArrayAdapter<>(
              this,
              R.layout.genre_item,
              R.id.genre_item_entry,
              genresList
      );
      genresListView.setAdapter(genresArrayAdapter);
    } else {
      genresArrayAdapter.clear();
      genresArrayAdapter.addAll(genresList);
      genresArrayAdapter.notifyDataSetChanged();
    }
  }
}
