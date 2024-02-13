package com.abdelhakim.etablissements.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    @NonNull
    public String login;

    @ColumnInfo(name = "user_pass")
    public String pass;

    @NonNull
    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

