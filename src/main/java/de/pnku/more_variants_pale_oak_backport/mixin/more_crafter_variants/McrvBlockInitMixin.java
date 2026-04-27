package de.pnku.more_variants_pale_oak_backport.mixin.more_crafter_variants;

import de.pnku.mcrv.block.MoreCrafterBlock;
import de.pnku.mcrv.init.McrvBlockInit;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(McrvBlockInit.class)
public abstract class McrvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_CRAFTER = registerPaleOakCrafterBlock();

    @Shadow
    private static void registerCrafterBlock(Block crafterBlock) {}

    @Unique
    private static Block registerPaleOakCrafterBlock() {
        Block crafterBlock = new MoreCrafterBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerCrafterBlock(crafterBlock);
        PaleOakVariantHolder.setBlock(VariantType.CRAFTER, WOOD_TYPE, crafterBlock);
        return crafterBlock;
    }
}
