package minealex.tlobbycommand.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.ChatColor;
import minealex.tlobbycommand.TLobbyCommand;
import java.util.Random;
import java.util.List;

public class Lobby extends Command {

    private final TLobbyCommand plugin;

    public Lobby(TLobbyCommand plugin) {
        super("lobby");
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            List<String> servers = plugin.getConfig().getStringList("servers");
            
            if (!servers.isEmpty()) {
                String randomServer = servers.get(new Random().nextInt(servers.size()));
                player.connect(plugin.getProxy().getServerInfo(randomServer));

                // Obtener y enviar el mensaje personalizado del config
                String lobbyMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("lobby-message"));
                player.sendMessage(lobbyMessage);

                // Obtener y enviar el mensaje de conexión personalizado del config
                String lobbyConnectedMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("lobby-connected-message"));
                player.sendMessage(lobbyConnectedMessage);
            } else {
                player.sendMessage(ChatColor.RED + "No servers are available for redirection.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by players.");
        }
    }
}
