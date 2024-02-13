package com.abdelhakim.etablissements;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abdelhakim.etablissements.db.MyDatabase;
import com.abdelhakim.etablissements.db.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button connect;
    private EditText login;
    private EditText pass;
    private TextView text;

    public static User user = new User();
    public static MyDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        connect = findViewById(R.id.connect);
        login = findViewById(R.id.login);
        pass = findViewById(R.id.password);
        text = findViewById(R.id.newAccount);

        mydatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "user_bd")
                .allowMainThreadQueries().build();

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClick(v);
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClick(v);
            }
        });
    }

    private void myClick(View v) {
        if (v.getId() == R.id.newAccount) {
            Intent intent = new Intent(this, CreationCompteActivity.class);
            startActivity(intent);
        }  else if (v.getId() == R.id.connect) {
            user.login = login.getText().toString();
            user.pass = pass.getText().toString();
            if (!(login.getText().toString()).matches("")) {
                List<User> usr = mydatabase.mydao().getUser(login.getText().toString());

                if (usr.size() != 0) {
                    if (usr.get(0).pass.contentEquals(pass.getText().toString())) {
                        Intent intent = new Intent(this, MyListActivity.class);
                        intent.putExtra("username",usr.get(0).getLogin());
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Mot de passe incorrect",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Compte non existant",
                            Toast.LENGTH_SHORT).show();
                }
            }

        }

    }
}
