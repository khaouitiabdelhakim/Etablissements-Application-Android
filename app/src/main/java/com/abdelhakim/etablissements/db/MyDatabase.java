package com.abdelhakim.etablissements.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Etablissement.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract Usr_dao mydao();

    public abstract EtablissementDao etablissementDao();
}
