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

public class GrimsteelArmorItem extends ArmorItem {
    public GrimsteelArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            // Check only once per tick on chestplate slot to avoid duplicate effect processing
            if (player.getEquippedStack(EquipmentSlot.CHEST).getItem() == this && hasFullGrimsteelSet(player)) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 40, 0, false, false, true));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 220, 0, false, false, false));
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private boolean hasFullGrimsteelSet(PlayerEntity player) {
        ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);

        return !helmet.isEmpty() && helmet.getItem() instanceof GrimsteelArmorItem &&
               !chest.isEmpty() && chest.getItem() instanceof GrimsteelArmorItem &&
               !legs.isEmpty() && legs.getItem() instanceof GrimsteelArmorItem &&
               !boots.isEmpty() && boots.getItem() instanceof GrimsteelArmorItem;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (TooltipHelper.isShiftDown()) {
            tooltip.add(Text.translatable("tooltip.shadowbound.grimsteel_armor.line1"));
            tooltip.add(Text.translatable("tooltip.shadowbound.grimsteel_armor.line2"));
        } else {
            tooltip.add(Text.translatable("tooltip.shadowbound.grimsteel_armor.short"));
            tooltip.add(Text.translatable("tooltip.shadowbound.press_shift"));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
