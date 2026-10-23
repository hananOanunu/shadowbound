package net.darkfantasy.shadowbound.item;

import net.darkfantasy.shadowbound.ShadowboundMod;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    GRIMSTEEL("grimsteel", 37, 3, 8, 6, 3, 18,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.ofItems(ModItems.GRIMSTEEL_INGOT)),
    BLOODFORGED("bloodforged", 42, 4, 9, 7, 4, 22,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.5F, 0.15F, () -> Ingredient.ofItems(ModItems.BLOOD_GEM));

    private final String name;
    private final int durabilityMultiplier;
    private final int bootsProtection;
    private final int chestplateProtection;
    private final int leggingsProtection;
    private final int helmetProtection;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    ModArmorMaterials(String name, int durabilityMultiplier,
                      int bootsProtection, int chestplateProtection, int leggingsProtection, int helmetProtection,
                      int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance,
                      Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.bootsProtection = bootsProtection;
        this.chestplateProtection = chestplateProtection;
        this.leggingsProtection = leggingsProtection;
        this.helmetProtection = helmetProtection;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS -> 13 * this.durabilityMultiplier;
            case LEGGINGS -> 15 * this.durabilityMultiplier;
            case CHESTPLATE -> 16 * this.durabilityMultiplier;
            case HELMET -> 11 * this.durabilityMultiplier;
        };
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS -> this.bootsProtection;
            case LEGGINGS -> this.leggingsProtection;
            case CHESTPLATE -> this.chestplateProtection;
            case HELMET -> this.helmetProtection;
        };
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return ShadowboundMod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
