package com.example.films;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Hashtable;

public class InfoActivity extends AppCompatActivity {

    private TextView titreFilm;
    private EditText resumeFilm;
    private ImageView img;
    private Intent film;
    private String titre;
    private Hashtable<String, String> resumes;
    private Hashtable<String, Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Récupération des composants graphiques de l'activité
        titreFilm= findViewById(R.id.titreFilm);
        resumeFilm= findViewById(R.id.resumeFilm);
        img = findViewById(R.id.imgFilm);

        // Un exemple d'initialisation des hashtable
        resumes= new Hashtable<>();
        resumes.put("Ocean 11", "blabla 11");
        images= new Hashtable<>();
        images.put("Ocean 11", R.drawable.aff11);

        // Récupération des informations venant de l'autre activité
        film= getIntent();
        titre= film.getStringExtra("titre");

        if (titreFilm != null) {
            titreFilm.setText(titre);
        }

        // recherche dans les hashtables
        if (resumes.containsKey(titre)) {
            resumeFilm.setText(resumes.get(titre));
        }
        if (images.containsKey(titre)) {
            img.setImageResource(images.get(titre));
        }
    }
}
