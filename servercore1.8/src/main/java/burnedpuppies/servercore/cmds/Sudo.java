package burnedpuppies.servercore.cmds;

import burnedpuppies.servercore.other.ConfigManager;
import burnedpuppies.servercore.other.Msg;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class Sudo extends BukkitCommand {

    public String cmdPerm = ConfigManager.getInstance().getString("commands.sudo.permission");

    public Sudo(String name) {
        super(name);
    }

    public boolean execute(CommandSender sender, String str, String[] args) {
        if (sender.hasPermission(cmdPerm)) {
            Player target = Bukkit.getServer().getPlayer(sender.getName());
            if (args.length >= 3) {
                if (target != null) {
                    String cmd = "";
                    for (int i = 1; i < args.length; i++) {
                        cmd = cmd + (args[i] + " ");
                    }
                    target.chat(cmd);
                    Msg.getInstance().sendSenderMSG(sender,"&7You made &a" + target.getName() + " &7execute &c+" + cmd,true);
                    return true;
                }
                Msg.getInstance().playernotonline(sender);
                return true;
            }
        Msg.getInstance().sendSenderMSG(sender,"&cUsages: /sudo (player) {message/command}",true);
            return true;
        }
        return false;
    }
}
