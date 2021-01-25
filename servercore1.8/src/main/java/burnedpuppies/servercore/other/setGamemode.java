package burnedpuppies.servercore.other;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setGamemode {
    private static setGamemode instance = null;

    protected setGamemode() {
    }

    // Lazy Initialization (If required then only)
    public static setGamemode getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (setGamemode.class) {
                if (instance == null) {
                    instance = new setGamemode();
                }
            }
        }
        return instance;
    }

    public static String cmdPerm = ConfigManager.getInstance().getString("commands.gamemode.permission");


    public void setPGamemode(CommandSender sender, Player target, GameMode gm){
        boolean targetIsSame = true;
        if (sender instanceof Player){
            Player p = Bukkit.getServer().getPlayer(sender.getName());
            if (!sender.hasPermission(cmdPerm + "." + gm.name().toLowerCase())){
                Msg.getInstance().nopermission(p);
                return;
            }
            if (p != target){
                if (!sender.hasPermission(cmdPerm + "." + gm.name().toLowerCase() + ".other")){
                    Msg.getInstance().nopermission(p);
                    return;
                }
                targetIsSame = false;
            }
            if (target.getGameMode() == gm){
                if (targetIsSame){
                    Msg.getInstance().sendSenderMSG(sender,"&7You are already in &c" + gm.name().toLowerCase() +  "&7.",true);
                    return;
                }
                Msg.getInstance().sendSenderMSG(sender,"&a" + target.getName() + " &7is already in &c" + gm.name().toLowerCase() + "&7.",true);
                return;
            }
            target.setGameMode(gm);
            Msg.getInstance().sendPlayerMSG(target,"&7Your gamemode has been set to &c" + gm.name().toLowerCase() +"&7." , true);
            if (!targetIsSame){
                Msg.getInstance().sendSenderMSG(sender, "&7You have set &a" + target.getName() + "&7 gamemode to &c" + gm.name().toLowerCase()  +"&7.",true);
            }
        }
    }
}
