package me.wgoddard;

import me.wgoddard.listeners.CommandListener;
import me.wgoddard.listeners.MessageListener;
import me.wgoddard.listeners.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class PolyannaBot {

    public static void main(String[] args) throws InterruptedException {

        JDABuilder builder = JDABuilder.createDefault(args[0]);

        builder.enableIntents(
                GatewayIntent.MESSAGE_CONTENT);

        builder.addEventListeners(
                new ReadyListener(),
                new MessageListener(),
                new CommandListener());

        builder.setActivity(Activity.watching("you"));

        JDA bot = builder.build();

        bot.awaitReady();

        bot.getGuildById("933432644857909339").upsertCommand("oof", "Makes a big oof").queue();

    }

}
