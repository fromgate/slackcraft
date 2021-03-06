package pw.javipepe.slackcraft.slack;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import lombok.Getter;
import org.bukkit.ChatColor;
import pw.javipepe.slackcraft.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javi
 */
public class Slack {

    @Getter String token;

    public Slack(String token) {
        this.token = token;
    }

    /**
     * Post message to channel and close connection
     *
     * @param channel
     * @param message
     * @throws Exception
     */
    public void sendMessageToChannelAndQuit(String channel, String message) throws Exception {
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession(token);
        session.connect();

        session.sendMessage(session.findChannelByName(channel), message, null);
        session.disconnect();
    }

    /**
     * Checks if the slack you are connected to contains a channel called like the param channel
     *
     * @param channel query to check
     * @return true if exists, false if not
     * @throws Exception
     */
    public boolean channelExists(String channel) throws Exception {
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession(token);
        session.connect();

        boolean exists = false;
        if (session.findChannelByName(channel) != null)
            exists = true;

        session.disconnect();
        return exists;
    }

    /**
     * Checks if the slack you are connected to contains a user called like the param user
     *
     * @param user query to check
     * @return true if exists, false if not
     * @throws Exception
     */
    public boolean userExists(String user) throws Exception {
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession(token);
        session.connect();

        boolean exists = false;
        if (session.findUserByUserName(user) != null)
            exists = true;

        session.disconnect();
        return exists;
    }

    /**
     * Print out a nicely comma formatted list of channels available to the bot (in where it's in)
     *
     * @return comma list of available channels
     * @throws Exception
     */
    public String listOfAccessibleChannels() throws Exception {
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession(token);
        session.connect();

        List<String> a = new ArrayList<>();

        for (SlackChannel c : session.getChannels()) {
            if (c.getName() != null)
                a.add(c.getName());
        }

        System.out.println(StringUtils.listToEnglishCompound(a, "", ""));

        session.disconnect();
        return StringUtils.listToEnglishCompound(a, ChatColor.GOLD + "", ChatColor.RED + "");
    }

    /**
     * Send a private message to target
     *
     * @param target of the pm
     * @param message you want to send
     * @throws Exception
     */
    public void pm(String target, String message) throws Exception {
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession(token);
        session.connect();

        session.sendMessageToUser(session.findUserByUserName(target), message, null);

        session.disconnect();
    }
}
