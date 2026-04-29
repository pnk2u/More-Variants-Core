package de.pnku.more_variants_core.mixin.more_crafting_tables;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import io.github.lieonlion.lolmct.block.MoreCraftingTableBlock;
import io.github.lieonlion.lolmct.init.MctBlockInit;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MctBlockInit.class)
public abstract class MctBlockInitMixin {
    @Shadow
    private static void registerBlock(String name, Block block) {}


    @Unique
    private static void registerCraftingTableVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            MoreCraftingTableBlock block = new MoreCraftingTableBlock(woodType.getMapColor());
            registerBlock(woodType.getName() + "_" + VariantType.CRAFTING_TABLE.registrationType(), block);
            MoreVariantHolder.setBlock(VariantType.CRAFTING_TABLE, woodType, block);
        }
    }

    @Inject(method = "registerBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBlocksAtHead(CallbackInfo ci) {
        registerCraftingTableVariants(WoodTypeHolder.getWoodTypes());
    }
}
