package de.pnku.more_variants_core.mixin.more_cartography_tables;

import de.pnku.mcgt.block.MoreCartographyTablesBlock;
import de.pnku.mcgt.init.McgtBlockInit;
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

@Mixin(McgtBlockInit.class)
public abstract class McgtBlockInitMixin {
    @Shadow
    private static void registerBlock(MoreCartographyTablesBlock cartographyTable) {}

    @Unique
    private static void registerCartographyTableBlockVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            MoreCartographyTablesBlock cartographyTable = new MoreCartographyTablesBlock(woodType.getMapColor(), woodType.getName());
            registerBlock(cartographyTable);
            MoreVariantHolder.setBlock(VariantType.CARTOGRAPHY_TABLE, woodType, cartographyTable);
        }
    }

    @Inject(method = "registerBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterCartographyTableBlocksAtTail(CallbackInfo ci) {
        registerCartographyTableBlockVariants(WoodTypeHolder.getWoodTypes());
    }
}
