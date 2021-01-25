package burnedpuppies.servercore.other;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SocialspyList {
    private static SocialspyList instance = null;

    protected SocialspyList() {
    }

    // Lazy Initialization (If required then only)
    public static SocialspyList getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (SocialspyList.class) {
                if (instance == null) {
                    instance = new SocialspyList();
                }
            }
        }
        return instance;
    }
    public static ArrayList<String> enbaledUsers;
    List<String> SSenabledUsers;

    public void SendSS(String msg, Player target, Player sender){
        SSenabledUsers = ConfigManager.getInstance().getStringList("socialspy");
        for (String cur : SSenabledUsers){
            Player p = Bukkit.getServer().getPlayer(cur);
            if (p != null){
                if (p.hasPermission(ConfigManager.getInstance().getString("commands.socialspy.permission"))){
                    Msg.getInstance().sendPlayerMSG(p,"&6SS - &a" + sender.getName() + " &e-> &b" + target.getName() + "&f : " +msg,false);
                    return;
                }
                removePlayerSS(cur);
            }
        }
    }

    public void removePlayerSS(String player){
        if (SSenabledUsers.contains(player)){
            SSenabledUsers.remove(player);
            ConfigManager.getInstance().SetObject(SSenabledUsers,"socialspy");
        }

    }
    public void addPlayerSS(String player){
        if (!SSenabledUsers.contains(player)){
            SSenabledUsers.add(player);
            ConfigManager.getInstance().SetObject(SSenabledUsers,"socialspy");
        }
    }

    public Boolean ssIsEnabled(String player){
        if (SSenabledUsers.contains(player)){
            return true;
        }
        return false;
    }

}
