package neocraft.livecraft.model

import org.bukkit.ChatColor
import org.bukkit.Server
import org.bukkit.Sound

class GiftItem(
        private val userName: String,
        private val prise: Int
) {
    fun give(server: Server) {
        val itemStack = ItemTable(userName, prise).getItem() ?: return
        server.onlinePlayers.forEach {
            if (it.inventory.firstEmpty() == -1) {
                it.location.world.dropItem(it.location, itemStack)
            } else {
                it.inventory.addItem(itemStack)
            }
            it.playSound(it.location, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.2f)
        }
        val itemName: String = itemStack.itemMeta.displayName
        if (itemName.isEmpty()) {
            server.broadcastMessage("${ChatColor.YELLOW}アイテム を入手しました")
        } else {
            server.broadcastMessage("${itemStack.itemMeta} ${ChatColor.YELLOW}を入手しました")
        }
    }
}