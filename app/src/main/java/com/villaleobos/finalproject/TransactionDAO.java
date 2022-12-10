package com.villaleobos.finalproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDAO {
  @Insert
  void addTransaction(Transactions transaction);

  @Query("SELECT COUNT(*) FROM Transactions")
  int count();

  @Query("SELECT * FROM Transactions")
  List<Transactions> getAll();

  @Query("SELECT * FROM Transactions WHERE customerName = :username")
  List<Customers> getTransactionsFromUsername(String username);

  @Delete
  void delete(Transactions transaction);
}
