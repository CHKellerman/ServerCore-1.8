package burnedpuppies.servercore.other;

import burnedpuppies.servercore.cmds.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.Field;

public class EnableCMD {

    private static EnableCMD instance = null;

    protected EnableCMD() {
    }

    // Lazy Initialization (If required then only)
    public static EnableCMD getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (EnableCMD.class) {
                if (instance == null) {
                    instance = new EnableCMD();
                }
            }
        }
        return instance;
    }


    public void registerHealCMD(){
        if (ConfigManager.getInstance().getBoolean("commands.heal.enabled")){
            addCommand("heal", new Heal("Heal"));
            return;
        }
        Msg.getInstance().sendConsole("&6Heal &fcommand has been &cDisabled",false);
    }

    public void registerFeedCMD(){
        if (ConfigManager.getInstance().getBoolean("commands.feed.enabled")){
            addCommand("feed", new Feed("Feed"));
            return;
        }
        Msg.getInstance().sendConsole("&6Feed &fcommand has been &cDisabled",false);
    }

    public void registerGamemodeCMD(){
        if (ConfigManager.getInstance().getBoolean("commands.gamemode.enabled")) {
            addCommand("gamemode", new Gamemode("Gamemode"));
            addCommand("gms", new Gms("Gms"));
            addCommand("gmc", new Gmc("Gmc"));
            addCommand("gma", new Gma("Gma"));
            addCommand("gmspec", new Gmspec("Gmspec"));
            return;
        }
        Msg.getInstance().sendConsole("&6Gamemode &fcommands has been &cDisabled",false);
    }

    public void registerSpeedCMD(){
        if (ConfigManager.getInstance().getBoolean("commands.speed.enabled")){
            addCommand("speed", new Speed("Speed"));
            return;
        }
        Msg.getInstance().sendConsole("&6Speed &fcommand has been &cDisabled",false);
    }
    public void registerMessagesCMD(){
        addCommand("message",new Message("Message"));
        addCommand("socialspy",new Socialspy("Socialspy"));
        addCommand("ignore",new Ignore("Ignore"));
    }

    public void registerFlyCMD(){
        if (ConfigManager.getInstance().getBoolean("commands.fly.enabled")){
            addCommand("fly", new Fly("fly"));
            return;
        }
        Msg.getInstance().sendConsole("&6Fly &fcommand has been &cDisabled",false);
    }
    public void registerSudoCMD(){
        if (ConfigManager.getInstance().getBoolean("commands.sudo.enabled")){
            addCommand("sudo", new Sudo("Sudo"));
            return;
        }
        Msg.getInstance().sendConsole("&6Sudo &fcommand has been &cDisabled",false);
    }

    private void addCommand(String cmd, BukkitCommand bc) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register(cmd, bc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
