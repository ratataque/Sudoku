package com.example.er_killersudoku20;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Grille {


    public GameLevel level;
    public boolean noteBrouillons = false;

    public int currentCellX = -1;
    public int currentCellY = -1;

    private int casesAfficher;


    private Cellule[][] grilleSolution;
    int NBR = 0;


    public Grille() {
        initialiserLaGrille();
        start();
        //afficherLaGrille();
    }


    private void initialiserLaGrille() {
        grilleSolution = new Cellule[9][9];
        for (int i = 0; i < 9;i++) {
            for (int j = 0; j < 9; j++) {
                grilleSolution[i][j] = new Cellule();
                grilleSolution[i][j].setValeur(0);
            }
        }
    }

    private boolean start() {

        Random rand = new Random();

        if (grilleSolution[8][8].getValeur() == 0) {

            for (int nbrligne = 0; nbrligne < grilleSolution.length; nbrligne++) {
                for ( int nbrcolonne = 0; nbrcolonne < grilleSolution.length; nbrcolonne++) {

                    if (grilleSolution[nbrligne][nbrcolonne].getValeur() == 0) {

                        List<Integer> ls = new ArrayList<>();

                        while (ls.size() < 9) {
                            NBR = rand.nextInt(9) + 1;

                            if (!ls.contains(NBR)) {
                                ls.add(NBR);

                                if (!(estDansLaLigne(NBR, nbrligne) || estDansLaColonne(NBR, nbrcolonne) || estDansLaCase(NBR, nbrligne/3, nbrcolonne/3))) {

                                    placeDansLaGrille(NBR, nbrligne, nbrcolonne);

                                    if (start()) {

                                        return true;

                                    } else {

                                        enleveDeLaGrille(nbrligne, nbrcolonne);
                                    }

                                }
                            }
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private void placeDansLaGrille(int nbr,int nbrligne, int nbrcolonne) {

        grilleSolution[nbrligne][nbrcolonne].setValeur(nbr);
        //afficherLaGrille();

    }


    private void enleveDeLaGrille(int nbrligne, int nbrcolonne) {

        grilleSolution[nbrligne][nbrcolonne].setValeur(0);
        //afficherLaGrille();
    }


    private boolean estDansLaLigne(int nbr, int nbrligne) {
        for (int i = 0; i < grilleSolution.length; i++) {
            if (grilleSolution[nbrligne][i].getValeur() == nbr) return true;
        }
        return false;
    }


    private boolean estDansLaColonne(int nbr, int nbrcolonne) {
        for (int i = 0; i < grilleSolution.length; i++) {
            if (grilleSolution[i][nbrcolonne].getValeur() == nbr) return true;
        }
        return false;
    }


    private boolean estDansLaCase(int nbr, int nbrcasey, int nbrcasex) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (grilleSolution[nbrcasey * 3 + i][nbrcasex * 3 + j].getValeur() == nbr) return true;
            }
        }
        return false;
    }


    private void afficherLaGrille() {

        for (int i = 0; i < grilleSolution.length; i++) {
            if ((i % 3) == 0) {
                System.out.println("+-------+-------+-------+");
            }
            for (int j = 0; j < grilleSolution[i].length; j++) {

                if ((j % 3) == 0) {
                    System.out.print("| ");
                }
                System.out.print(grilleSolution[i][j].getValeur() + " ");
            }
            System.out.print("| ");
            System.out.println("");
        }
        System.out.println("+-------+-------+-------+\n");
    }

    public String getGrilleentier() {
        grilleSolution = getGrille(GameLevel.MEDIUM);
        String resultat = "";
        for (int i = 0; i < grilleSolution.length; i++) {
            if ((i % 3) == 0) {
                resultat += ("+--------+---------+---------+\n");
            }
            for (int j = 0; j < grilleSolution[i].length; j++) {

                if ((j % 3) == 0) {
                    resultat += ("| ");
                }
                resultat += (grilleSolution[i][j].isInitial + " ");
            }
            resultat += ("| \n");
            resultat += ("");
        }
        resultat += ("+--------+--------+---------+\n");
        return resultat;
    }


    public Cellule[][] getGrille(GameLevel level ) {

        //if ( level != GameLevel.MEDIUM ) throw new RuntimeException( "Pas encore la" );

        switch (level){
            case EASY:
                casesAfficher = 30;
                break;
            case MEDIUM:
                casesAfficher = 20;
                break;
            case HARD:
                casesAfficher = 10;
                break;
        }

        List<int[]> lst = new ArrayList<>();

        Random rand = new Random();
        int x, y;

        while (lst.size() < casesAfficher) {
            x = rand.nextInt(9);
            y = rand.nextInt(9);

            int[] a = {x, y};
            boolean add = true;
            for (int i = 0; i < lst.size(); i++) {
                if (lst.get(i)[0] == a[0] && lst.get(i)[1] == a[1]) {
                    add = false;
                }
            }

            if (add) {lst.add(a);}

         }

        //Log.d("tata", "getGrille: " + lst);
        for (int i = 0; i < lst.size(); i++) {
            grilleSolution[lst.get(i)[0]][lst.get(i)[1]].isInitial = true;
        }

        return grilleSolution;
    }




}