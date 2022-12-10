package com.villaleobos.finalproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CustomerDAO {
  @Insert
  void addCustomer(Customers customer);

  @Query("SELECT COUNT(*) FROM Customers")
  int count();

  @Query("SELECT * FROM Customers")
  List<Customers> getAll();

  @Query("SELECT * FROM Customers WHERE username = :username")
  List<Customers> getCustomerFromUsername(String username);

  @Query("SELECT password FROM Customers WHERE username = :username")
  String getPasswordFromUsername(String username);

  @Delete
  void delete(Customers customer);
}
