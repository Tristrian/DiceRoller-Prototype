/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tristrian.diceroller;

import java.util.ArrayList;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;

/**
 *
 * @author AlmeidaCorreiaT
 */
public class CommandRoll implements CommandExecutor {

    private final Parser parser = new Parser();
    private ArrayList<Integer> result;

    @Override
    public CommandResult execute(CommandSource cs, CommandContext cc) throws CommandException {
//        cs.sendMessage(Text.of("Hello World!"));
        MessageChannel mc = MessageChannel.TO_ALL;
//        mc.send(Text.of("Hello World!"));
        String args = cc.<String>getOne("formula").get();
//        cs.sendMessage(Text.of(args));
//        mc.send(Text.of(cs.getName()+" lance : "+args));
        result = new ArrayList<>();
        result = parser.parseCommand(args);
//        String string = "Vous obtenez : ";
        String string = "";
        if (result.size() == 1) {
            string += result.get(0);
        } else {
            for (Integer inte : result) {
                string += inte + ", ";
            }
        }
//        cs.sendMessage(Text.of(string));
        mc.send(Text.of(cs.getName() + " lance " + args + " et obtient : " + string));

        return CommandResult.success();
    }

}
