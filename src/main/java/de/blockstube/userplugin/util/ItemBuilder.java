package de.blockstube.userplugin.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.List;
import java.util.Map;

/**
 * @author schlingeldrops
 * This is a simple item builder.
 */
public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    private ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public static ItemBuilder create(Material material) {
        return new ItemBuilder(new ItemStack(material));
    }

    public void setType(Material type) {
        itemStack.setType(type);
    }

    @Deprecated
    public ItemBuilder setTypeId(int type) {
        itemStack.setTypeId(type);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setData(MaterialData data) {
        itemStack.setData(data);
        return this;
    }

    public ItemBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        itemStack.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment ench, int level) {
        itemStack.addEnchantment(ench, level);
        return this;
    }

    public ItemBuilder addUnsafeEnchantments(Map<Enchantment, Integer> enchantments) {
        itemStack.addUnsafeEnchantments(enchantments);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        itemStack.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench) {
        itemStack.removeEnchantment(ench);
        return this;
    }

    public ItemBuilder setDisplayName(String s) {
        itemMeta.setDisplayName(s);
        return this;
    }

    public ItemBuilder setLore(List<String> list) {
        itemMeta.setLore(list);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int i, boolean b) {
        itemMeta.addEnchant(enchantment, i, b);
        return this;
    }

    public ItemBuilder removeEnchant(Enchantment enchantment) {
        itemMeta.removeEnchant(enchantment);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder removeItemFlags(ItemFlag... itemFlags) {
        itemMeta.removeItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        itemMeta.spigot().setUnbreakable(unbreakable);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
