package com.tristrian.diceroller;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Parser {

    private final Roller roller;
    private ArrayList<Integer> result;
    private ArrayList<Integer> diceResult;
    private ArrayList<String> dicesArg;
    ScriptEngineManager mgr;
    ScriptEngine engine;

    public Parser() {
        roller = new Roller();
        mgr = new ScriptEngineManager(null);
        engine = mgr.getEngineByName("JavaScript");
    }

    public ArrayList<Integer> parseCommand(String string) {
        result = new ArrayList<>();
        String[] argument = string.split(" ");
        for (String argument1 : argument) {
            diceResult = new ArrayList<>();
            dicesArg = new ArrayList<>();
            //Pour un lancer de dés, on sépare tous les opérateurs en premier
            String[] dices = argument1.split("[-+*/]");
            //On parcourt les dés individuellement.
            rollDices(dices);
            //Effectue les opérateurs
            String valeurFinale = replaceArgs(argument1);
            result.add(calculateString(valeurFinale));
        }
        return result;
    }

    public void rollDices(String[] dices) {
        for (String dice : dices) {
            //On vérifie s'il s'agit d'un dé et on le rajoute à la liste de résultat des dés
            if (dice.indexOf('d') > 0) {
                dicesArg.add(dice);
                String[] number = dice.split("d");
                diceResult.add(roller.rollDice(Integer.parseInt(number[0]), Integer.parseInt(number[1])));
            }
        }
    }

    public String replaceArgs(String arg) {
        String valeurFinale = arg;
        for (int j = 0; j < dicesArg.size(); j++) {
            valeurFinale = valeurFinale.replaceFirst(dicesArg.get(j), "" + diceResult.get(j));
        }
        return valeurFinale;
    }

    public int calculateString(String calculationString) {
        int resultDice = 0;
        System.out.println(calculationString);
        System.out.println(engine);
        try {
            resultDice = (int) engine.eval(calculationString);
        } catch (ScriptException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultDice;
    }
}
