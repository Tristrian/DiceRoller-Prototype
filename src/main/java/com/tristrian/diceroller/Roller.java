/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tristrian.diceroller;

import java.util.Random;

/**
 *
 * @author AlmeidaCorreiaT
 */
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
