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
            case "room/list":
                return list(command);
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
        String room = command.getOption("room").getAsString();
        return HTTPThing.postRequest("room/" + guildId, "{\"name\": \"" + room + "\"}");
    }

    private String list(SlashCommandInteractionEvent command) {
        String guildId = command.getGuild().getId();
        String response = HTTPThing.getRequest("room/" + guildId);
        return (!response.equals("")) ? response : "No rooms could be found";
    }

    private String look(SlashCommandInteractionEvent command) {
        String guildId = command.getGuild().getId();
        String room = command.getOption("room").getAsString();
        int num = (command.getOption("num") != null) ? command.getOption("num").getAsInt() : 1;
        String response = HTTPThing.getRequest("room/" + guildId + "/" + room + "/" + num);
        return (!response.equals("")) ? response : "The room could not be found";
    }

    private String edit(SlashCommandInteractionEvent command) {
        String guildId = command.getGuild().getId();
        String room = command.getOption("room").getAsString();
        int num = (command.getOption("num") != null) ? command.getOption("num").getAsInt() : 1;
        String requestBody = "{";
        requestBody += (command.getOption("name") != null) ? "\"name\":\"" + command.getOption("name").getAsString() + "\"," : "";
        requestBody = ((requestBody.charAt(requestBody.length()-1) == ',') ? requestBody.substring(0, requestBody.length()-1) : requestBody) + "}";
        return HTTPThing.putRequest("room/" + guildId + "/" + room + "/" + num, requestBody);
    }

    private String delete(SlashCommandInteractionEvent command) {
        String guildId = command.getGuild().getId();
        String room = command.getOption("room").getAsString();
        int num = (command.getOption("num") != null) ? command.getOption("num").getAsInt() : 1;
        return HTTPThing.deleteRequest("room/" + guildId + "/" + room + "/" + num);
    }

}
