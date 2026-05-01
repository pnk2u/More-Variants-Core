package de.pnku.more_variants_core.mixin.more_smithing_tables;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import de.pnku.mst.block.MoreSmithingTableBlock;
import de.pnku.mst.init.MstBlockInit;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MstBlockInit.class)
public abstract class MstBlockInitMixin {
    @Shadow
    private static void registerBlock(Block block) {}

    @Unique
    private static void registerSmithingTableBlockVariants(List<WoodType> woodTypes) {
        VariantType smithingTableType = VariantType.SMITHING_TABLE;
        for (WoodType woodType : woodTypes) {
            Block smithingTableBlock = new MoreSmithingTableBlock(woodType.getMapColor(), woodType.getName());
            registerBlock(smithingTableBlock);
            MoreVariantHolder.setBlock(smithingTableType, woodType, smithingTableBlock);
        }
    }

    @Inject(method = "registerBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBlocksAtTail(CallbackInfo ci) {
        registerSmithingTableBlockVariants(WoodTypeHolder.getWoodTypes());
    }
}
