package com.villaleobos.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.villaleobos.finalproject.databinding.ActivityViewTransactionsBinding;

import java.util.List;

public class ViewTransactions extends AppCompatActivity {
  public static final String TAG = "ViewTransactions";

  private List<Transactions> transactionsList;
  private ListView transactionsListView;
  private ArrayAdapter<Transactions> transactionsArrayAdapter;
  private Database db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityViewTransactionsBinding binding = ActivityViewTransactionsBinding
            .inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    db = Database.getInstance(ViewTransactions.this);
    // Initialize lists
    transactionsList = db.transaction().getAll();
    transactionsListView = binding.viewTransactionsList;
    updateList();

    // Return home
    binding.viewTransactionsHomeBtn.setOnClickListener(view ->
            startActivity(new Intent(this, MainActivity.class))
    );
    // Add a new book
    binding.viewTransactionsYesBtn.setOnClickListener(view ->
            startActivity(new Intent(this, AddBook.class))
    );
  }

  private void updateList() {
    transactionsList = db.transaction().getAll();
    if (transactionsArrayAdapter == null) {
      transactionsArrayAdapter = new ArrayAdapter<>(
              this,
              R.layout.transaction_item,
              R.id.transaction_item_entry,
              transactionsList
      );
      transactionsListView.setAdapter(transactionsArrayAdapter);
    } else {
      transactionsArrayAdapter.clear();
      transactionsArrayAdapter.addAll(transactionsList);
      transactionsArrayAdapter.notifyDataSetChanged();
    }
  }
}
