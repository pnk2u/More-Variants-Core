package de.pnku.more_variants_pale_oak_backport.mixin.more_beehive_variants;

import de.pnku.mbhv.block.MoreBeehiveVariantBlock;
import de.pnku.mbhv.init.MbhvBlockInit;
import de.pnku.more_variants_pale_oak_backport.PaleOakConstants;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBeehiveHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MbhvBlockInit.class)
public abstract class MbhvBlockInitMixin {
    @Unique
    private static final Block PALE_OAK_BEEHIVE = registerPaleOakBeehiveBlock();

    @Invoker("registerBeehiveBlock")
    public static void invokeRegisterBeehiveBlock(MoreBeehiveVariantBlock beehiveBlock) {
        throw new AssertionError();
    }

    @Unique
    private static Block registerPaleOakBeehiveBlock() {
        MoreBeehiveVariantBlock beehiveBlock = new MoreBeehiveVariantBlock(MapColor.QUARTZ, PaleOakConstants.WOOD_TYPE);
        invokeRegisterBeehiveBlock(beehiveBlock);
        PaleOakBeehiveHolder.setBlock(beehiveBlock);
        return beehiveBlock;
    }
}
