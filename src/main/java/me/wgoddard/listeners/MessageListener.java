package me.wgoddard.listeners;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class MessageListener implements EventListener{

    @Override
    public void onEvent(@NotNull GenericEvent event) {

        if (event instanceof MessageReceivedEvent) {

            MessageReceivedEvent messageEvent = (MessageReceivedEvent) event;
            String message = messageEvent.getMessage().getContentDisplay();
            System.out.println(message);

            if (message.equals("ping")) {
                System.out.println("hi");
                messageEvent.getChannel().sendMessage("Pong!").queue();
            }

        }

    }

}
