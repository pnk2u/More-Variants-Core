package de.pnku.more_variants_core.mixin.more_beehive_variants;

import de.pnku.mbhv.block.MoreBeehiveVariantBlock;
import de.pnku.mbhv.init.MbhvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MbhvBlockInit.class)
public abstract class MbhvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_BEEHIVE = registerPaleOakBeehiveBlock();

    @Shadow
    private static void registerBeehiveBlock(MoreBeehiveVariantBlock beehiveBlock) {}

    @Unique
    private static Block registerPaleOakBeehiveBlock() {
        MoreBeehiveVariantBlock beehiveBlock = new MoreBeehiveVariantBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBeehiveBlock(beehiveBlock);
        MoreVariantHolder.setBlock(VariantType.BEEHIVE, WOOD_TYPE, beehiveBlock);
        return beehiveBlock;
    }
}
