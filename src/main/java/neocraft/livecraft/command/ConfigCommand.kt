package neocraft.livecraft.command

import neocraft.livecraft.LiveCraft
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ConfigCommand(private val plugin: LiveCraft) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return when (args.getOrNull(0)) {
            "comments" -> {
                val enable = args.getOrNull(1).toBoolean()
                plugin.preference?.setComments(enable)
                true
            }
            "gifts" -> {
                val enable = args.getOrNull(1).toBoolean()
                plugin.preference?.setGifts(enable)
                true
            }
            "gift_items" -> {
                val enable = args.getOrNull(1).toBoolean()
                plugin.preference?.setGiftItems(enable)
                true
            }
            else -> false
        }
    }
}