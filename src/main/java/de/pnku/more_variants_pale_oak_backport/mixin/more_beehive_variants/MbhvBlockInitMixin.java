package de.pnku.more_variants_pale_oak_backport.mixin.more_beehive_variants;

import de.pnku.mbhv.block.MoreBeehiveVariantBlock;
import de.pnku.mbhv.init.MbhvBlockInit;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MbhvBlockInit.class)
public abstract class MbhvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_BEEHIVE = registerPaleOakBeehiveBlock();

    @Shadow
    private static void registerBeehiveBlock(MoreBeehiveVariantBlock beehiveBlock) {}

    @Unique
    private static Block registerPaleOakBeehiveBlock() {
        MoreBeehiveVariantBlock beehiveBlock = new MoreBeehiveVariantBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBeehiveBlock(beehiveBlock);
        PaleOakVariantHolder.setBlock(VariantType.BEEHIVE, WOOD_TYPE, beehiveBlock);
        return beehiveBlock;
    }
}
