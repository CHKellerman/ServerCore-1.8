package burnedpuppies.servercore.other;

import burnedpuppies.servercore.Main;

import java.util.List;

public class ConfigManager {
    private static ConfigManager instance = null;

    protected ConfigManager() {
    }

    // Lazy Initialization (If required then only)
    public static ConfigManager getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    public int getInteger(String path) {
        return Main.plugin.getConfig().getInt(path);
    }

    public String getString(String path) {
        return Main.plugin.getConfig().getString(path);
    }

    public List<String> getStringList(String path) {
        return Main.plugin.getConfig().getStringList(path);
    }

    public Boolean getBoolean(String path){
        return Main.plugin.getConfig().getBoolean(path);
    }

    public void SetObject(Object obj,String path ){
        Main.plugin.getConfig().set(path, obj);
        Main.plugin.saveConfig();
    }

}
