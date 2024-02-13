package com.abdelhakim.etablissements;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.abdelhakim.etablissements.db.Etablissement;
import com.abdelhakim.etablissements.db.MyDatabase;

public class AjouterEtablissmentActivity extends AppCompatActivity {

    private EditText label;
    private EditText name;
    private Switch type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_etablissment);

        label = findViewById(R.id.label);
        name = findViewById(R.id.name);
        type = findViewById(R.id.switchType);
    }

    public void ajouterEtablissement(View view) {

        String label_value = label.getText().toString();
        String name_value = name.getText().toString();
        boolean isPublic = type.isChecked();

        if (!label_value.isEmpty() && !name_value.isEmpty()) {
            // Ajouter le nouvel établissement à la base de données
            MyDatabase myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "etablissement_db").allowMainThreadQueries().build();
            Etablissement etablissement = new Etablissement(label_value, name_value,0, isPublic);
            myDatabase.etablissementDao().ajouterEtablissement(etablissement);

            Toast.makeText(this, "Établissement ajouté avec succès", Toast.LENGTH_SHORT).show();
            finish(); // Fermer l'activité après l'ajout
        } else {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        }
    }
}
