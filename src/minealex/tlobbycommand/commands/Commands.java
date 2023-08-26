package minealex.tlobbycommand.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.ChatColor;
import minealex.tlobbycommand.TLobbyCommand;

public class Commands extends Command {

    private final TLobbyCommand plugin;

    public Commands(TLobbyCommand plugin) {
        super("tlcversion", "tlc.version");
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("tlc.version")) {
            // Obtén la versión desde plugin.yml
            String version = plugin.getDescription().getVersion();

            // Obtener y enviar el mensaje personalizado del config
            String versionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("version-message"))
                    .replace("%version%", version);

            sender.sendMessage(versionMessage);
        } else {
            // Obtener y enviar el mensaje de falta de permiso personalizado del config
            String noPermissionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission-message"));

            sender.sendMessage(noPermissionMessage);
        }
    }
}
