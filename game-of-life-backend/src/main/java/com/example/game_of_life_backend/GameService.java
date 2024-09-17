package com.example.game_of_life_backend;

import org.springframework.stereotype.Service;

@Service
public class GameService {
    public Verden verden;

    public void initializeVerden(int rad, int kolonne) {
        verden = new Verden(rad, kolonne);
    }

/*     public Celle[][] getRutenettState() {
        if (verden == null) {
            return new Celle[0][0]; // Return an empty or default state
        }
        return verden.rutenett.rutene;
    } */

    public void updateVerden(){
        verden.oppdatering();
    }

    public Verden getVerden() {
        return verden;
    }
}
