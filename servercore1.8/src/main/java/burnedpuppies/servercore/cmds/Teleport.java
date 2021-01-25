package burnedpuppies.servercore.cmds;

import burnedpuppies.servercore.other.ConfigManager;
import burnedpuppies.servercore.other.Msg;
import burnedpuppies.servercore.other.teleport;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class Teleport extends BukkitCommand {

    public String cmdPerm = ConfigManager.getInstance().getString("commands.Teleport.permission");

    public Teleport(String name) {super(name);}

    public boolean execute(CommandSender sender, String str, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1){
                if (sender.hasPermission(cmdPerm)) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        Player p = Bukkit.getServer().getPlayer(sender.getName());
                        teleport.getInstance().teleportToPlayer(p,target);
                        return true;
                    }
                    Msg.getInstance().playernotonline(sender);
                    return true;
                }
            }
        }
        Msg.getInstance().notIsntanceoOfPlayer(sender);
        return false;
    }
}
