package burnedpuppies.servercore;

import burnedpuppies.servercore.other.EnableCMD;
import burnedpuppies.servercore.other.PlayerConfigFileManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Plugin plugin;

    public void onEnable(){
        plugin = this;
        registerCMD();
        registerConfig();
    }

    public void onDisable(){

    }

    private void registerCMD() {
        EnableCMD.getInstance().registerHealCMD();
        EnableCMD.getInstance().registerFeedCMD();
        EnableCMD.getInstance().registerGamemodeCMD();
        EnableCMD.getInstance().registerSpeedCMD();
        EnableCMD.getInstance().registerMessagesCMD();
        EnableCMD.getInstance().registerFlyCMD();
        EnableCMD.getInstance().registerSudoCMD();
    }


    private void registerConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        PlayerConfigFileManager.getInstance().loadFile();
    }


}
