package neocraft.livecraft

import neocraft.livecraft.command.ConfigCommand
import neocraft.livecraft.command.DebugCommand
import neocraft.livecraft.command.WsCommand
import neocraft.livecraft.config.PluginPreference
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
        ),
        Command(
                name = PluginCommands.DEBUG,
                permission = PluginPermissions.ADMIN,
                desc = "debug command",
                usage = "/<command>"
        ),
        Command(
                name = PluginCommands.CONFIG,
                permission = PluginPermissions.ADMIN,
                desc = "config command",
                usage = "/<command>"
        )
)
class LiveCraft : JavaPlugin() {

    var client: PluginWebSocketClient? = null
    var preference: PluginPreference? = null

    override fun onEnable() {
        saveDefaultConfig()

        preference = PluginPreference(this, this.config)

        getCommand(PluginCommands.WEB_SOCKET)?.setExecutor(WsCommand(this))
        getCommand(PluginCommands.DEBUG)?.setExecutor(DebugCommand(this))
        getCommand(PluginCommands.CONFIG)?.setExecutor(ConfigCommand(this))
    }

    override fun onDisable() {
    }
}