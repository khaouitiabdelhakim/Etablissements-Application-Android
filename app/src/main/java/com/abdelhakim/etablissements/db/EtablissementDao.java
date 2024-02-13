package com.abdelhakim.etablissements.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface EtablissementDao {
    @Query("SELECT * FROM etablissements")
    List<Etablissement> getAllEtablissements();

    @Query("SELECT * FROM etablissements WHERE isPublic = 1")
    List<Etablissement> getPublicEtablissements();

    @Query("SELECT * FROM etablissements WHERE isPublic = 0")
    List<Etablissement> getPrivateEtablissements();

    @Insert
    void ajouterEtablissement(Etablissement etablissement);

    @Delete
    void supprimerEtablissement(Etablissement etablissement);
}

