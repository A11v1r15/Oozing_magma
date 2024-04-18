package net.a11v1r15.oozingmagma;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

import com.google.common.annotations.VisibleForTesting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class OozingMagmaStatusEffect extends StatusEffect {
	private final ToIntFunction<Random> magmaCubeCountFunction;

	protected OozingMagmaStatusEffect(StatusEffectCategory category, int color, ToIntFunction<Random> slimeCountFunction) {
		super(category, color, new ItemStackParticleEffect(ParticleTypes.ITEM, Items.MAGMA_CREAM.getDefaultStack()));
		this.magmaCubeCountFunction = slimeCountFunction;
	}

	@VisibleForTesting
	protected static int getSlimesToSpawn(int maxEntityCramming, int nearbySlimes, int potentialSlimes) {
		return MathHelper.clamp(0, maxEntityCramming - nearbySlimes, potentialSlimes);
	}

	@Override
	public void onEntityRemoval(LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
		if (reason == Entity.RemovalReason.KILLED) {
			int count = this.magmaCubeCountFunction.applyAsInt(entity.getRandom());
			World world = entity.getWorld();
			int limit = world.getGameRules().getInt(GameRules.MAX_ENTITY_CRAMMING);
			List<MagmaCubeEntity> list = new ArrayList<MagmaCubeEntity>();
			world.collectEntitiesByType((TypeFilter<Entity, MagmaCubeEntity>)(EntityType.MAGMA_CUBE), entity.getBoundingBox().expand(2.0), entityx -> entityx != entity, list, limit);
			int finalCount = getSlimesToSpawn(limit, list.size(), count);

			for (int i = 0; i < finalCount; i++) {
				this.spawnSlime(entity.getWorld(), entity.getX(), entity.getY() + 0.5, entity.getZ());
			}
		}
	}

    private void spawnSlime(World world, double x, double y, double z) {
        MagmaCubeEntity magmaCubeEntity = EntityType.MAGMA_CUBE.create(world);
        if (magmaCubeEntity != null) {
            magmaCubeEntity.setSize(2, true);
            magmaCubeEntity.refreshPositionAndAngles(x, y, z, world.getRandom().nextFloat() * 360.0F, 0.0F);
            world.spawnEntity(magmaCubeEntity);
        }
    }
}