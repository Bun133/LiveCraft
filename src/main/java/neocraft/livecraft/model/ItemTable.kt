package neocraft.livecraft.model

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import java.util.*


class ItemTable(
        private val userName: String,
        private val prise: Int
) {
    val commonItems = listOf(
            ItemStack(Material.BREAD),
            ItemStack(Material.CARROT),
            ItemStack(Material.IRON_INGOT),
            ItemStack(Material.COAL),
            ItemStack(Material.ARROW, 10)
    )

    val rareItems = listOf(
            ItemStack(Material.GOLDEN_APPLE),
            ItemStack(Material.GOLDEN_CARROT),
            ItemStack(Material.DIAMOND),
            ItemStack(Material.TNT),
            ItemStack(Material.MILK_BUCKET),
            ItemStack(Material.GOLD_INGOT, 64),
            ItemStack(Material.IRON_INGOT, 64)
    )

    val rareEquipItems = listOf(
            rareSword(userName, prise),
            rarePickaxe(userName, prise),
            rareBow(userName, prise)
    )

    val epicItems = listOf(
            ItemStack(Material.ENCHANTED_GOLDEN_APPLE),
            ItemStack(Material.DIAMOND, 64),
            ItemStack(Material.NETHER_STAR),
            ItemStack(Material.OBSIDIAN, 64),
            ItemStack(Material.TNT, 64),
    )

    val epicEquipItems = listOf(
            epicSword(userName, prise),
            epicPickaxe(userName, prise),
            epicBow(userName, prise)
    )

    fun getItem(): ItemStack? {
        if (prise < 1000) {
            var rate = Random().nextInt(1000)
            rate += prise
            if (rate > 999) {
                return ItemStack(Material.DIAMOND)
            }
            return commonItems[Random().nextInt(commonItems.size)]
        } else if (prise in 1000..4999) {
            var rate = Random().nextInt(4999)
            rate += prise
            if (rate > 4999) {
                return rareEquipItems[Random().nextInt(rareEquipItems.size)]
            }
            return rareItems[Random().nextInt(rareItems.size)]
        } else if (prise > 5000) {
            var rate = Random().nextInt(10000)
            rate += prise
            if (rate > 10000) {
                return epicEquipItems[Random().nextInt(epicEquipItems.size)]
            }
            return epicItems[Random().nextInt(epicItems.size)]
        } else {
            return null
        }
    }

    private fun rareSword(userName: String, prise: Int): ItemStack {
        val enchantments = mapOf(
                Enchantment.DAMAGE_ALL to 3
        )
        return createItemStack(ChatColor.BLUE, userName, prise, Material.IRON_SWORD, enchantments)
    }

    private fun rarePickaxe(userName: String, prise: Int): ItemStack {
        val enchantments = mapOf(
                Enchantment.DIG_SPEED to 3
        )
        return createItemStack(ChatColor.BLUE, userName, prise, Material.IRON_PICKAXE, enchantments)
    }

    private fun rareBow(userName: String, prise: Int): ItemStack {
        val enchantments = mapOf(
                Enchantment.ARROW_DAMAGE to 5
        )
        return createItemStack(ChatColor.BLUE, userName, prise, Material.BOW, enchantments)
    }

    private fun epicSword(userName: String, prise: Int): ItemStack {
        val enchantments = mapOf(
                Enchantment.DAMAGE_ALL to 5
        )
        return createItemStack(ChatColor.DARK_PURPLE, userName, prise, Material.DIAMOND_SWORD, enchantments)
    }

    private fun epicPickaxe(userName: String, prise: Int): ItemStack {
        val enchantments = mapOf(
                Enchantment.DIG_SPEED to 5
        )
        return createItemStack(ChatColor.DARK_PURPLE, userName, prise, Material.DIAMOND_PICKAXE, enchantments)
    }

    private fun epicBow(userName: String, prise: Int): ItemStack {
        val enchantments = mapOf(
                Enchantment.ARROW_DAMAGE to 5,
                Enchantment.ARROW_INFINITE to 1
        )
        return createItemStack(ChatColor.DARK_PURPLE, userName, prise, Material.BOW, enchantments)
    }

    private fun getItem(itemTable: Map<ItemStack, Int>): ItemStack? {
        val rate = Random().nextInt(100000)
        var itemStack: ItemStack? = null
        var count = 0

        itemTable.forEach { dropItem, num ->
            if (rate in count..(count + num)) {
                itemStack = dropItem
            }
            count += num
        }

        return itemStack
    }

    private fun createItemStack(
            color: ChatColor,
            userName: String,
            prise: Int,
            material: Material,
            enchantments: Map<Enchantment, Int>
    ): ItemStack {
        val resultItemStack = ItemStack(material)
        enchantments.forEach { enchantment, level ->
            resultItemStack.addEnchantment(enchantment, level)
        }
        val itemMeta = resultItemStack.itemMeta
        itemMeta?.setDisplayName("${color}${userName} ${material.name}")
        itemMeta?.lore = listOf("${prise}コインのギフト")
        resultItemStack.itemMeta = itemMeta
        return resultItemStack
    }
}