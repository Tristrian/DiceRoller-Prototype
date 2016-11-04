package com.tristrian.diceroller;

import java.util.ArrayList;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;

public class CommandRoll implements CommandExecutor {

    private final Parser parser = new Parser();
    private ArrayList<Integer> result;

    @Override
    public CommandResult execute(CommandSource cs, CommandContext cc) throws CommandException {
        MessageChannel mc = MessageChannel.TO_ALL;
        String args = cc.<String>getOne("dice").get();
        result = new ArrayList<>();
        result = parser.parseCommand(args);
        String string = "";
        if (result.size() == 1) {
            string += result.get(0);
        } else {
            for (Integer inte : result) {
                string += inte + ", ";
            }
        }
        cs.sendMessage(Text.of(string));
        mc.send(Text.of(cs.getName() + " lance " + args + " et obtient : " + string));

        return CommandResult.success();
    }
}
