package com.example.films;

public class Film {

    private String titre;

    public Film () {
        titre= "";
    }

    public Film (String t) {
        titre= new String(t);
    }

    public void setTitre (String t) {
        titre= new String(t);
    }

    public String getTitre () {
        return titre;
    }

    public String toString () {
        return titre;
    }
}
