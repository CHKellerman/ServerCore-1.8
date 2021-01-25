package burnedpuppies.servercore.listeners;

import burnedpuppies.servercore.other.IgnoreCheck;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onEvent(AsyncPlayerChatEvent e) {
        for (Player cur:  Bukkit.getServer().getOnlinePlayers()){
            IgnoreCheck.getInstance().isIgnoring(cur.getName(),e.getPlayer().getName());
            e.getRecipients().remove(cur);
        }
    }
}
