package com.example.game_of_life_backend;
import java.io.Serializable;

public class Verden implements Serializable{
    int genNr = 0;
    Rutenett rutenett;
    int rad;
    int kolonne;

    public Verden(int rad, int kolonne){
        this.rad = rad;
        this.kolonne = kolonne;
        rutenett = new Rutenett(rad, kolonne);
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
    }

    public int getGenNr(){
        return genNr;
    }

    public int getRad(){
        return rad;
    }

    public int getKolonne(){
        return kolonne;
    }

    public Rutenett getRutenett(){
        return rutenett;
    }
    
    public void oppdatering(){
        for(int i = 0; i < rad; i++){
            for(int x = 0; x < kolonne; x++){
                if(rutenett.hentCelle(i, x) != null){
                    rutenett.hentCelle(i, x).tellLevendeNaboer();
                }
            }
        }
        for(int i = 0; i < rad; i++){
            for(int x = 0; x < kolonne; x++){
                if(rutenett.hentCelle(i, x) != null){
                    rutenett.hentCelle(i, x).oppdaterStatus();
                }
            }
        }
        genNr++;
    }
}

