package neocraft.livecraft

import neocraft.livecraft.command.WsCommand
import neocraft.livecraft.ws.PluginWebSocketClient
import org.bukkit.permissions.PermissionDefault
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.annotation.command.Command
import org.bukkit.plugin.java.annotation.command.Commands
import org.bukkit.plugin.java.annotation.permission.Permission
import org.bukkit.plugin.java.annotation.permission.Permissions
import org.bukkit.plugin.java.annotation.plugin.ApiVersion
import org.bukkit.plugin.java.annotation.plugin.Plugin

@Plugin(name = "LiveCraft", version = "1.0-SNAPSHOT")
@ApiVersion(ApiVersion.Target.v1_15)
@Permissions(
        Permission(
                name = PluginPermissions.ADMIN,
                desc = "Gives access to LiveCraft admin commands",
                defaultValue = PermissionDefault.OP
        )
)
@Commands(
        Command(
                name = PluginCommands.WEB_SOCKET,
                permission = PluginPermissions.ADMIN,
                desc = "web socket command",
                usage = "/<command>"
        )
)
class LiveCraft : JavaPlugin() {

    var client: PluginWebSocketClient? = null

    override fun onEnable() {
        getCommand(PluginCommands.WEB_SOCKET)?.setExecutor(WsCommand(this))
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}