package uk.radialbog9.spigot.manhunt.kits;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import uk.radialbog9.spigot.manhunt.Manhunt;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings({"ConstantConditions"})
public class KitProvider {
    @Getter
    private HashMap<String, Kit> hunterKits;

    @Getter
    private HashMap<String, Kit> runnerKits;

    public KitProvider() {
        // Hunter kits
        hunterKits = getKitsFromConfig(Manhunt.getInstance().getConfig().getConfigurationSection("kits.hunters"));
        // Runner kits
        runnerKits = getKitsFromConfig(Manhunt.getInstance().getConfig().getConfigurationSection("kits.runners"));
    }

    /**
     * Imports the kits from the specified file
     * @param kitSection the configuration section containing the kits
     */
    public HashMap<String, Kit> getKitsFromConfig(ConfigurationSection kitSection) {
        HashMap<String, Kit> kits = new HashMap<>();
        for (String key : kitSection.getKeys(false)) {
            ConfigurationSection kitsec = kitSection.getConfigurationSection(key);
            ArrayList<ItemStack> itemStacks = new ArrayList<>();
            for (String stack : kitsec.getConfigurationSection("items").getKeys(false)) {
                int amount = Math.min(kitsec.getConfigurationSection(stack).getInt("quantity"), 64);
                ItemStack itemStack = new ItemStack(Material.getMaterial(kitsec.getConfigurationSection(stack).getString("item")), amount);
                itemStacks.add(itemStack);
            }
            Kit thisKit = new Kit(key, itemStacks);
            kits.put(key, thisKit);
        }
        return kits;
    }
}
