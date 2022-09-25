package me.wgoddard.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface Command {

    public String run(SlashCommandInteractionEvent command);

}
