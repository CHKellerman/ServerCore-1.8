package burnedpuppies.servercore.cmds;

import burnedpuppies.servercore.other.ConfigManager;
import burnedpuppies.servercore.other.Msg;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import java.util.Map;

public class Feed extends BukkitCommand {
   public String cmdPerm = ConfigManager.getInstance().getString("commands.feed.permission");
   public Map<Player, Long> feedCooldown = Maps.newHashMap();
   public Integer delay = ConfigManager.getInstance().getInteger("commands.feed.delay");


    public Feed(String name) {super(name);}

    public boolean execute(CommandSender sender, String str, String[] args) {

        if (args.length == 0){
            if (sender instanceof Player){
                Player player = Bukkit.getServer().getPlayer(sender.getName());
                if (sender.hasPermission(cmdPerm)){
                    if (feedCooldown.containsKey(player)){
                        long secleft = (feedCooldown.get(player) / 1000 + delay) - (System.currentTimeMillis() / 1000);
                        if (secleft > 0) {
                            Msg.getInstance().sendPlayerMSG(player,"&7You have to wait &c" + secleft +" seconds &7 befor you can feed yourself again.",true);
                            return true;
                        }
                    }
                    if (!player.hasPermission(cmdPerm + ".bypass")){
                        feedCooldown.put(player,System.currentTimeMillis());
                    }
                    feedPlayer(player);
                    return true;
                }
                Msg.getInstance().nopermission(player);
                return true;
            }
            Msg.getInstance().notIsntanceoOfPlayer(sender);
            return true;
        }

        if (args.length == 1){
            if (sender instanceof Player){
                Player player = Bukkit.getServer().getPlayer(sender.getName());
                if (!sender.hasPermission(cmdPerm + ".other")){
                    Msg.getInstance().nopermission(player);
                    return true;
                }
            }
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target != null) {
                feedPlayer(target);
                Msg.getInstance().sendSenderMSG(sender,"&a" + target.getName() + "'s &7food has been &creplenished",true);
                return false;
            }
            Msg.getInstance().playernotonline(sender);
            return true;
        }
        Msg.getInstance().sendSenderMSG(sender,"&cUsage: /feed &7[player]",true);
        return false;
    }

    public void feedPlayer(Player player){
        player.setFoodLevel(20);
        Msg.getInstance().sendPlayerMSG(player,"&7Your food has been &cReplenished",true);
    }
}
