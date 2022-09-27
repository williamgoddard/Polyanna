package me.wgoddard;

import me.wgoddard.listeners.CommandListener;
import me.wgoddard.listeners.GuildJoinListener;
import me.wgoddard.listeners.MessageListener;
import me.wgoddard.listeners.ReadyListener;
import me.wgoddard.utilities.HTTPThing;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.*;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;
import java.util.List;

public class PolyannaBot {

    // Start the bot
    public static void main(String[] args) throws InterruptedException {

        // Create the builder
        JDABuilder builder = JDABuilder.createDefault(args[0]);

        // Select intents for the builder
        builder.enableIntents(
                GatewayIntent.MESSAGE_CONTENT);

        // Add event listeners to the builder
        builder.addEventListeners(
                new ReadyListener(),
                new MessageListener(),
                new CommandListener(),
                new GuildJoinListener());

        // Set the builder's activity
        builder.setActivity(Activity.watching("you"));

        // Create and start the bot
        JDA bot = builder.build();
        bot.awaitReady();

        // Set up the commands
        List<CommandData> commands = new ArrayList<>();

        commands.add(Commands.slash("drop", "Drop an item from your inventory into the room").addOptions(
                new OptionData(OptionType.STRING, "item", "The name of the item", true)
                        .setRequiredLength(1, 30),
                new OptionData(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false)
                        .setRequiredRange(1, Integer.MAX_VALUE)));

        commands.add(Commands.slash("take", "Take an item from the room into your inventory").addOptions(
                new OptionData(OptionType.STRING, "item", "The name of the item", true)
                        .setRequiredLength(1, 30),
                new OptionData(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false)
                        .setRequiredRange(1, Integer.MAX_VALUE)));

        commands.add(Commands.slash("undress", "Undress an item of clothing and hold it in your inventory").addOptions(
                new OptionData(OptionType.STRING, "item", "The name of the item", true)
                        .setRequiredLength(1, 30),
                new OptionData(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false)
                        .setRequiredRange(1, Integer.MAX_VALUE)));

        commands.add(Commands.slash("wear", "Wear an item of clothing from your inventory").addOptions(
                new OptionData(OptionType.STRING, "item", "The name of the item", true)
                        .setRequiredLength(1, 30),
                new OptionData(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false)
                        .setRequiredRange(1, Integer.MAX_VALUE)));

        commands.add(Commands.slash("undressdrop", "Undress an item of clothing and drop it into the room").addOptions(
                new OptionData(OptionType.STRING, "item", "The name of the item", true)
                        .setRequiredLength(1, 30),
                new OptionData(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false)
                        .setRequiredRange(1, Integer.MAX_VALUE)));

        commands.add(Commands.slash("takewear", "Take an item from the room and wear it").addOptions(
                new OptionData(OptionType.STRING, "item", "The name of the item", true)
                        .setRequiredLength(1, 30),
                new OptionData(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false)
                        .setRequiredRange(1, Integer.MAX_VALUE)));

        commands.add(Commands.slash("room", "Command to perform admin actions on rooms").addSubcommands(
                new SubcommandData("create", "Create a room").addOptions(
                        new OptionData(OptionType.STRING, "room", "The name of the room", true)
                                .setRequiredLength(1, 30)
                ),
                new SubcommandData("list", "List all of the rooms"),
                new SubcommandData("look", "Look at a room").addOptions(
                        new OptionData(OptionType.STRING, "room", "The name of the room", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.INTEGER, "num", "The number of the specific room, if there are multiple rooms with the same name", false)
                                .setRequiredRange(1, Integer.MAX_VALUE)
                ),
                new SubcommandData("edit", "Edit a room").addOptions(
                        new OptionData(OptionType.STRING, "room", "The name of the room", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.INTEGER, "num", "The number of the specific room, if there are multiple rooms with the same name", false)
                                .setRequiredRange(1, Integer.MAX_VALUE),
                        new OptionData(OptionType.STRING, "name", "The new name of the room", false)
                                .setRequiredLength(1, 30)
                ),
                new SubcommandData("delete", "Delete a room").addOptions(
                        new OptionData(OptionType.STRING, "room", "The name of the room", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.INTEGER, "num", "The number of the specific room, if there are multiple rooms with the same name", false)
                                .setRequiredRange(1, Integer.MAX_VALUE)
                )
        ));

        commands.add(Commands.slash("character", "Command to perform admin actions on characters").addSubcommands(
                new SubcommandData("create", "Create a character").addOptions(
                        new OptionData(OptionType.STRING, "name", "The name of the character", true)
                                .setRequiredLength(1, 30)),
                new SubcommandData("look", "Look at a character").addOptions(
                        new OptionData(OptionType.STRING, "name", "The name of the character", true)
                                .setRequiredLength(1, 30)),
                new SubcommandData("edit", "Edit a character").addOptions(
                        new OptionData(OptionType.STRING, "name", "The name of the character", true)
                                .setRequiredLength(1, 30)),
                new SubcommandData("delete", "Delete a character").addOptions(
                        new OptionData(OptionType.STRING, "name", "The name of the character", true)
                                .setRequiredLength(1, 30))));

        commands.add(Commands.slash("item", "Command to perform admin actions on items").addSubcommands(
                new SubcommandData("create", "Create an item").addOptions(
                        new OptionData(OptionType.STRING, "location-type", "The type of location the item is in", true)
                                .addChoice("room", "room")
                                .addChoice("character", "character")
                                .addChoice("clothes", "clothes"),
                        new OptionData(OptionType.STRING, "location", "The name of the location of the item", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.STRING, "name", "The name of the item", true)
                                .setRequiredLength(1, 30)),
                new SubcommandData("look", "Look at an item").addOptions(
                        new OptionData(OptionType.STRING, "location-type", "The type of location the item is in", true)
                                .addChoice("room", "room")
                                .addChoice("character", "character")
                                .addChoice("clothes", "clothes"),
                        new OptionData(OptionType.STRING, "location", "The name of the location of the item", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.STRING, "name", "The name of the item", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false)
                                .setRequiredRange(1, Integer.MAX_VALUE)),
                new SubcommandData("edit", "Edit an item").addOptions(
                        new OptionData(OptionType.STRING, "location-type", "The type of location the item is in", true)
                                .addChoice("room", "room")
                                .addChoice("character", "character")
                                .addChoice("clothes", "clothes"),
                        new OptionData(OptionType.STRING, "location", "The name of the location of the item", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.STRING, "name", "The name of the item", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false)
                                .setRequiredRange(1, Integer.MAX_VALUE)),
                new SubcommandData("delete", "Delete an item").addOptions(
                        new OptionData(OptionType.STRING, "location-type", "The type of location the item is in", true)
                                .addChoice("room", "room")
                                .addChoice("character", "character")
                                .addChoice("clothes", "clothes"),
                        new OptionData(OptionType.STRING, "location", "The name of the location of the item", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.STRING, "name", "The name of the item", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false)
                                .setRequiredRange(1, Integer.MAX_VALUE)),
                new SubcommandData("copy", "Copy an item to the clipboard (Clipboard is oer-server)").addOptions(
                        new OptionData(OptionType.STRING, "location-type", "The type of location the item is in", true)
                                .addChoice("room", "room")
                                .addChoice("character", "character")
                                .addChoice("clothes", "clothes"),
                        new OptionData(OptionType.STRING, "location", "The name of the location of the item", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.STRING, "name", "The name of the item", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false)
                                .setRequiredRange(1, Integer.MAX_VALUE)),
                new SubcommandData("paste", "Paste an item from the clipboard (Clipboard is oer-server)").addOptions(
                        new OptionData(OptionType.STRING, "location-type", "The type of location the item is in", true)
                                .addChoice("room", "room")
                                .addChoice("character", "character")
                                .addChoice("clothes", "clothes"),
                        new OptionData(OptionType.STRING, "location", "The name of the location of the item", true)
                                .setRequiredLength(1, 30),
                        new OptionData(OptionType.STRING, "name", "The name of the item", true)
                                .setRequiredLength(1, 30))));

        bot.getGuildById("933432644857909339").updateCommands().addCommands(commands).queue();

    }

}
