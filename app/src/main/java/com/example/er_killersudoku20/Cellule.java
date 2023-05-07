package com.example.er_killersudoku20;


import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class Cellule {

    public int vraiValeur;
    public int valeurTest;
    public Boolean valeurtestistrue = null;
    private int valeur;
    public boolean isInitial = false;
    public boolean[] note = {false, false, false, false, false, false, false, false, false};


    public Cellule() {

    }

    public void setValeur(int nombre) {
        valeur = nombre;
    }

    public int getValeur() {
        return valeur;
    }
}
