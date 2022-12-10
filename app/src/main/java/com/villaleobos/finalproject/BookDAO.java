package com.villaleobos.finalproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDAO {
  @Insert
  void addBook(Books book);

  @Query("SELECT COUNT(*) FROM Books")
  int count();

  @Query("SELECT * FROM Books")
  List<Books> getAll();

  @Query("SELECT genre FROM Books")
  List<String> getAllGenres();

  @Query("SELECT title FROM Books WHERE genre = :genre AND available = 1 ORDER BY title")
  List<String> getAvailableTitlesByGenre(String genre);

  @Query("SELECT * FROM Books WHERE genre = :genre AND title = :title")
  List<Books> getBookFromGenreAndTitle(String genre, String title);

  @Update
  void updateBook(Books book);
}
