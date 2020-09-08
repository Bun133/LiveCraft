package neocraft.livecraft.command

import neocraft.livecraft.LiveCraft
import neocraft.livecraft.model.GiftItem
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class DebugCommand(private val plugin: LiveCraft) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return when (args.getOrNull(0)) {
            "gift" -> {
                val name = args.getOrNull(1) ?: return false
                val price = args.getOrNull(2)?.toIntOrNull() ?: return false
                GiftItem(name, price).give(plugin.server)
                true
            }
            else -> false
        }
    }
}