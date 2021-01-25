package burnedpuppies.servercore.cmds;

import burnedpuppies.servercore.other.IgnoreCheck;
import burnedpuppies.servercore.other.Msg;
import burnedpuppies.servercore.other.SocialspyList;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Message extends BukkitCommand {
    public Message(String name) {
        super(name);
        this.setAliases(Arrays.asList("msg", "pm", "privatemessage", "dm", "directmessage","tell","whisper"));
    }

    public boolean execute(CommandSender sender, String str, String[] args) {
        if (args.length == 0 || args.length == 1){
            Msg.getInstance().sendSenderMSG(sender,"&cUsages: /message (player) {message}",true);
            return true;
        }
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null){
            Msg.getInstance().playernotonline(sender);
            return true;
        }
        String msg = "";
        for (int i = 1; i < args.length; i++) {
            msg = msg + (args[i] + " ");
        }
        if (IgnoreCheck.getInstance().isIgnoring(target.getName(),sender.getName())){
            if (!sender.isOp()){
                Msg.getInstance().sendSenderMSG(sender,"&cThis player is ignoring you!",true);

                return true;
            }
        }
        if (IgnoreCheck.getInstance().isIgnoring(sender.getName(),target.getName())){
            if (!sender.isOp()){
                Msg.getInstance().sendSenderMSG(sender,"&cYou are ignoring this player!",true);
                return true;
            }
        }
        Msg.getInstance().sendSenderMSG(sender,"&8[ &cMe &e&l-> &a" + target.getName() + " &8] &e" + msg,false);
        Msg.getInstance().sendPlayerMSG(target, "&8[ &a" + sender.getName() +" &e&l-> &cMe &8] &e" + msg,false);
        Player p = Bukkit.getServer().getPlayer(sender.getName());
        SocialspyList.getInstance().SendSS(msg,target,p);
        return false;
    }
}
