package de.pnku.more_variants_core.mixin.more_fletching_tables;

import de.pnku.mft.block.MoreFletchingTablesBlock;
import de.pnku.mft.init.MftBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MftBlockInit.class)
public abstract class MftBlockInitMixin {
    @Shadow
    private static void registerBlock(MoreFletchingTablesBlock fletchingTable) {}

    @Unique
    private static void registerFletchingTableBlockVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            MoreFletchingTablesBlock fletchingTable = new MoreFletchingTablesBlock(woodType.getMapColor(), woodType.getName());
            registerBlock(fletchingTable);
            MoreVariantHolder.setBlock(VariantType.FLETCHING_TABLE, woodType, fletchingTable);
        }
    }

    @Inject(method = "registerBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBlocksAtTail(CallbackInfo ci) {
        registerFletchingTableBlockVariants(WoodTypeHolder.getWoodTypes());
    }
}
