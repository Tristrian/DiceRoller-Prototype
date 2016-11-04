/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tristrian.diceroller;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import static org.spongepowered.api.command.args.GenericArguments.plugin;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

/**
 *
 * @author AlmeidaCorreiaT
 */
@Plugin(id = "diceroller", name = "Dice Roller", version = "1.0", description = "Roll dices with modifiers")
public class DiceRoller {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartingServerEvent event) {
        logger.info("Démarrage de DiceRoller");
        CommandSpec rollDice = CommandSpec.builder()
                .description(Text.of("Roll a dice"))
                .permission("diceroller.command.roll")
                .arguments(
                        GenericArguments.string(Text.of("dice"))
                )
                .executor(new CommandRoll())
                .build();

        Sponge.getCommandManager().register(this, rollDice, "roll");
    }

    public void onServerClose(GameStoppingServerEvent event) {
        logger.info("Arrêt de DiceRoller");
    }
}
