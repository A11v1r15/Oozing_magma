package net.a11v1r15.oozingmagma;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class OozingMagmaPotions {
 
    public static final Potion OOZING_MAGMA_POTION = 
        Registry.register(Registries.POTION, new Identifier("oozing-magma", "oozing_magma_potion"),
            new Potion(new StatusEffectInstance(OozingMagma."OOZING_MAGMA", 3600, 0)));
 
    public static void registerPotions(){
 
    }
 
    public static void registerPotionRecipes(){
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.MAGMA_BLOCK, OozingMagmaPotions.OOZING_MAGMA_POTION);
    }
}