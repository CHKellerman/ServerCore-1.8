package burnedpuppies.servercore.cmds;

import burnedpuppies.servercore.other.ConfigManager;
import burnedpuppies.servercore.other.Msg;
import burnedpuppies.servercore.other.SocialspyList;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

public class Socialspy extends BukkitCommand {
    public static String cmdPerm = ConfigManager.getInstance().getString("commands.socialspy.permission");

    public Socialspy(String name) {
        super(name);}

    public boolean execute(CommandSender sender, String str, String[] args) {
        if (sender.hasPermission(cmdPerm)){
            if (SocialspyList.getInstance().ssIsEnabled(sender.getName())){
                SocialspyList.getInstance().removePlayerSS(sender.getName());
                Msg.getInstance().sendSenderMSG(sender,"&7You have &cDisabled &7Socialspy",true);
                return false;
            }
            SocialspyList.getInstance().addPlayerSS(sender.getName());
            Msg.getInstance().sendSenderMSG(sender,"&7You have &cEnabled &7Socialspy",true);
        }
        return false;
    }
}
