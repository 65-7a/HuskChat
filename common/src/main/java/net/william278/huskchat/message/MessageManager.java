package net.william278.huskchat.message;

import net.william278.huskchat.channel.Channel;
import net.william278.huskchat.config.ConfigFile;
import net.william278.huskchat.player.Player;

import java.util.HashMap;
import java.util.Map;

public abstract class MessageManager {

    private static final Map<String, String> messages = new HashMap<>();

    public MessageManager(ConfigFile messagesConfig) {
        load(messagesConfig);
    }

    private void load(ConfigFile messagesConfig) {
        messages.clear();
        for (String messageKey : messagesConfig.getConfigKeys()) {
            messages.put(messageKey, messagesConfig.getString(messageKey, ""));
        }
    }

    public String getRawMessage(String messageID) {
        return messages.get(messageID);
    }

    public String getRawMessage(String messageID, String... placeholderReplacements) {
        String message = messages.get(messageID);
        int replacementIndexer = 1;

        // Replace placeholders
        for (String replacement : placeholderReplacements) {
            String replacementString = "%" + replacementIndexer + "%";
            message = message.replace(replacementString, replacement);
            replacementIndexer = replacementIndexer + 1;
        }
        return message;
    }

    // Send a message to the correct channel
    public abstract void sendMessage(Player player, String messageID, String... placeholderReplacements);

    public abstract void sendMessage(Player player, String messageId);

    public abstract void sendCustomMessage(Player player, String message);

    public abstract void sendFormattedChannelMessage(Player target, Player sender, Channel channel, String message);

    public abstract void sendFormattedOutboundPrivateMessage(Player recipient, Player sender, String message);

    public abstract void sendFormattedInboundPrivateMessage(Player recipient, Player sender, String message);

}