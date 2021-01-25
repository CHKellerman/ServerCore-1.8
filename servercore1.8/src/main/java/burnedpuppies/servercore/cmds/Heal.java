package burnedpuppies.servercore.cmds;

import burnedpuppies.servercore.other.ConfigManager;
import burnedpuppies.servercore.other.Msg;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Map;

public class Heal extends BukkitCommand{
    public String cmdPerm = ConfigManager.getInstance().getString("commands.heal.permission");
    public Map<Player, Long> healCooldown = Maps.newHashMap();
    public Integer delay = ConfigManager.getInstance().getInteger("commands.heal.delay");


    public Heal(String name) {super(name);}

    public boolean execute(CommandSender sender, String str, String[] args) {

        if (args.length == 0){
            if (sender instanceof Player){
                Player player = Bukkit.getServer().getPlayer(sender.getName());
                if (sender.hasPermission(cmdPerm)){
                    if (healCooldown.containsKey(player)){
                        long secleft = (healCooldown.get(player) / 1000 + delay) - (System.currentTimeMillis() / 1000);
                        if (secleft > 0) {
                            Msg.getInstance().sendPlayerMSG(player,"&7You have to wait &c" + secleft +" seconds &7 befor you can heal yourself again.",true);
                            return true;
                        }
                    }
                    if (!player.hasPermission(cmdPerm + ".bypass")){
                        healCooldown.put(player,System.currentTimeMillis());
                    }
                    healPlayer(player);
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
                healPlayer(target);
                Msg.getInstance().sendSenderMSG(sender,"&a" + target.getName() + "'s &7health has been &creplenished",true);
                return false;
            }
            Msg.getInstance().playernotonline(sender);
            return true;
        }
        Msg.getInstance().sendSenderMSG(sender,"&cUsage: /Heal &7[player]",true);
        return false;
    }

    public void healPlayer(Player player){
        player.setFoodLevel(20);
        player.setHealth(20);
        for (PotionEffect cur : player.getActivePotionEffects()){
            player.removePotionEffect(cur.getType());
        }
        Msg.getInstance().sendPlayerMSG(player,"&7Your health has been &cReplenished",true);
    }
}
