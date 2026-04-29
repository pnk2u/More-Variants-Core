package de.pnku.more_variants_core.mixin.more_composter_variants;

import de.pnku.mcmv.block.MoreComposterBlock;
import de.pnku.mcmv.init.McmvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(McmvBlockInit.class)
public abstract class McmvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_COMPOSTER = registerPaleOakComposter();

    @Shadow
    private static void registerBlock(MoreComposterBlock composter) {}

    @Unique
    private static Block registerPaleOakComposter() {
        MoreComposterBlock composter = new MoreComposterBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBlock(composter);
        MoreVariantHolder.setBlock(VariantType.COMPOSTER, WOOD_TYPE, composter);
        return composter;
    }
}
