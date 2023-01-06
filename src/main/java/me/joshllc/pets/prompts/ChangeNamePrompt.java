package me.joshllc.pets.prompts;

import me.joshllc.pets.Pets;
import me.joshllc.pets.util.text.C;
import org.bukkit.ChatColor;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;

public class ChangeNamePrompt extends StringPrompt {

    private final Pets pets;
    private Player player;
    private String oldName;

    public ChangeNamePrompt(final Pets pets, Player player) {
        this.pets = pets;
        this.player = player;
        this.oldName = pets.getPetHandler().getPet(player.getUniqueId()).getName();
    }


    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return C.colorize(pets.getMessages().get("pets.rename-confirmation", null).replaceAll("%pet_name%", pets.getPetHandler().getPet(player.getUniqueId()).getName()));
    }

    @Override
    public Prompt acceptInput(ConversationContext conversationContext, String s) {
        Conversable who = conversationContext.getForWhom();
        pets.getPetHandler().getPet(player.getUniqueId()).setName(ChatColor.stripColor(s));
        who.sendRawMessage(pets.getMessages().get("pets.rename-success", "").replaceAll("%old_name%", oldName).replaceAll("%new_name%", s));
        return END_OF_CONVERSATION;
    }

}