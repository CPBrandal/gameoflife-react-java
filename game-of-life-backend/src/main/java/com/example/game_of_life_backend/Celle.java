package com.example.game_of_life_backend;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Celle implements Serializable{
    @JsonIgnore
    Celle[] naboer = new Celle[8];
    boolean levende = false;
    int antNaboer = 0;
    int antLevendeNaboer = 0;

    public Celle(){
    }

    public void settDoed(){
        levende = false;
    }

    public void settLevende(){
        levende = true;
    }

    public boolean getLevende(){
        return levende;
    }
    @JsonIgnore
    public Celle[] getNaboer(){
        return naboer;
    }

    public int getAntNaboer(){
        return antNaboer;
    }

    public int getAntLevendeNaboer(){
        return antLevendeNaboer;
    }
    
    public void leggTilNabo(Celle enCelle){
        naboer[antNaboer] = enCelle;
        antNaboer++;
    }

    public void tellLevendeNaboer(){
        antLevendeNaboer = 0;
        for (int i = 0; i < 8; i++){
            if(naboer[i] != null && naboer[i].getLevende()){
                antLevendeNaboer++;
            }
        }
    }

    public void oppdaterStatus(){
        if(levende){
            if(antLevendeNaboer > 3 || antLevendeNaboer < 2){
                levende = false;
            }
        } else {
            if(antLevendeNaboer == 3){
                levende = true;
            }
        }
    }
}