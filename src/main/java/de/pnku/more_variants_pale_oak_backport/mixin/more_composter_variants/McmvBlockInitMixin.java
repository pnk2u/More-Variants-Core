package de.pnku.more_variants_pale_oak_backport.mixin.more_composter_variants;

import de.pnku.mcmv.block.MoreComposterBlock;
import de.pnku.mcmv.init.McmvBlockInit;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(McmvBlockInit.class)
public abstract class McmvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_COMPOSTER = registerPaleOakComposter();

    @Shadow
    private static void registerBlock(MoreComposterBlock composter) {}

    @Unique
    private static Block registerPaleOakComposter() {
        MoreComposterBlock composter = new MoreComposterBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBlock(composter);
        PaleOakVariantHolder.setBlock(VariantType.COMPOSTER, WOOD_TYPE, composter);
        return composter;
    }
}
