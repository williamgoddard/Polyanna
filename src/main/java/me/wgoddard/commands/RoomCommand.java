package me.wgoddard.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class RoomCommand implements Command {

    public RoomCommand() {

    }

    @Override
    public String run(SlashCommandInteractionEvent command) {

        String path = command.getCommandPath();

        switch (path) {
            case "room/create":
                return create(command);
            case "room/look":
                return look(command);
            case "room/edit":
                return edit(command);
            case "room/delete":
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
