package me.joshllc.pets.pet;

import com.google.common.collect.Maps;
import lombok.Getter;
import me.joshllc.pets.Pets;
import me.joshllc.pets.files.Configuration;
import me.joshllc.pets.pet.obj.Pet;
import me.joshllc.pets.pet.obj.PetType;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class PetHandler {

    private final Pets pets;

    @Getter private HashMap<UUID, Pet> playerPets;

    public PetHandler(final Pets pets) {
        this.pets = pets;
        this.playerPets = Maps.newHashMap();
        this.initialize();
    }

    private void initialize() {
        File folder = new File(pets.getDataFolder() + File.separator + "userdata");
        if(!folder.exists()) folder.mkdir();
    }

    public Configuration getUserConfig(final UUID uuid) {
        return new Configuration(pets, uuid.toString() + ".yml", new File(pets.getDataFolder() + File.separator + "userdata"));
    }

    public void savePets() {
        playerPets.forEach((uuid, pet) -> savePet(uuid));
        playerPets.clear();
    }

    public Pet getPet(UUID uuid) {
        return playerPets.getOrDefault(uuid, null);
    }

    public void fetchPet(UUID uuid) {
        Configuration config = getUserConfig(uuid);
        if(config.getSection("pet") == null) return;

        String name = config.yaml().getString("pet.name");
        PetType type = PetType.valueOf(config.yaml().getString("pet.type"));
        int hp = config.yaml().getInt("pet.hp");
        int food = config.yaml().getInt("pet.food");

        Pet pet = new Pet(type);
        pet.setName(name);
        pet.setHealth(hp);
        pet.setFood(food);

        playerPets.put(uuid, pet);
    }

    public void savePet(UUID uuid) {
        if(!playerPets.containsKey(uuid)) return;
        Configuration config = getUserConfig(uuid);
        Pet pet = playerPets.get(uuid);
        config.yaml().set("pet.name", pet.getName());
        config.yaml().set("pet.type", pet.getType().name());
        config.yaml().set("pet.hp", pet.getHealth());
        config.yaml().set("pet.food", pet.getFood());
        config.save();
    }
}
