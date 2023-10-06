package com.example.films;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
                                                        AdapterView.OnItemClickListener{

    /*
    Le bouton permettant d'ajouter un film à la liste
     */
    private Button bPlus;
    /*
    Le champ texte contenant le titre du film à ajouter
     */
    private EditText titreFilm;
    /*
    La structure de données utilisée pour stocker les films
     */
    private ArrayList<Film> lf;
    /*
    La vue qui permet de visualiser les films stockés
     */
    private ListView vl;

    /*
    L'adapteur qui connectera vue et structure de données
     */
    private ArrayAdapter<Film> aaf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialisation : ne peut être faite lors de la déclaration car
        // l'interface n'existe qu'après l'appel aux deux instructions ci-dessus

        // On récupère le champ texte
        titreFilm= findViewById(R.id.inputFilm);
        // On récupère le bouton
        bPlus= findViewById(R.id.butFilm);
        // A qui on ajoute son action
        bPlus.setOnClickListener(this);

        // On crée un modèle pour la liste de film
        lf= new ArrayList<>();
        // On récupère la vue de l'interface graphique
        vl= findViewById(R.id.listeFilms);
        // On crée l'adapteur qui connectera la vue à la structure de données
        aaf= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lf);
        // On associe cet adapteur à la vue
        // l'adapteur est donc connecté et à la vue et à la structure de données
        // il peut faire passer les informations de l'une à l'autre
        vl.setAdapter(aaf);
        vl.setOnItemClickListener(this);

        // On associe un menu contextuel à la liste
        registerForContextMenu(vl);
    }

    @Override
    /*
        Méthode appelée lorsque l'utilisateur clique sur le bouton "+"
        Elle était abstraite dans l'interface OnClickListener.
     */
    public void onClick(View view) {
        addFilm();
    }

    @Override
        /*
        Méthode appelée lorsque l'utilisateur clique sur un élément de liste
        Elle était abstraite dans l'interface OnItemClickListener.
     */
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent action;
        String v;
        // On affiche un bref message
        // i représente la position de l'objet cliqué dans la liste
        // Cette position est fournie automatiquement par le système

        //*

        Toast.makeText(view.getContext(), "Item à la position " +
                Integer.toString(i), Toast.LENGTH_SHORT);
        // Ce que l'on ajoute à la liste sont des objets de type Film
        // On récupère donc un objet de type Film dans la liste
        // Et on prend son titre, en appelant la méthode "to_string"
        v= ((Film)adapterView.getItemAtPosition(i)).toString();
        action= new Intent(this, InfoActivity.class);
        // Pour donner le titre du film comme information à la nouvelle activité
        action.putExtra("titre",v);
        this.startActivity(action);



    }

    /*
    Méthode addFilm du controleur : elle sait ou prendre le nouveau titre
    et ou l'ajouter.
    Affiche un message bref en bas de l'écran si le champ titre est vide
     */
    public void addFilm () {
        String s;

        s = titreFilm.getText().toString();
        if (!s.isEmpty()) {
            lf.add(new Film(s));
            aaf.notifyDataSetChanged();
            titreFilm.setText("");
        } else {

            // Pour faire en sorte que si titre trop court alors affiche " Titre vide, pas de film ajouté"

            Toast.makeText(getApplicationContext(),
                    "Titre vide, pas de film ajouté", Toast.LENGTH_SHORT).show();
        }
    }

    //*
    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Actions");
        contextMenu.add(Menu.NONE, view.getId(), 1, "Supprimer");
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        lf.remove(info.position);
        aaf.notifyDataSetChanged();
        return true;
    }
}
