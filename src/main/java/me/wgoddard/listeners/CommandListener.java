package me.wgoddard.listeners;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class CommandListener implements EventListener{

    @Override
    public void onEvent(@NotNull GenericEvent event) {

        if (event instanceof SlashCommandInteractionEvent) {

            System.out.println("Command received");

            SlashCommandInteractionEvent commandEvent = (SlashCommandInteractionEvent) event;
            if (commandEvent.getName().equals("oof")) {
                commandEvent.reply("OOF!").queue();
            }

        }

    }

}
