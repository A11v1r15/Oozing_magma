package net.a11v1r15.oozingmagma.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.OozingStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;

@Mixin(OozingStatusEffect.class)
public class OozingStatusEffectMixin
extends StatusEffect {
  protected OozingStatusEffectMixin(StatusEffectCategory statusEffectCategory, int color) {
		super(statusEffectCategory, color);
	}

	@ModifyVariable(
        method = "spawnSlime(Lnet/minecraft/world/World;DDD)V",
        at = @At(value = "STORE"), ordinal = 0
        )
    private SlimeEntity oozingMagma$changeSlimeToMagmaCube(SlimeEntity slime, World world) {
		if(world.getDimension().ultrawarm())
			slime = EntityType.MAGMA_CUBE.create(world);
        return slime;
    }
}