package de.pnku.more_variants_pale_oak_backport.mixin.more_barrel_variants;

import de.pnku.more_barrel_variants.block.MoreBarrelBlock;
import de.pnku.more_barrel_variants.init.MoreBarrelBlocks;
import de.pnku.more_variants_pale_oak_backport.PaleOakConstants;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBarrelHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;


@Mixin(MoreBarrelBlocks.class)
public abstract class MoreBarrelBlocksMixin {
    @Shadow
    @Mutable
    @Final
    public static List<Block> more_barrels;
    @Unique
    private static final Block PALE_OAK_BARREL = registerPaleOakBarrelBlock();

    @Inject(method = "registerBlocks", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterBlocksAtHead(CallbackInfo ci) {
        more_barrels = new ArrayList<>(more_barrels);
        if (!more_barrels.contains(PALE_OAK_BARREL)) {
            more_barrels.add(PALE_OAK_BARREL);
        }
    }

    @Unique
    private static Block registerPaleOakBarrelBlock() {
        Block barrelBlock = new MoreBarrelBlock(MapColor.QUARTZ, PaleOakConstants.WOOD_TYPE);
        PaleOakBarrelHolder.setBlock(barrelBlock);
        return barrelBlock;
    }
}
