package com.villaleobos.finalproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Books")
public class Books {
  public static final int AVAILABLE = 1;
  public static final int UNAVAILABLE = 0;

  @PrimaryKey(autoGenerate = true)
  private int id;

  @ColumnInfo(name = "title")
  private String title;

  @ColumnInfo(name = "genre")
  private String genre;

  @ColumnInfo(name = "available")
  private int available;

  @ColumnInfo(name = "author")
  private String author;

  public Books(String title, String genre, int available, String author) {
    this.title = title;
    this.genre = genre;
    this.available = available;
    this.author = author;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getAvailable() {
    return available;
  }

  public void setAvailable(int available) {
    this.available = available;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }
}
