package de.pnku.more_variants_pale_oak_backport.mixin.more_beehive_variants;

import de.pnku.mbhv.block.MoreBeehiveVariantBlock;
import de.pnku.mbhv.init.MbhvBlockInit;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBeehiveHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MbhvBlockInit.class)
public abstract class MbhvBlockInitMixin {
    @Unique
    private static final Block PALE_OAK_BEEHIVE = registerPaleOakBeehiveBlock();

    @Invoker("registerBeehiveBlock")
    public static void invokeRegisterBeehiveBlock(MoreBeehiveVariantBlock beehiveBlock) {
        throw new AssertionError();
    }

    @Inject(method = "registerBeehiveBlocks", at = @At("HEAD"), remap = false)
    private static void injectedRegisterBeehiveBlocksAtHead(CallbackInfo ci) {
        if (PALE_OAK_BEEHIVE.defaultBlockState().isAir()) {
            throw new IllegalStateException("Failed to register Pale Oak Beehive Block");
        }
    }

    @Unique
    private static Block registerPaleOakBeehiveBlock() {
        String woodType = "pale_oak";
        MoreBeehiveVariantBlock beehiveBlock = new MoreBeehiveVariantBlock(MapColor.QUARTZ, woodType);
        invokeRegisterBeehiveBlock(beehiveBlock);
        PaleOakBeehiveHolder.PALE_OAK_BEEHIVE = beehiveBlock;
        return beehiveBlock;
    }
}
