package de.pnku.more_variants_core.mixin.more_crafter_variants;

import de.pnku.mcrv.block.MoreCrafterBlock;
import de.pnku.mcrv.init.McrvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(McrvBlockInit.class)
public abstract class McrvBlockInitMixin {
    @Shadow
    private static void registerCrafterBlock(Block crafterBlock) {}

    @Unique
    private static void registerCrafterBlockVariants(List<WoodType> woodTypes) {
        VariantType crafterType = VariantType.CRAFTER;
        for (WoodType woodType : woodTypes) {
            Block crafterBlock = new MoreCrafterBlock(woodType.getMapColor(), woodType.getName());
            registerCrafterBlock(crafterBlock);
            MoreVariantHolder.setBlock(crafterType, woodType, crafterBlock);
        }
    }

    @Inject(method = "registerCrafterBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterCrafterBlocksAtTail(CallbackInfo ci) {
        registerCrafterBlockVariants(WoodTypeHolder.getWoodTypes());
    }
}
