package burnedpuppies.servercore.other;

import burnedpuppies.servercore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerConfigFileManager {

    private static PlayerConfigFileManager instance = null;

    protected PlayerConfigFileManager() {
    }

    // Lazy Initialization (If required then only)
    public static PlayerConfigFileManager getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (PlayerConfigFileManager.class) {
                if (instance == null) {
                    instance = new PlayerConfigFileManager();
                }
            }
        }
        return instance;
    }
    private File file;

    private FileConfiguration fileConfiguration;


    public void loadFile(){
        file = new File(Main.plugin.getDataFolder(), "playerdata.yml");
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        saveFile();
    }

    public void saveFile(){
        try{
            fileConfiguration.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public FileConfiguration getFileConfiguration(){
        return fileConfiguration;
    }

}
