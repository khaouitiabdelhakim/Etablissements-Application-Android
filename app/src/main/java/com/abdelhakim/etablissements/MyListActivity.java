package com.abdelhakim.etablissements;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.abdelhakim.etablissements.db.Etablissement;
import com.abdelhakim.etablissements.db.MyDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MyListActivity extends AppCompatActivity {

    private List<Etablissement> allEtablissements;
    private List<Etablissement> filteredEtablissements;

    private TextView text;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text = findViewById(R.id.user);
        text.setText("Bonjour, " + Objects.requireNonNull(getIntent().getStringExtra("username")).toUpperCase());

        // get the reference of RecyclerView
        RecyclerView rv = findViewById(R.id.list);
        // set a LinearLayoutManager with default vertical orientation
        rv.setLayoutManager(new LinearLayoutManager(this));
        // Create adapter instance and set it to RecyclerView
        MyAdapter adapter = new MyAdapter(this,new ArrayList<>());
        rv.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get data from Room database
        MyDatabase myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "etablissement_db").allowMainThreadQueries().build();
        allEtablissements = myDatabase.etablissementDao().getAllEtablissements();


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String establishmentPreference = sharedPreferences.getString("establishment_preference", "public");

        switch (establishmentPreference) {
            case "public":
                // Filter to show only public establishments
                filteredEtablissements = allEtablissements.stream()
                        .filter(Etablissement::isPublic)
                        .collect(Collectors.toList());
                ((MyAdapter) ((RecyclerView) findViewById(R.id.list)).getAdapter()).updateList(filteredEtablissements);

                break;

            case "private":
                // Filter to show only private establishments
                filteredEtablissements = allEtablissements.stream()
                        .filter(etablissement -> !etablissement.isPublic())
                        .collect(Collectors.toList());
                ((MyAdapter) ((RecyclerView) findViewById(R.id.list)).getAdapter()).updateList(filteredEtablissements);

                break;
            default:
                filteredEtablissements = allEtablissements;
                ((MyAdapter) ((RecyclerView) findViewById(R.id.list)).getAdapter()).updateList(filteredEtablissements);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item1) {
            // Handle action for item1
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.item2) {

            Intent intent = new Intent(this, AjouterEtablissmentActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.item3) {
            // Handle action for item3
            Toast.makeText(this, "Supprimer un établissement", Toast.LENGTH_LONG).show();
            return true;
        } else if (itemId == R.id.etablissement_public) {
            // Filter to show only public establishments
            filteredEtablissements = allEtablissements.stream()
                    .filter(Etablissement::isPublic)
                    .collect(Collectors.toList());
            ((MyAdapter) ((RecyclerView) findViewById(R.id.list)).getAdapter()).updateList(filteredEtablissements);
            return true;
        }
        else if (itemId == R.id.etablissement_all) {
            filteredEtablissements = allEtablissements;
        return true;
        } else if (itemId == R.id.etablissement_private) {
            // Filter to show only private establishments
            filteredEtablissements = allEtablissements.stream()
                    .filter(etablissement -> !etablissement.isPublic())
                    .collect(Collectors.toList());
            ((MyAdapter) ((RecyclerView) findViewById(R.id.list)).getAdapter()).updateList(filteredEtablissements);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
