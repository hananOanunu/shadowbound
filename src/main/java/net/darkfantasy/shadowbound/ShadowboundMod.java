package net.darkfantasy.shadowbound;

import net.darkfantasy.shadowbound.group.ModItemGroups;
import net.darkfantasy.shadowbound.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.recipe.Recipe;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ShadowboundMod implements ModInitializer {
    public static final String MOD_ID = "shadowbound";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Awakening the shadows: Initializing Shadowbound...");

        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();

        // Automatically unlock all Shadowbound recipes in the player's Recipe Book (green book)
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            List<Recipe<?>> modRecipes = server.getRecipeManager().values().stream()
                    .filter(recipe -> recipe.getId().getNamespace().equals(MOD_ID))
                    .toList();
            player.unlockRecipes(modRecipes);
        });

        LOGGER.info("Shadowbound dark relics and armors successfully loaded.");
    }
}
