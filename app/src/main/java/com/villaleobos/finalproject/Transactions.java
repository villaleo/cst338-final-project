package com.villaleobos.finalproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Transactions")
public class Transactions {
  public static final String NEW_ACCOUNT = "New Account";
  public static final String PLACE_HOLD = "Hold placed";
  public static int ID = 0;

  @PrimaryKey(autoGenerate = true)
  private int id;

  @ColumnInfo(name = "type")
  private String type;

  @ColumnInfo(name = "customerName")
  private String customerName;

  @ColumnInfo(name = "bookTitle")
  private String bookTitle;

  @ColumnInfo(name = "reservationNumber")
  private int reservationNumber;

  public Transactions(String type, String customerName, String bookTitle, int reservationNumber) {
    this.type = type;
    this.customerName = customerName;
    this.bookTitle = bookTitle;
    this.reservationNumber = reservationNumber;
    Transactions.ID++;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public int getReservationNumber() {
    return reservationNumber;
  }

  public void setReservationNumber(int reservationNumber) {
    this.reservationNumber = reservationNumber;
  }

  @NonNull
  @Override
  public String toString() {
    switch (type) {
      case NEW_ACCOUNT:
        return String.format("%s: %s", NEW_ACCOUNT, customerName);
      case PLACE_HOLD:
        return String.format("%s: %s -> %s", PLACE_HOLD, customerName, bookTitle);
      default:
        return "";
    }
  }
}
