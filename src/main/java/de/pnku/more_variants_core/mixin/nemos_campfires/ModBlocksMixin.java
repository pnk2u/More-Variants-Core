package de.pnku.more_variants_core.mixin.nemos_campfires;

import com.nemonotfound.nemoscampfires.block.ModBlocks;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.CampfireType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ModBlocks.class)
public abstract class ModBlocksMixin {
    @Shadow
    private static Block registerBlock(String path, Block campfireBlock) {
        throw new AssertionError();
    }

    @Shadow
    private static BlockBehaviour.Properties createCampfireProperties(MapColor mapColor, int lightLevel) {
        throw new AssertionError();
    }
    
    @Unique
    private static void registerCampfireBlockVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (CampfireType campfireType : CampfireType.values()) {
                Block campfireBlock = registerBlock(MoreVariantHolder.getRegistrationId(campfireType, woodType).getPath(), new CampfireBlock(true, 1, createCampfireProperties(woodType.getMapColor(), campfireType.lightLevel())));
                MoreVariantHolder.setBlock(campfireType, woodType, campfireBlock);
                BlockEntityType.CAMPFIRE.addSupportedBlock(campfireBlock);
            }
        }
    }

    @Inject(method = "registerBlocks", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegistercampfireBlocksAtHead(CallbackInfo ci) {
        registerCampfireBlockVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}