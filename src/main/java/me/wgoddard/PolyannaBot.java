package me.wgoddard;

import me.wgoddard.listeners.CommandListener;
import me.wgoddard.listeners.MessageListener;
import me.wgoddard.listeners.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
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

        commands.add(Commands.slash("drop", "Drop an item from your inventory into the room")
                .addOption(OptionType.STRING, "item", "The name of the item", true)
                .addOption(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false));

        commands.add(Commands.slash("take", "Take an item from the room into your inventory")
                .addOption(OptionType.STRING, "item", "The name of the item", true)
                .addOption(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false));

        commands.add(Commands.slash("undress", "Undress an item of clothing and hold it in your inventory")
                .addOption(OptionType.STRING, "item", "The name of the item", true)
                .addOption(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false));

        commands.add(Commands.slash("wear", "Wear an item of clothing from your inventory")
                .addOption(OptionType.STRING, "item", "The name of the item", true)
                .addOption(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false));

        commands.add(Commands.slash("undressdrop", "Undress an item of clothing and drop it into the room")
                .addOption(OptionType.STRING, "item", "The name of the item", true)
                .addOption(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false));

        commands.add(Commands.slash("takewear", "Take an item from the room and wear it")
                .addOption(OptionType.STRING, "item", "The name of the item", true)
                .addOption(OptionType.INTEGER, "num", "The number of the specific item, if there are multiple items with the same name", false));

        bot.getGuildById("933432644857909339").updateCommands().addCommands(commands).queue();

    }

}
