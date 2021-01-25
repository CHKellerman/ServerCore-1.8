package burnedpuppies.servercore.cmds;

import burnedpuppies.servercore.other.ConfigManager;
import burnedpuppies.servercore.other.Msg;
import burnedpuppies.servercore.other.setGamemode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Gms extends BukkitCommand {

    public Gms(String name) {
        super(name);
        this.setAliases(Arrays.asList("gmsurvival"));
    }

    public boolean execute(CommandSender sender, String str, String[] args) {
        if (args.length == 0){
            Player player = Bukkit.getServer().getPlayer(sender.getName());
            setGamemode.getInstance().setPGamemode(sender,player, GameMode.SURVIVAL);
            return true;
        }
        if (args.length == 1){
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null){
                burnedpuppies.servercore.other.Msg.getInstance().playernotonline(sender);
                return true;
            }
            setGamemode.getInstance().setPGamemode(sender,target,GameMode.SURVIVAL);
        }
        Msg.getInstance().sendSenderMSG(sender,"&cUsage: /gms &7[player]",true);
        return false;
    }
}
