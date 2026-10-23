package net.darkfantasy.shadowbound.group;

import net.darkfantasy.shadowbound.ShadowboundMod;
import net.darkfantasy.shadowbound.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> SHADOWBOUND_GROUP_KEY = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            new Identifier(ShadowboundMod.MOD_ID, "shadowbound_tab")
    );

    public static final ItemGroup SHADOWBOUND_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.BLOODTHORN_BLADE))
            .displayName(Text.translatable("itemgroup.shadowbound.tab"))
            .entries((displayContext, entries) -> {
                // Materials
                entries.add(ModItems.SHADOW_REMNANT);
                entries.add(ModItems.GRIMSTEEL_INGOT);
                entries.add(ModItems.BLOOD_GEM);
                entries.add(ModItems.VOID_ESSENCE);

                // Weapons
                entries.add(ModItems.BLOODTHORN_BLADE);
                entries.add(ModItems.SOUL_SCYTHE);
                entries.add(ModItems.ABYSSAL_DAGGER);
                entries.add(ModItems.GRIMSTEEL_CLAYMORE);

                // Grimsteel Armor
                entries.add(ModItems.GRIMSTEEL_HELMET);
                entries.add(ModItems.GRIMSTEEL_CHESTPLATE);
                entries.add(ModItems.GRIMSTEEL_LEGGINGS);
                entries.add(ModItems.GRIMSTEEL_BOOTS);

                // Bloodforged Armor
                entries.add(ModItems.BLOODFORGED_HELMET);
                entries.add(ModItems.BLOODFORGED_CHESTPLATE);
                entries.add(ModItems.BLOODFORGED_LEGGINGS);
                entries.add(ModItems.BLOODFORGED_BOOTS);
            })
            .build();

    public static void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP, SHADOWBOUND_GROUP_KEY, SHADOWBOUND_GROUP);
    }
}
