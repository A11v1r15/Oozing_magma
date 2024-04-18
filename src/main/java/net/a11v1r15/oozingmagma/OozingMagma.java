package net.a11v1r15.oozingmagma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class OozingMagma implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("oozing-magma");
	public static final StatusEffect OOZING_MAGMA = new OozingMagmaStatusEffect
													(StatusEffectCategory.HARMFUL, 702228, random -> 2);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registries.STATUS_EFFECT, new Identifier("oozing-magma", "oozing_magma"), OOZING_MAGMA);

		LOGGER.info("Do not put slimes in the microwave");
	}
}