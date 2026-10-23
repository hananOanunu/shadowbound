package net.darkfantasy.shadowbound.item;

import net.darkfantasy.shadowbound.util.TooltipHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BloodforgedArmorItem extends ArmorItem {
    public BloodforgedArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            if (player.getEquippedStack(EquipmentSlot.CHEST).getItem() == this && hasFullBloodforgedSet(player)) {
                // Immunity / cure for Wither
                if (player.hasStatusEffect(StatusEffects.WITHER)) {
                    player.removeStatusEffect(StatusEffects.WITHER);
                }

                // Blood Frenzy: Activates when under 50% health (<= 10 hearts)
                if (player.getHealth() <= 10.0F) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 40, 1, false, false, true));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 40, 0, false, false, true));
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private boolean hasFullBloodforgedSet(PlayerEntity player) {
        ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);

        return !helmet.isEmpty() && helmet.getItem() instanceof BloodforgedArmorItem &&
               !chest.isEmpty() && chest.getItem() instanceof BloodforgedArmorItem &&
               !legs.isEmpty() && legs.getItem() instanceof BloodforgedArmorItem &&
               !boots.isEmpty() && boots.getItem() instanceof BloodforgedArmorItem;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (TooltipHelper.isShiftDown()) {
            tooltip.add(Text.translatable("tooltip.shadowbound.bloodforged_armor.line1"));
            tooltip.add(Text.translatable("tooltip.shadowbound.bloodforged_armor.line2"));
        } else {
            tooltip.add(Text.translatable("tooltip.shadowbound.bloodforged_armor.short"));
            tooltip.add(Text.translatable("tooltip.shadowbound.press_shift"));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
