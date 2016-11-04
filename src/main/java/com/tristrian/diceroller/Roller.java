package com.tristrian.diceroller;

import java.util.Random;

public class Roller {

    Random random;

    public Roller() {
    }

    public int rollDice(int number, int face) {
        int resultat = 0;
        for (int i = 0; i < number; i++) {
            random = new Random();
            resultat += random.nextInt(face) + 1;
        }
        return resultat;
    }
}
