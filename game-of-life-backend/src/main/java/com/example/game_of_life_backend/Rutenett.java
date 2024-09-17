package com.example.game_of_life_backend;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Rutenett implements Serializable{
    int antRader;
    int antKolonner;
    Celle[][] rutene;

    public Rutenett(int r, int k){
        antRader = r;
        antKolonner = k;
        rutene = new Celle[r][k];
    }

    public int getAntRader(){
        return antRader;
    }

    public int getAntKolonner(){
        return antKolonner;
    }

    public Celle[][] getRutene(){
        return rutene;
    }
    public void lagCelle(int rad, int kolonne){
        Celle encelle = new Celle();
        if(Math.random()<=0.3333){
            encelle.settLevende();
        } 
        rutene[rad][kolonne] = encelle;
    }

    public void fyllMedTilfeldigeCeller(){
        for(int i = 0; i < antRader; i++){
            for(int x = 0; x < antKolonner; x++){
                lagCelle(i, x);
            }
        }
    }

    @JsonIgnore
    public Celle hentCelle(int rad, int kolonne){
       if (rad < antRader && kolonne < antKolonner){
            if (rad >= 0 && kolonne >= 0){
                return rutene[rad][kolonne];
            }
        } return null;
    }

    public void settNaboer(int rad, int kolonne){
        Celle enCelle = hentCelle(rad, kolonne);
        for(int r = -1; r < 2; r++){
            for(int k = -1; k < 2; k++){
                if(hentCelle(rad+r, kolonne+k) != null && hentCelle(rad+r, kolonne+k) != enCelle){
                    enCelle.leggTilNabo(hentCelle(rad+r, kolonne+k));
                }
            }
        }
    }

    public void kobleAlleCeller(){
        for(int i = 0; i < antRader; i++){
            for(int x = 0; x < antKolonner; x++){
                settNaboer(i, x);
                rutene[i][x].tellLevendeNaboer();
            }
        }
    }
    @JsonIgnore
    public int antallLevende(){
        int teller = 0;
        for(int i = 0; i < antRader; i++){
            for(int x = 0; x < antKolonner; x++){
                if(rutene[i][x].getLevende()){
                    teller++;
                }
            }
        } return teller;
    }
}
