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

public class SoulScytheItem extends SwordItem {
    public SoulScytheItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!world.isClient()) {
            attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 90, 1));
            attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 120, 0));

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 70, 0));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 0));

            world.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                    SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.PLAYERS, 0.9F, 1.1F);
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (TooltipHelper.isShiftDown()) {
            tooltip.add(Text.translatable("tooltip.shadowbound.soul_scythe.line1"));
            tooltip.add(Text.translatable("tooltip.shadowbound.soul_scythe.line2"));
            tooltip.add(Text.translatable("tooltip.shadowbound.soul_scythe.line3"));
        } else {
            tooltip.add(Text.translatable("tooltip.shadowbound.soul_scythe.short"));
            tooltip.add(Text.translatable("tooltip.shadowbound.press_shift"));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
