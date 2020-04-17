package de.blockstube.userplugin.inventory;

import de.blockstube.userplugin.UserSystem;
import de.blockstube.userplugin.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author schlingeldrops
 * This is a user inventory, with this you can delete a user profile.
 */
public class UserInventory {

    private final Inventory inventory;
    private final UserInventoryListener listener = new UserInventoryListener();

    public UserInventory() {
        inventory = Bukkit.createInventory(null, 9*3, "§eBenutzer Interface");
        inventory.setItem(13, ItemBuilder.create(Material.BARRIER).setDisplayName("§cLöschen").build());
    }

    /**
     * Enable the gui listener.
     * @param javaPlugin The plugin.
     */
    public void enable(JavaPlugin javaPlugin) {
        Bukkit.getPluginManager().registerEvents(listener, javaPlugin);
    }

    /**
     * Disable the gui listener.
     */
    public void disable() {
        HandlerList.unregisterAll(listener);
    }

    /**
     * Open the gui.
     * @param player The player.
     */
    public void open(Player player) {
        player.openInventory(inventory);
    }

    private final class UserInventoryListener implements Listener {

        @EventHandler
        public void onInventoryClick(InventoryClickEvent event) {
            if (!(event.getWhoClicked() instanceof Player))
                return;

            final Player player = (Player) event.getWhoClicked();

            if (!event.getInventory().equals(inventory))
                return;

            event.setCancelled(true);

            if (event.getSlot() == 13) {
                UserSystem.getInstance().deleteUser(player);
                player.closeInventory();
            }
        }

    }


}
