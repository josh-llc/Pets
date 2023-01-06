package me.joshllc.pets.util.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@AllArgsConstructor
@Getter
public enum CustomHead {

    CHICK("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDliMjJjNmZjYTBlMDZiYzFmNWFhY2JhZDY2NDA2ZWRmNmRhNzk4MDY5MjViOTI0Y2FiOGU0Y2JkYmRjZDUyYSJ9fX0="),
    AXOLOTL("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTIxNDllYjhhNDg5ZDhiMDM1OGQ5ODk1NjBjZDI3MDRiMjU2NGFjYjkxY2JmYjZkMDE0NmYzNWNjMDRhM2ZmIn19fQ=="),
    RACOON("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmU5ZGFhOTllZTQ1YzAxMWVkMDc5ZDhmYzhhN2ViNzFiNTk2Yjg2NDQxNzA4YWQwOTU5ODNiNjAxZGIyMTE0YiJ9fX0="),
    OTTER("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjczYjI5MTgxYjFhOGU1ZjI4MjZiNGFjOGQzMGZjNTJjMDZhZTBiZTAwMDUyOGM0NmFlOGExZTdmNTNkYWM4ZCJ9fX0="),
    PENGUIN("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDljYTgwZWI0OGIwODFjYTZiNTc4YmE2MzlhOThmZGRkOWYxMTkxZWU4OTJhMzc5ZDMxNDU4NTMwMTcwOWNlOCJ9fX0="),
    SHARK("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjA5OTA5NGI5YzQyOWE4Yzg0Njg1MTQwYzk3MTY0ZTU5NGJmMGViMzdmMDExNjQzNjA4YWU5NmIzZjFiNjFmNiJ9fX0=");

    private final String texture;

    public static ItemBuilder toItemBuilder(CustomHead customHead) {
        ItemStack skull = (new ItemStack(Material.PLAYER_HEAD));
        UUID hashAsId = (new UUID(customHead.getTexture().hashCode(), customHead.getTexture().hashCode()));
        ItemStack item = (Bukkit.getUnsafe().modifyItemStack(skull,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + customHead.getTexture() + "\"}]}}}"
        ));
        return new ItemBuilder(item);
    }
}
