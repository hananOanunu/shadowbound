package net.darkfantasy.shadowbound.item;

import net.darkfantasy.shadowbound.ShadowboundMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    // --- Dark Fantasy Materials ---
    public static final Item SHADOW_REMNANT = registerItem("shadow_remnant",
            new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item GRIMSTEEL_INGOT = registerItem("grimsteel_ingot",
            new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item BLOOD_GEM = registerItem("blood_gem",
            new Item(new FabricItemSettings().rarity(Rarity.RARE)));
    public static final Item VOID_ESSENCE = registerItem("void_essence",
            new Item(new FabricItemSettings().rarity(Rarity.EPIC)));

    // --- Dark Fantasy Weapons ---
    public static final Item BLOODTHORN_BLADE = registerItem("bloodthorn_blade",
            new BloodthornBladeItem(ModToolMaterials.BLOODFORGED, 8, -2.8F,
                    new FabricItemSettings().rarity(Rarity.RARE).fireproof()));

    public static final Item SOUL_SCYTHE = registerItem("soul_scythe",
            new SoulScytheItem(ModToolMaterials.GRIMSTEEL, 7, -2.2F,
                    new FabricItemSettings().rarity(Rarity.RARE).fireproof()));

    public static final Item ABYSSAL_DAGGER = registerItem("abyssal_dagger",
            new AbyssalDaggerItem(ModToolMaterials.GRIMSTEEL, 4, -1.2F,
                    new FabricItemSettings().rarity(Rarity.UNCOMMON)));

    public static final Item GRIMSTEEL_CLAYMORE = registerItem("grimsteel_claymore",
            new SwordItem(ModToolMaterials.GRIMSTEEL, 9, -3.0F,
                    new FabricItemSettings().rarity(Rarity.UNCOMMON)));

    // --- Grimsteel Armor Set ---
    public static final Item GRIMSTEEL_HELMET = registerItem("grimsteel_helmet",
            new GrimsteelArmorItem(ModArmorMaterials.GRIMSTEEL, ArmorItem.Type.HELMET,
                    new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item GRIMSTEEL_CHESTPLATE = registerItem("grimsteel_chestplate",
            new GrimsteelArmorItem(ModArmorMaterials.GRIMSTEEL, ArmorItem.Type.CHESTPLATE,
                    new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item GRIMSTEEL_LEGGINGS = registerItem("grimsteel_leggings",
            new GrimsteelArmorItem(ModArmorMaterials.GRIMSTEEL, ArmorItem.Type.LEGGINGS,
                    new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item GRIMSTEEL_BOOTS = registerItem("grimsteel_boots",
            new GrimsteelArmorItem(ModArmorMaterials.GRIMSTEEL, ArmorItem.Type.BOOTS,
                    new FabricItemSettings().rarity(Rarity.UNCOMMON)));

    // --- Bloodforged Armor Set ---
    public static final Item BLOODFORGED_HELMET = registerItem("bloodforged_helmet",
            new BloodforgedArmorItem(ModArmorMaterials.BLOODFORGED, ArmorItem.Type.HELMET,
                    new FabricItemSettings().rarity(Rarity.RARE).fireproof()));
    public static final Item BLOODFORGED_CHESTPLATE = registerItem("bloodforged_chestplate",
            new BloodforgedArmorItem(ModArmorMaterials.BLOODFORGED, ArmorItem.Type.CHESTPLATE,
                    new FabricItemSettings().rarity(Rarity.RARE).fireproof()));
    public static final Item BLOODFORGED_LEGGINGS = registerItem("bloodforged_leggings",
            new BloodforgedArmorItem(ModArmorMaterials.BLOODFORGED, ArmorItem.Type.LEGGINGS,
                    new FabricItemSettings().rarity(Rarity.RARE).fireproof()));
    public static final Item BLOODFORGED_BOOTS = registerItem("bloodforged_boots",
            new BloodforgedArmorItem(ModArmorMaterials.BLOODFORGED, ArmorItem.Type.BOOTS,
                    new FabricItemSettings().rarity(Rarity.RARE).fireproof()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ShadowboundMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ShadowboundMod.LOGGER.info("Registering dark fantasy items for " + ShadowboundMod.MOD_ID);
    }
}
