package me.wgoddard;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class PolyannaBot {

    public static void main(String[] args) {

        JDABuilder builder = JDABuilder.createDefault(args[0]);

        JDA bot = builder.build();

    }

}
