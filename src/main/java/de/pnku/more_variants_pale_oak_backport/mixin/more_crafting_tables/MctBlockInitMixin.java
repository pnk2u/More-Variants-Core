package de.pnku.more_variants_pale_oak_backport.mixin.more_crafting_tables;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import io.github.lieonlion.lolmct.block.MoreCraftingTableBlock;
import io.github.lieonlion.lolmct.init.MctBlockInit;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MctBlockInit.class)
public abstract class MctBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final MoreCraftingTableBlock PALE_OAK_CRAFTING_TABLE = registerPaleOakCraftingTable();

    @Shadow
    private static void registerBlock(String name, Block block) {}

    @Inject(method = "registerBlocks", at = @At("HEAD"), remap = false)
    private static void injectedRegisterBlocksAtHead(CallbackInfo ci) {
        if (PALE_OAK_CRAFTING_TABLE.defaultBlockState().isAir()) {
            throw new IllegalStateException("Failed to register Pale Oak Crafting Table Block");
        }
    }

    @Unique
    private static MoreCraftingTableBlock registerPaleOakCraftingTable() {
        MoreCraftingTableBlock block = new MoreCraftingTableBlock(WOOD_TYPE.getMapColor());
        registerBlock(WOOD_TYPE.getName() + "_crafting_table", block);
        PaleOakVariantHolder.setBlock(PaleOakVariantHolder.CRAFTING_TABLE_FAMILY, WOOD_TYPE, block);
        return block;
    }
}
