
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class containing Discord bot initialiser and webscraper scheduler
 */
public class DiscordBot extends ListenerAdapter {
    public static void main(String[] args) throws Exception {
        String token = InputReader.returnToken();

        JDA api = new JDABuilder(token)
                .addEventListeners(new DiscordBot())
                .build();

        Runnable helloRunnable = new Runnable() {

            Long channelid = InputReader.returnChannelID();
            String [] URLAddresses = InputReader.returnURLAddresses();
            String [] previousLink = InputReader.returnPreviousURLs(URLAddresses.length);
            boolean isFirst = true;

            /**
             * Scrapes each feed and sends a message in the associated Discord
             * channel if there has been an update
             * Prevents messages from being sent in the first loop,
             * as previous links are unknown
             */
            public void run() {
                TextChannel channel = api.getTextChannelById(channelid);

                for (int i = 0; i < URLAddresses.length; i++) {
                    String latestLink = MessageData.readRSS(URLAddresses[i]);

                    if (!latestLink.equals(previousLink[i])) {
                        previousLink[i] = latestLink;

                        if (isFirst) {
                            continue;

                        } else if (isFirst && i == URLAddresses.length - 1) {
                            isFirst = false;

                        } else {
                            channel.sendMessage("<" + latestLink + ">" + " @everyone").queue();
                        }
                    }
                }
            }
        };

        //Schedules webscraper to run periodically
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Initial delay prevents blocking from bot initialisation
        executor.scheduleAtFixedRate(helloRunnable, 10, 60, TimeUnit.SECONDS);
    }
}