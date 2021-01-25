package burnedpuppies.servercore.other;

import org.bukkit.entity.Player;

public class teleport {
    private static teleport instance = null;

    protected teleport() {
    }

    // Lazy Initialization (If required then only)
    public static teleport getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (teleport.class) {
                if (instance == null) {
                    instance = new teleport();
                }
            }
        }
        return instance;
    }

    public void teleportToPlayer(Player player, Player target){
        player.teleport(target.getLocation());
        Msg.getInstance().sendPlayerMSG(player,"&7You have been &cteleported to &a "+ target.getName(),true);
        return;
    }


}
