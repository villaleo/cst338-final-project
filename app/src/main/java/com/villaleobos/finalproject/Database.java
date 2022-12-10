package com.villaleobos.finalproject;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

// Table with Customers, Books, and Transactions
@androidx.room.Database(
        entities = {Customers.class, Books.class, Transactions.class},
        version = 14,
        exportSchema = false
)
public abstract class Database extends RoomDatabase {
  private static Database instance;

  public abstract CustomerDAO customer();

  public abstract BookDAO book();

  public abstract TransactionDAO transaction();

  public static synchronized Database getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "customers.db")
              .fallbackToDestructiveMigration()
              .allowMainThreadQueries()
              .build();
    }
    return instance;
  }

  public void seedCustomers() {
    if (customer().count() != 0) {
      return;
    }
    runInTransaction(() -> {
      // Initial 3 customer accounts
      customer().addCustomer(new Customers("anton", "t3nn1sch@mp22"));
      customer().addCustomer(new Customers("bernie", "k3ralaf@n"));
      customer().addCustomer(new Customers("shirleybee", "carmel2chicago"));
    });
  }

  public void seedBooks() {
    if (book().count() != 0) {
      return;
    }
    runInTransaction(() -> {
      book().addBook(new Books("Angela's Ashes", "Memoir", Books.AVAILABLE, "Frank McCourt"));
      book().addBook(new Books("Approximation Algorithms", "Computer Science", Books.AVAILABLE, "Vijay Vazirani"));
      book().addBook(new Books("Frankenstein", "Fiction", Books.AVAILABLE, "Mary Shelley"));
    });
  }
}
