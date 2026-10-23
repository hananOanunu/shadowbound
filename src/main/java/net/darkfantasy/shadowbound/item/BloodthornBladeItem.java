package net.darkfantasy.shadowbound.item;

import net.darkfantasy.shadowbound.util.TooltipHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BloodthornBladeItem extends SwordItem {
    public BloodthornBladeItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!world.isClient()) {
            attacker.heal(2.5F);

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 70, 1));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 50, 1));

            world.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                    SoundEvents.ENTITY_WITHER_HURT, SoundCategory.PLAYERS, 0.6F, 1.4F);
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (TooltipHelper.isShiftDown()) {
            tooltip.add(Text.translatable("tooltip.shadowbound.bloodthorn_blade.line1"));
            tooltip.add(Text.translatable("tooltip.shadowbound.bloodthorn_blade.line2"));
            tooltip.add(Text.translatable("tooltip.shadowbound.bloodthorn_blade.line3"));
        } else {
            tooltip.add(Text.translatable("tooltip.shadowbound.bloodthorn_blade.short"));
            tooltip.add(Text.translatable("tooltip.shadowbound.press_shift"));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
