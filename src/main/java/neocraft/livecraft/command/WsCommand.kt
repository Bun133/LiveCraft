package neocraft.livecraft.command

import neocraft.livecraft.LiveCraft
import neocraft.livecraft.api.MildomUseCase
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class WsCommand(private val plugin: LiveCraft) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return when (args.getOrNull(0)) {
            "connect" -> {
                val roomId = args.getOrNull(1)?.toIntOrNull() ?: return false
                MildomUseCase(plugin).connect(roomId)
                true
            }
            "close" -> {
                plugin.client?.close()
                true
            }
            else -> false
        }
    }
}