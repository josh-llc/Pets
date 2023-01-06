package me.joshllc.pets.pet.obj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.joshllc.pets.pet.obj.food.Food;
import me.joshllc.pets.util.items.CustomHead;

@AllArgsConstructor
@Getter
public enum PetType {

    CHICK("Chick", CustomHead.CHICK, 10, 20, Food.SEED),
    AXOLOTL("Axolotl", CustomHead.AXOLOTL, 20, 20, Food.FISH_FLAKES),
    RACOON("Racoon", CustomHead.RACOON, 50, 20, Food.TRASH),
    OTTER("Otter", CustomHead.OTTER, 50, 20, Food.OYSTER),
    PENGUIN("Penguin", CustomHead.PENGUIN, 50, 20, Food.SALMON),
    SHARK("Shark", CustomHead.SHARK, 50, 20, Food.MEAT);

    private String name;
    private CustomHead head;
    private int hp, food;
    private Food foodType;
}
