package neocraft.livecraft.config

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin

class PluginPreference(private val plugin: JavaPlugin, private val config: FileConfiguration) {
    private object Keys {
        const val COMMENTS = "comments"
        const val GIFTS = "gifts"
        const val GIFT_ITEMS = "gift_items"
    }

    fun getComments(): Boolean {
        return config.getBoolean(Keys.COMMENTS)
    }

    fun setComments(value: Boolean) {
        set(Keys.COMMENTS, value)
    }

    fun getGifts(): Boolean {
        return config.getBoolean(Keys.GIFTS)
    }

    fun setGifts(value: Boolean) {
        set(Keys.GIFTS, value)
    }

    fun getGiftItems(): Boolean {
        return config.getBoolean(Keys.GIFT_ITEMS)
    }

    fun setGiftItems(value: Boolean) {
        set(Keys.GIFT_ITEMS, value)
    }

    private fun set(key: String, value: Any?) {
        config.set(key, value)
        plugin.saveConfig()
    }
}