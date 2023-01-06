package me.joshllc.pets.pet.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pet {

    private String name;
    private final PetType type;
    private int health, food;

    public Pet(final PetType type) {
        this.name = type.getName();
        this.type = type;
        this.health = type.getHp();
        this.food = type.getFood();
    }
}
