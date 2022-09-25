package me.wgoddard.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CharacterCommand implements Command {

    @Override
    public String run(SlashCommandInteractionEvent command) {

        String path = command.getCommandPath();

        switch (path) {
            case "character/create":
                return create(command);
            case "character/look":
                return look(command);
            case "character/edit":
                return edit(command);
            case "character/delete":
                return delete(command);
            default:
                return "Error: Invalid command path (" + path + ")";
        }

    }

    private String create(SlashCommandInteractionEvent command) {
        return null;
    }

    private String look(SlashCommandInteractionEvent command) {
        return null;
    }

    private String edit(SlashCommandInteractionEvent command) {
        return null;
    }

    private String delete(SlashCommandInteractionEvent command) {
        return null;
    }

}
