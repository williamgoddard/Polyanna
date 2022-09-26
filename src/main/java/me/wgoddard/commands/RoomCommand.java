package me.wgoddard.commands;

import me.wgoddard.utilities.HTTPThing;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class RoomCommand implements Command {

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
        String name = command.getOption("name").getAsString();
        return HTTPThing.postRequest("room/create", "{\"name\": \"" + name + "\"}");
    }

    private String look(SlashCommandInteractionEvent command) {
        String name = command.getOption("name").getAsString();
        return HTTPThing.getRequest("room/read/" + name);
    }

    private String edit(SlashCommandInteractionEvent command) {
        return null;
    }

    private String delete(SlashCommandInteractionEvent command) {
        String name = command.getOption("name").getAsString();
        return HTTPThing.deleteRequest("room/delete/" + name);
    }

}
