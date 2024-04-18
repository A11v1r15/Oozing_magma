package net.a11v1r15.oozingmagma;

import java.util.function.ToIntFunction;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.OozingStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class OozingMagmaStatusEffect extends OozingStatusEffect {

    protected OozingMagmaStatusEffect(StatusEffectCategory category, int color, ToIntFunction<Random> slimeCountFunction) {
        super(category, color, slimeCountFunction);
    }

    @Override
    protected void spawnSlime(World world, double x, double y, double z) {
        MagmaCubeEntity magmaCubeEntity = EntityType.MAGMA_CUBE.create(world);
        if (magmaCubeEntity != null) {
            magmaCubeEntity.setSize(2, true);
            magmaCubeEntity.refreshPositionAndAngles(x, y, z, world.getRandom().nextFloat() * 360.0F, 0.0F);
            world.spawnEntity(magmaCubeEntity);
        }
    }
}
