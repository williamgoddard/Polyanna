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
        String guildId = command.getGuild().getId();
        String name = command.getOption("name").getAsString();
        return HTTPThing.postRequest("room/" + guildId, "{\"name\": \"" + name + "\"}");
    }

    private String look(SlashCommandInteractionEvent command) {
        String guildId = command.getGuild().getId();
        String name = command.getOption("name").getAsString();
        int num = (command.getOption("num") != null) ? command.getOption("num").getAsInt() : 1;
        return HTTPThing.getRequest("room/" + guildId + "/" + name + "/" + num);
    }

    private String edit(SlashCommandInteractionEvent command) {
        String guildId = command.getGuild().getId();
        String name = command.getOption("name").getAsString();
        int num = (command.getOption("num") != null) ? command.getOption("num").getAsInt() : 1;
        return HTTPThing.putRequest("room/" + guildId + "/" + name + "/" + num, "{\"name\": \"" + name + "\"}");
    }

    private String delete(SlashCommandInteractionEvent command) {
        String guildId = command.getGuild().getId();
        String name = command.getOption("name").getAsString();
        int num = (command.getOption("num") != null) ? command.getOption("num").getAsInt() : 1;
        return HTTPThing.deleteRequest("room/" + guildId + "/" + name + "/" + num);
    }

}
