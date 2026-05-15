package de.pnku.more_variants_core.client.mixin.nemos_campfires;

import com.nemonotfound.nemoscampfires.NemosCampfiresClient;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.CampfireType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(NemosCampfiresClient.class)
public abstract class NemosCampfiresClientMixin {
    @Unique
    private static void registerCampfireBlockVariantRenderTypes(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (MoreVariantHolder.CampfireType campfireType : CampfireType.values()) {
                Block campfireBlock = MoreVariantHolder.getBlock(campfireType, woodType);
                BlockRenderLayerMap.INSTANCE.putBlock(campfireBlock, RenderType.cutout());
            }
        }
    }

    @Inject(method = "onInitializeClient", at = @At("HEAD"), remap = false)
    private void injectedOnInitializeClientAtHead(CallbackInfo ci) {
        registerCampfireBlockVariantRenderTypes(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
