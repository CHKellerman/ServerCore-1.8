package burnedpuppies.servercore.cmds;

import burnedpuppies.servercore.other.IgnoreCheck;
import burnedpuppies.servercore.other.Msg;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class Ignore extends BukkitCommand{
    public Ignore(String name) {super(name);}

    public boolean execute(CommandSender sender, String str, String[] args) {
        if (args.length == 1){
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target != null){
                Player player = Bukkit.getServer().getPlayer(sender.getName());
                if (player == target){
                    Msg.getInstance().sendSenderMSG(sender,"&cYou can not ignore yourself.",true);
                    return true;
                }
                IgnoreCheck.getInstance().toggleIgnore(sender,target.getName());
                return true;
            }
            Msg.getInstance().playernotonline(sender);
            return true;
        }
        Msg.getInstance().sendSenderMSG(sender,"&cUsages: /ignore (player)",true);
        return false;
    }
}
