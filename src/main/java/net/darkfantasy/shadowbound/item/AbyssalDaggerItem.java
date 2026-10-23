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

public class AbyssalDaggerItem extends SwordItem {
    public AbyssalDaggerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!world.isClient()) {
            boolean isSneaking = attacker.isSneaking();
            int light = world.getLightLevel(attacker.getBlockPos());

            if (isSneaking || light < 7) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 80, 1));
                world.playSound(null, target.getX(), target.getY(), target.getZ(),
                        SoundEvents.ENTITY_ENDERMAN_HURT, SoundCategory.PLAYERS, 0.7F, 1.6F);
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (TooltipHelper.isShiftDown()) {
            tooltip.add(Text.translatable("tooltip.shadowbound.abyssal_dagger.line1"));
            tooltip.add(Text.translatable("tooltip.shadowbound.abyssal_dagger.line2"));
            tooltip.add(Text.translatable("tooltip.shadowbound.abyssal_dagger.line3"));
        } else {
            tooltip.add(Text.translatable("tooltip.shadowbound.abyssal_dagger.short"));
            tooltip.add(Text.translatable("tooltip.shadowbound.press_shift"));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
