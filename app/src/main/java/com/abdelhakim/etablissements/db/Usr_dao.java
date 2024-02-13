package com.abdelhakim.etablissements.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Usr_dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addUser(User user);

    @Query("SELECT * FROM users WHERE login = :login")
    List<User> getUser(String login);

    @Query("SELECT * FROM users")
    List<User> getUsers();

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}

