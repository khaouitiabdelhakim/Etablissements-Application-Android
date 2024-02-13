package com.abdelhakim.etablissements.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "etablissements")
public class Etablissement {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "label")
    private String label;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "imag")
    private int imag;

    @ColumnInfo(name = "isPublic")
    private boolean isPublic;

    public Etablissement(String label, String name, int imag, boolean isPublic) {
        this.label = label;
        this.name = name;
        this.imag = imag;
        this.isPublic = isPublic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImag() {
        return imag;
    }

    public void setImag(int imag) {
        this.imag = imag;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
