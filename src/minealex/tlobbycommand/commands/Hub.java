package minealex.tlobbycommand.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.ChatColor;
import minealex.tlobbycommand.TLobbyCommand;
import java.util.Random;
import java.util.List;

public class Hub extends Command {

    private final TLobbyCommand plugin;

    public Hub(TLobbyCommand plugin) {
        super("hub");
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
                String hubMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("hub-message"));
                player.sendMessage(hubMessage);

                // Obtener y enviar el mensaje de conexión personalizado del config
                String hubConnectedMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("hub-connected-message"));
                player.sendMessage(hubConnectedMessage);
            } else {
            	player.sendMessage(ChatColor.RED + "No servers are available for redirection.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by players.");
        }
    }
}
