package net.a11v1r15.oozingmagma.mixin;

import net.minecraft.world.World;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.OozingStatusEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.math.Box;

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
    private SlimeEntity oozingMagma$changeSlimeToMagmaCube(World world, SlimeEntity slime) {
		if(world.getDimension().ultrawarm())
			slime = EntityType.MAGMA_CUBE.create(world);
        return slime;
    }
}