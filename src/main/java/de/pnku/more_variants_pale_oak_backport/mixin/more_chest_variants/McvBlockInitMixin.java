package de.pnku.more_variants_pale_oak_backport.mixin.more_chest_variants;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakChestHolder;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakChestHolder.ChestType;
import io.github.lieonlion.mcv.block.MoreChestBlock;
import io.github.lieonlion.mcv.block.MoreTrappedChestBlock;
import io.github.lieonlion.mcv.init.McvBlockInit;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(McvBlockInit.class)
public abstract class McvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final MoreChestBlock PALE_OAK_CHEST = registerPaleOakChest();
    @Unique
    private static final MoreTrappedChestBlock PALE_OAK_TRAPPED_CHEST = registerPaleOakTrappedChest();

    @Invoker("registerBlock")
    @SuppressWarnings("SameParameterValue")
    private static void invokeRegisterBlock(MoreChestBlock chestBlock, MoreTrappedChestBlock trappedChestBlock) {
        throw new AssertionError();
    }

    @Inject(method = "registerBlocks", at = @At("HEAD"), remap = false)
    private static void injectedRegisterBlocksAtHead(CallbackInfo ci) {
        invokeRegisterBlock(PALE_OAK_CHEST, PALE_OAK_TRAPPED_CHEST);
    }

    @Unique
    private static MoreChestBlock registerPaleOakChest() {
        MoreChestBlock chestBlock = new MoreChestBlock(MapColor.QUARTZ, WOOD_TYPE.getName());
        PaleOakChestHolder.setBlock(WOOD_TYPE, ChestType.CHEST, chestBlock);
        return chestBlock;
    }

    @Unique
    private static MoreTrappedChestBlock registerPaleOakTrappedChest() {
        MoreTrappedChestBlock trappedChestBlock = new MoreTrappedChestBlock(MapColor.QUARTZ, WOOD_TYPE.getName());
        PaleOakChestHolder.setBlock(WOOD_TYPE, ChestType.TRAPPED_CHEST, trappedChestBlock);
        return trappedChestBlock;
    }
}
