package de.pnku.more_variants_pale_oak_backport.mixin.mstv.more_rail_variants;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.RailType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.mstv_mrailv.init.MrailvBlockInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import static de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.RailType.*;

@Mixin(MrailvBlockInit.class)
public abstract class MrailvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_RAIL = registerPaleOakRailBlock(RAIL);
    @Unique
    private static final Block PALE_OAK_DETECTOR_RAIL = registerPaleOakRailBlock(DETECTOR_RAIL);
    @Unique
    private static final Block PALE_OAK_POWERED_RAIL = registerPaleOakRailBlock(POWERED_RAIL);
    @Unique
    private static final Block PALE_OAK_ACTIVATOR_RAIL = registerPaleOakRailBlock(ACTIVATOR_RAIL);
    @Unique
    private static final Item PALE_OAK_RAIL_ITEM = registerPaleOakRailItem(RAIL, PALE_OAK_RAIL);
    @Unique
    private static final Item PALE_OAK_DETECTOR_RAIL_ITEM = registerPaleOakRailItem(DETECTOR_RAIL, PALE_OAK_DETECTOR_RAIL);
    @Unique
    private static final Item PALE_OAK_POWERED_RAIL_ITEM = registerPaleOakRailItem(POWERED_RAIL, PALE_OAK_POWERED_RAIL);
    @Unique
    private static final Item PALE_OAK_ACTIVATOR_RAIL_ITEM = registerPaleOakRailItem(ACTIVATOR_RAIL, PALE_OAK_ACTIVATOR_RAIL);

    @Shadow
    public static Block registerRailBlock(String woodType, Block railBlock) {
        throw new AssertionError();
    }

    @Shadow
    public static Block registerRailBlock(String woodType, Block railBlock, String railType) {
        throw new AssertionError();
    }

    @Shadow
    public static Item registerRailItem(String woodType, Item railItem, String railType) {
        throw new AssertionError();
    }

    @Unique
    private static Block registerPaleOakRailBlock(RailType railType) {
        Block inputRailBlock = createInputRailBlock(railType);
        Block railBlock = railType == RAIL
                ? registerRailBlock(WOOD_TYPE.getName(), inputRailBlock)
                : registerRailBlock(WOOD_TYPE.getName(), inputRailBlock, railType.registrationType());
        PaleOakVariantHolder.setBlock(railType, WOOD_TYPE, railBlock);
        return railBlock;
    }

    @Unique
    private static Item registerPaleOakRailItem(RailType railType, Block railBlock) {
        Item railItem = registerRailItem(WOOD_TYPE.getName(), new BlockItem(railBlock, new Item.Properties()), railType.registrationType());
        PaleOakVariantHolder.setItem(railType, WOOD_TYPE, railItem);
        return railItem;
    }

    @Unique
    private static Block createInputRailBlock(RailType railType) {
        if (railType == RAIL) {
            return new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL));
        }
        if (railType == DETECTOR_RAIL) {
            return new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL));
        }
        Block vanillaRail = railType == ACTIVATOR_RAIL ? Blocks.ACTIVATOR_RAIL : Blocks.POWERED_RAIL;
        return new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(vanillaRail));
    }
}
