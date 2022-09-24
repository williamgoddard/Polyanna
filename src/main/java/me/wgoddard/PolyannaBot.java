package me.wgoddard;

import me.wgoddard.listeners.CommandListener;
import me.wgoddard.listeners.MessageListener;
import me.wgoddard.listeners.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.Command;
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
                new CommandListener());

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
                        .setRequiredRange(1, Integer.MAX_VALUE)
        ));

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

        commands.add(Commands.slash("item", "Command to perform admin actions on items")
                        .addSubcommandGroups(
                                new SubcommandGroupData("create", "Create an item")
                                        .addSubcommands(
                                                new SubcommandData("room","Create an item in a room")
                                                        .addOption(OptionType.STRING, "room", "The name of the room", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true),
                                                new SubcommandData("player","Create an item in a player's inventory")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true),
                                                new SubcommandData("clothes","Create an item in a player's clothes")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                        ),
                                new SubcommandGroupData("look", "Look at an item")
                                        .addSubcommands(
                                                new SubcommandData("room","Look at an item in a room")
                                                        .addOption(OptionType.STRING, "room", "The name of the room", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false),
                                                new SubcommandData("player","Look at an item in a player's inventory")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false),
                                                new SubcommandData("clothes","Look at an item in a player's clothes")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false)

                                        ),
                                new SubcommandGroupData("edit", "Edit an item")
                                        .addSubcommands(
                                                new SubcommandData("room","Edit an item in a room")
                                                        .addOption(OptionType.STRING, "room", "The name of the room", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false),
                                                new SubcommandData("player","Edit an item in a player's inventory")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false),
                                                new SubcommandData("clothes","Edit an item in a player's clothes")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false)

                                        ),
                                new SubcommandGroupData("delete", "Delete an item")
                                        .addSubcommands(
                                                new SubcommandData("room","Delete an item in a room")
                                                        .addOption(OptionType.STRING, "room", "The name of the room", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false),
                                                new SubcommandData("player","Delete an item in a player's inventory")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false),
                                                new SubcommandData("clothes","Delete an item in a player's clothes")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false)

                                        ),
                                new SubcommandGroupData("copy", "Copy an item to the clipboard (Clipboard is per-server)")
                                        .addSubcommands(
                                                new SubcommandData("room","Copy an item from a room to the clipboard (Clipboard is per-server)")
                                                        .addOption(OptionType.STRING, "room", "The name of the room", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false),
                                                new SubcommandData("player","Copy an item from a player's inventory to the clipboard (Clipboard is per-server)")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false),
                                                new SubcommandData("clothes","Copy an item from a player's clothes to the clipboard (Clipboard is per-server)")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)
                                                        .addOption(OptionType.STRING, "num", "The number of the specific item, if there are multiple items with the same name", false)

                                        ),
                                new SubcommandGroupData("paste", "Paste an item from the clipboard (Clipboard is per-server)")
                                        .addSubcommands(
                                                new SubcommandData("room","Paste the item in the clipboard into a room (Clipboard is per-server)")
                                                        .addOption(OptionType.STRING, "room", "The name of the room", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true),
                                                new SubcommandData("player","Paste the item in the clipboard into a player's inventory (Clipboard is per-server)")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true),
                                                new SubcommandData("clothes","Paste the item in the clipboard into a player's clothes (Clipboard is per-server)")
                                                        .addOption(OptionType.STRING, "player", "The name of the player", true)
                                                        .addOption(OptionType.STRING, "name", "The name of the item", true)

                                        )

                        )
                );

        bot.getGuildById("933432644857909339").updateCommands().addCommands(commands).queue();

    }

}
