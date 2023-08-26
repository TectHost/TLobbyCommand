package minealex.tlobbycommand;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

import net.md_5.bungee.api.plugin.Plugin;
import minealex.tlobbycommand.commands.Commands;
import minealex.tlobbycommand.commands.Hub;
import minealex.tlobbycommand.commands.Lobby;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

@SuppressWarnings("unused")
public class TLobbyCommand extends Plugin {

    private Configuration config;

    @Override
    public void onEnable() {
        // Registrar los comandos
        getProxy().getPluginManager().registerCommand(this, new Hub(this));
        getProxy().getPluginManager().registerCommand(this, new Lobby(this));
        getProxy().getPluginManager().registerCommand(this, new Commands(this));
        
        // Cargar la configuración desde config.yml
        loadConfig();
        
        // Resto de la lógica de inicialización de tu plugin
    }
    
    private void loadConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File configFile = new File(getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            saveDefaultConfig(configFile);
        }

        config = getConfig(configFile);
    }

    private void saveDefaultConfig(File configFile) {
        if (!configFile.exists()) {
            try (InputStream is = getResourceAsStream("config.yml")) {
                Files.copy(is, configFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Configuration getConfig(File configFile) {
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Configuration getConfig() {
        return config;
    }
}
