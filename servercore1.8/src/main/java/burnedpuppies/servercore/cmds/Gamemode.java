package burnedpuppies.servercore.cmds;

import burnedpuppies.servercore.other.Msg;
import burnedpuppies.servercore.other.setGamemode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Gamemode extends BukkitCommand {

    public Gamemode(String name) {
        super(name);
        this.setAliases(Arrays.asList("gmode", "gm"));
    }

    public boolean execute(CommandSender sender, String str, String[] args) {
        if (args.length == 0){
            Msg.getInstance().sendSenderMSG(sender,"&cUsage: /gamemode (gamemode) &7[player]",true);
        }
        if (args.length == 1){
            if (sender instanceof Player){
                Player player = Bukkit.getServer().getPlayer(sender.getName());
                String gamemode = args[0].toLowerCase();
                if (gamemode.equalsIgnoreCase("SURVIVAL") || gamemode.equalsIgnoreCase("0") || gamemode.equalsIgnoreCase("s")){
                    setGamemode.getInstance().setPGamemode(sender,player,GameMode.SURVIVAL);
                    return true;
                }
                if (gamemode.equalsIgnoreCase("CREATIVE") || gamemode.equalsIgnoreCase("1")|| gamemode.equalsIgnoreCase("c")){
                    setGamemode.getInstance().setPGamemode(sender,player,GameMode.CREATIVE);
                    return true;
                }
                if (gamemode.equalsIgnoreCase("ADVENTURE") || gamemode.equalsIgnoreCase("2")|| gamemode.equalsIgnoreCase("a")){
                    setGamemode.getInstance().setPGamemode(sender,player,GameMode.ADVENTURE);
                    return true;
                }
                if (gamemode.equalsIgnoreCase("SPECTATOR") || gamemode.equalsIgnoreCase("3")|| gamemode.equalsIgnoreCase("spec")){
                    setGamemode.getInstance().setPGamemode(sender,player,GameMode.SPECTATOR);
                    return true;
                }
            }
        }

        if (args.length == 2){
            Player target = Bukkit.getServer().getPlayer(args[1]);
            if (target == null){
                Msg.getInstance().playernotonline(sender);
                return true;
            }
            String gamemode = args[0].toLowerCase();
            if (gamemode.equalsIgnoreCase("SURVIVAL") || gamemode.equalsIgnoreCase("0")|| gamemode.equalsIgnoreCase("s")){
                setGamemode.getInstance().setPGamemode(sender,target,GameMode.SURVIVAL);
                return true;
            }
            if (gamemode.equalsIgnoreCase("CREATIVE") || gamemode.equalsIgnoreCase("1")|| gamemode.equalsIgnoreCase("c")){
                setGamemode.getInstance().setPGamemode(sender,target,GameMode.CREATIVE);
                return true;
            }
            if (gamemode.equalsIgnoreCase("ADVENTURE") || gamemode.equalsIgnoreCase("2")|| gamemode.equalsIgnoreCase("a")){
                setGamemode.getInstance().setPGamemode(sender,target,GameMode.ADVENTURE);
                return true;
            }
            if (gamemode.equalsIgnoreCase("SPECTATOR") || gamemode.equalsIgnoreCase("3")|| gamemode.equalsIgnoreCase("spec")){
                setGamemode.getInstance().setPGamemode(sender,target,GameMode.SPECTATOR);
                return true;
            }
        }
        Msg.getInstance().sendSenderMSG(sender,"&cUsage: /gamemode (gamemode) &7[player]",true);
        return false;
    }
}
