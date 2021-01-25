package burnedpuppies.servercore.cmds;

import burnedpuppies.servercore.other.ConfigManager;
import burnedpuppies.servercore.other.Msg;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;



public class Speed extends BukkitCommand {

    public String cmdPerm = ConfigManager.getInstance().getString("commands.speed.permission");

    public Speed(String name) {
        super(name);
    }

    public boolean execute(CommandSender sender, String str, String[] args) {
        if (args.length == 1){
            try {
                Double.parseDouble(args[0]);
            } catch(NumberFormatException e){
                Msg.getInstance().sendSenderMSG(sender,"&cPlease specify walk/fly. &7/speed (walk/fly) (speed)",true);
                return false;
            }
            if (sender instanceof Player){
                Player player = Bukkit.getServer().getPlayer(sender.getName());
                Boolean isfly = player.isFlying();
                float valcheck = Float.parseFloat(args[0]);
            if (valcheck <= 4){
                if (valcheck >= 0){
                    float val = valcheck/4;
                    setSpeed(sender,player, isfly, val);
                    return true;
                }
            }
                Msg.getInstance().sendSenderMSG(sender, "&cSpeed value can only be between 0-4",true);
                return true;
            }
            Msg.getInstance().sendConsole("You can not execute this command thur console.",false);
            return true;
        }
        if (args.length == 2){
            if (sender instanceof Player) {
                if (args[0].equalsIgnoreCase("walk")) {
                    Player player = Bukkit.getServer().getPlayer(sender.getName());
                    Boolean isfly = false;
                    float valcheck = Float.parseFloat(args[1]);
                    if (valcheck <= 4){
                        if (valcheck >= 0){
                            float val = valcheck/4;
                            setSpeed(sender,player, isfly, val);
                            return true;
                        }
                    }
                    Msg.getInstance().sendSenderMSG(sender, "&cSpeed value can only be between 0-4",true);
                    return true;
                }
                if (args[0].equalsIgnoreCase("fly")) {
                    Player player = Bukkit.getServer().getPlayer(sender.getName());
                    Boolean isfly = true;
                    float valcheck = Float.parseFloat(args[1]);
                    if (valcheck <= 4){
                        if (valcheck >= 0){
                            float val = valcheck/4;
                            setSpeed(sender,player, isfly, val);
                            return true;
                        }
                    }
                    Msg.getInstance().sendSenderMSG(sender, "&cSpeed value can only be between 0-4",true);
                    return true;
                }
                Msg.getInstance().sendSenderMSG(sender,"&cPlease specify walk/fly. &7/speed (walk/fly) (speed)",true);
            return true;
            }
            Msg.getInstance().sendConsole("You can not execute this command thur console.",false);
            return true;
        }
        if (args.length == 3){
            if (args[0].equalsIgnoreCase("walk")) {
                Player target = Bukkit.getServer().getPlayer(args[2]);
                if (target != null) {
                    Boolean isfly = false;
                    float valcheck = Float.parseFloat(args[2]);
                    if (valcheck <= 4) {
                        if (valcheck >= 0) {
                            float val = valcheck / 4;
                            setSpeed(sender, target, isfly, val);
                            return true;
                        }
                    }
                    Msg.getInstance().sendSenderMSG(sender, "&cSpeed value can only be between 0-4", true);
                    return true;
                }
                Msg.getInstance().playernotonline(sender);
                return true;
            }
            if (args[0].equalsIgnoreCase("fly")) {
                Player target = Bukkit.getServer().getPlayer(args[2]);
                if (target != null) {
                    Boolean isfly = true;
                    float valcheck = Float.parseFloat(args[2]);
                    if (valcheck <= 4) {
                        if (valcheck >= 0) {
                            float val = valcheck / 4;
                            setSpeed(sender, target, isfly, val);
                            return true;
                        }
                    }
                    Msg.getInstance().sendSenderMSG(sender, "&cSpeed value can only be between 0-4", true);
                    return true;
                }
                Msg.getInstance().playernotonline(sender);
                return true;
            }
            Msg.getInstance().sendSenderMSG(sender,"&cPlease specify walk/fly. &7/speed (walk/fly) (speed)",true);
            return true;
        }
        Msg.getInstance().sendSenderMSG(sender,"&cUsage:/speed (walk/fly) (speed) [player]",true);
        return false;
    }


    public void setSpeed(CommandSender sender, Player target, Boolean isFlying, float value){
        Boolean targetIsPlayer = false;
        if (sender instanceof Player) {
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
        if (isFlying){
          target.setFlySpeed(value);
          Msg.getInstance().sendPlayerMSG(target,"&7Your &cflying &7speed was set." ,true);
          if (!targetIsPlayer){
              Msg.getInstance().sendSenderMSG(sender, "&7You have set &a" + target.getName() + "&c flying &7speed.",true);
          }
          return;
        }
        target.setWalkSpeed(value);
        Msg.getInstance().sendPlayerMSG(target,"&7Your &cwalk &7speed was set." ,true);
        if (!targetIsPlayer){
            Msg.getInstance().sendSenderMSG(sender, "&7You have set &a" + target.getName() + "&c walk &7speed.",true);
        }
    }
}
