package burnedpuppies.servercore.cmds;

import burnedpuppies.servercore.other.ConfigManager;
import burnedpuppies.servercore.other.Msg;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class Fly  extends BukkitCommand {

    public String cmdPerm = ConfigManager.getInstance().getString("commands.fly.permission");

    public Fly(String name) {super(name);}

    public boolean execute(CommandSender sender, String str, String[] args) {
        if (args.length == 0){
            if (sender instanceof Player){
                Player p = Bukkit.getServer().getPlayer(sender.getName());
                if (p.getAllowFlight()){
                    setFly(sender,p,false);
                    return true;
                }
                setFly(sender,p,true);
                return true;
            }
            Msg.getInstance().notIsntanceoOfPlayer(sender);
            return true;
        }
        return false;
    }

    public void setFly(CommandSender sender, Player target, Boolean setflying){
        Boolean targetIsPlayer = false;
        if (sender instanceof Player){
            Player player = Bukkit.getServer().getPlayer(sender.getName());
            if (!sender.hasPermission(cmdPerm)) {
                Msg.getInstance().nopermission(player);
                return;
            }
            if (target != player) {
                if (!player.hasPermission(cmdPerm + ".other")) {
                    Msg.getInstance().nopermission(player);
                    return;
                }
            }
            if (target == player){
                targetIsPlayer = true;
            }
        }
        if(setflying){
            if (target.getAllowFlight()){
                if (!targetIsPlayer){
                    Msg.getInstance().sendSenderMSG(sender, "&a" + target.getName() + "&7 is allready &cflying&7.",true);
                    return;
                }
            }
            target.setFlying(true);
            target.setAllowFlight(true);
            Msg.getInstance().sendPlayerMSG(target,"&7Your flight &7has been &cenbaled&7.",true);
            if (!targetIsPlayer){
                Msg.getInstance().sendSenderMSG(sender,"&a" + target.getName() + "&7 flight has been &cenabled.",true);
            }
            return;
        }
            if (!target.getAllowFlight()){
                if (!targetIsPlayer){
                    Msg.getInstance().sendSenderMSG(sender, "&a" + target.getName() + "&7 is not &cflying&7.",true);
                    return;
                }
            }
            target.setFlying(false);
            target.setAllowFlight(false);
            Msg.getInstance().sendPlayerMSG(target,"&7Your flight has been &cenbaled&7.",true);
            if (!targetIsPlayer){
                Msg.getInstance().sendSenderMSG(sender,"&a" + target.getName() + "&7 flight has been &cenabled.",true);
            }
        }
}
