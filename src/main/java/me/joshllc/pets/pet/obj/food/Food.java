package me.joshllc.pets.pet.obj.food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.joshllc.pets.util.items.ItemBuilder;
import me.joshllc.pets.util.text.C;
import org.bukkit.Material;

@AllArgsConstructor
@Getter
public enum Food {

    SEED("&aSeed", Material.WHEAT_SEEDS),
    FISH_FLAKES("&eFish Flakes", Material.COCOA_BEANS),
    TRASH("&2Trash", Material.SALMON_BUCKET),
    OYSTER("&fOyster", Material.PHANTOM_MEMBRANE),
    SALMON("&dSalmon", Material.SALMON_BUCKET),
    MEAT("&cMeat", Material.ROTTEN_FLESH);

    private String foodName;
    private Material foodMaterial;

    public ItemBuilder getFoodBuilder(Food food) {
        return new ItemBuilder(food.getFoodMaterial()).setName(C.colorize(food.getFoodName()));
    }
}
