package de.pnku.more_variants_core.mixin.more_nemos_woodcutter_variants;

import de.pnku.more_nemos_woodcutter_variants.block.MoreWoodcutterBlock;
import de.pnku.more_nemos_woodcutter_variants.block.MoreWoodcutterBlocks;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(MoreWoodcutterBlocks.class)
public abstract class MoreWoodcutterBlocksMixin {
    @Shadow
    @Mutable
    @Final
    public static List<Block> more_woodcutter_blocks;

    @Unique
    private static List<Block> registerWoodcutterBlockVariants(List<MoreVariantWoodType> woodTypes) {
        List<Block> woodcutterBlocks = new ArrayList<>();
        MoreVariantHolder.MoreVariantType woodcutterType = MoreVariantHolder.MoreVariantType.WOODCUTTER;
        for (MoreVariantWoodType woodType : woodTypes) {
            Block woodcutterBlock = new MoreWoodcutterBlock(woodType.mapColor(), woodType.getName());
            MoreVariantHolder.setBlock(woodcutterType, woodType, woodcutterBlock);
            woodcutterBlocks.add(woodcutterBlock);
        }
        return woodcutterBlocks;
    }

    @Inject(method = "registerBlocks", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterBlocksAtHead(CallbackInfo ci) {
        more_woodcutter_blocks = new ArrayList<>(more_woodcutter_blocks);
        more_woodcutter_blocks.addAll(registerWoodcutterBlockVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes()));
    }
}
