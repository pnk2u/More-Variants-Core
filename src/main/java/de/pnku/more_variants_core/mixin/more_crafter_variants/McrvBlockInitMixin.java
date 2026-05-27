package de.pnku.more_variants_core.mixin.more_crafter_variants;

/*
import de.pnku.mcrv.block.MoreCrafterBlock;
import de.pnku.mcrv.init.McrvBlockInit;
*/
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(targets={"de.pnku.mcrv.init.McrvBlockInit.class"})
public abstract class McrvBlockInitMixin {
    /*
    @Shadow
    private static void registerCrafterBlock(Block crafterBlock) {}

    @Unique
    private static void registerCrafterBlockVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantHolder.MoreVariantType crafterType = MoreVariantHolder.MoreVariantType.CRAFTER;
        for (MoreVariantWoodType woodType : woodTypes) {
            Block crafterBlock = new MoreCrafterBlock(woodType.mapColor(), woodType.getName());
            registerCrafterBlock(crafterBlock);
            MoreVariantHolder.setBlock(crafterType, woodType, crafterBlock);
        }
    }

    @Inject(method = "registerCrafterBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterCrafterBlocksAtTail(CallbackInfo ci) {
        registerCrafterBlockVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
     */
}
