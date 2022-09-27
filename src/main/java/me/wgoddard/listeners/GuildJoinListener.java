package me.wgoddard.listeners;

import me.wgoddard.utilities.HTTPThing;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class GuildJoinListener implements EventListener {

    @Override
    public void onEvent(@NotNull GenericEvent event) {

        if (event instanceof GuildJoinEvent) {

            String guildId = ((GuildJoinEvent) event).getGuild().getId();
            HTTPThing.postRequest("server", "{\"discordId\": " + guildId + "}");

        }

    }
}
