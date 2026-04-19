package de.pnku.more_variants_pale_oak_backport.mixin.more_crafting_tables;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakCraftingTableHolder;
import de.pnku.more_variants_pale_oak_backport.PaleOakConstants;
import io.github.lieonlion.lolmct.block.MoreCraftingTableBlock;
import io.github.lieonlion.lolmct.init.MctBlockInit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MctBlockInit.class)
public abstract class MctBlockInitMixin {
    @Unique
    private static final MoreCraftingTableBlock PALE_OAK_CRAFTING_TABLE = registerPaleOakCraftingTable();

    @Invoker("registerBlock")
    private static void invokeRegisterBlock(String name, Block block) {
        throw new AssertionError();
    }

    @Inject(method = "registerBlocks", at = @At("HEAD"), remap = false)
    private static void injectedRegisterBlocksAtHead(CallbackInfo ci) {
        if (PALE_OAK_CRAFTING_TABLE.defaultBlockState().isAir()) {
            throw new IllegalStateException("Failed to register Pale Oak Crafting Table Block");
        }
    }

    @Unique
    private static MoreCraftingTableBlock registerPaleOakCraftingTable() {
        MoreCraftingTableBlock block = new MoreCraftingTableBlock(MapColor.QUARTZ);
        invokeRegisterBlock(PaleOakConstants.WOOD_TYPE + "_crafting_table", block);
        PaleOakCraftingTableHolder.setBlock(block);
        return block;
    }
}
