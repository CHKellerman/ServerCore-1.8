package burnedpuppies.servercore.other;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class IgnoreCheck {

    private static IgnoreCheck instance = null;

    protected IgnoreCheck() {
    }

    // Lazy Initialization (If required then only)
    public static IgnoreCheck getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (IgnoreCheck.class) {
                if (instance == null) {
                    instance = new IgnoreCheck();
                }
            }
        }
        return instance;
    }


    public boolean isIgnoring(String player,String target){
        List<String> ignoreList = PlayerConfigFileManager.getInstance().getFileConfiguration().getStringList(player + ".ignore");
        if (ignoreList.contains(target)){
            return true;
        }
        return false;
    }



    public void toggleIgnore(CommandSender sender, String player){
        if (sender instanceof Player){
            List<String> ignorelist = PlayerConfigFileManager.getInstance().getFileConfiguration().getStringList(sender.getName() + ".ignore");
            if (ignorelist == null){
                ignorelist.add(player);
                PlayerConfigFileManager.getInstance().getFileConfiguration().set(sender.getName() + ".ignore",ignorelist);
                PlayerConfigFileManager.getInstance().saveFile();
                Msg.getInstance().sendSenderMSG(sender,"&7You are now &cignoring &a" + player,true);
                return;
            }
            if (ignorelist.contains(player)){
                ignorelist.remove(player);
                PlayerConfigFileManager.getInstance().getFileConfiguration().set(sender.getName() + ".ignore",ignorelist);
                PlayerConfigFileManager.getInstance().saveFile();
                Msg.getInstance().sendSenderMSG(sender,"&7You are no longer &cignoring &a" + player,true);
                return;
            }
            ignorelist.add(player);
            PlayerConfigFileManager.getInstance().getFileConfiguration().set(sender.getName() + ".ignore",ignorelist);
            PlayerConfigFileManager.getInstance().saveFile();
            Msg.getInstance().sendSenderMSG(sender,"&7You are now &cignoring &a" + player,true);
            return;
        }
        Msg.getInstance().notIsntanceoOfPlayer(sender);
        return;
    }

}
