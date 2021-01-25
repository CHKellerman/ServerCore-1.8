package burnedpuppies.servercore.other;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Msg {
    public String prefix = ConfigManager.getInstance().getString("msg.prefix");

    private static Msg instance = null;

    protected Msg() {
    }

    // Lazy Initialization (If required then only)
    public static Msg getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (Msg.class) {
                if (instance == null) {
                    instance = new Msg();
                }
            }
        }
        return instance;
    }

    public void sendConsole(String msg,Boolean error){
        if (error){
            System.err.println(makeColored(msg,ConfigManager.getInstance().getString("msg.consoleprefix")));
            return;
        }
        System.out.println(makeColored(msg,ConfigManager.getInstance().getString("msg.consoleprefix")));
    }

    public String makeColored(String msg,String theprefix){
        String out = ChatColor.translateAlternateColorCodes('&',  theprefix + " " + msg);
        return out;
    }

    public void notIsntanceoOfPlayer(CommandSender sender){
        sender.sendMessage(makeColored(ConfigManager.getInstance().getString("msg.notisntanceofplayer"),prefix));
    }

    public void playernotonline(CommandSender player){
        player.sendMessage(makeColored(ConfigManager.getInstance().getString("msg.playernotonline"),prefix));
    }

    public void nopermission(Player player){
        player.sendMessage(makeColored(ConfigManager.getInstance().getString("msg.nopermission"), prefix));
    }

    public void sendPlayerMSG(Player player, String msg,Boolean addPrefix){
        String curprefix = "";
        if (addPrefix){
            curprefix = prefix;
        }
        player.sendMessage(makeColored(msg,curprefix));
    }

    public void sendSenderMSG(CommandSender sender, String msg,Boolean addPrefix){
        String curprefix = "";
        if (addPrefix){
            curprefix = prefix;
        }
        sender.sendMessage(makeColored(msg,curprefix));
    }
}
