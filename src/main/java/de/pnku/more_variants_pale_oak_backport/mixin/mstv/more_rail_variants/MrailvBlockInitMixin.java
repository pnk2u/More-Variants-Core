package de.pnku.more_variants_pale_oak_backport.mixin.mstv.more_rail_variants;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakRailHolder;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakRailHolder.RailType;
import de.pnku.mstv_mrailv.init.MrailvBlockInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

import static de.pnku.more_variants_pale_oak_backport.PaleOakConstants.WOOD_TYPE;
import static de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakRailHolder.RailType.*;

@Mixin(MrailvBlockInit.class)
public abstract class MrailvBlockInitMixin {
    @Unique
    private static final Block PALE_OAK_RAIL = registerPaleOakRailBlock();
    @Unique
    private static final Block PALE_OAK_DETECTOR_RAIL = registerPaleOakRailBlock(DETECTOR_RAIL);
    @Unique
    private static final Block PALE_OAK_POWERED_RAIL = registerPaleOakRailBlock(POWERED_RAIL);
    @Unique
    private static final Block PALE_OAK_ACTIVATOR_RAIL = registerPaleOakRailBlock(ACTIVATOR_RAIL);
    @Unique
    private static final Item PALE_OAK_RAIL_ITEM = registerPaleOakRailItem(PALE_OAK_RAIL);
    @Unique
    private static final Item PALE_OAK_DETECTOR_RAIL_ITEM = registerPaleOakRailItem(PALE_OAK_DETECTOR_RAIL);
    @Unique
    private static final Item PALE_OAK_POWERED_RAIL_ITEM = registerPaleOakRailItem(PALE_OAK_POWERED_RAIL);
    @Unique
    private static final Item PALE_OAK_ACTIVATOR_RAIL_ITEM = registerPaleOakRailItem(PALE_OAK_ACTIVATOR_RAIL);

    @Invoker("registerRailBlock")
    public static Block invokeRegisterRailBlock(String woodType, Block railBlock) {
        throw new AssertionError();
    }

    @Invoker("registerRailBlock")
    public static Block invokeRegisterRailBlock(String woodType, Block railBlock, String railType) {
        throw new AssertionError();
    }

    @Invoker("registerRailItem")
    public static Item invokeRegisterRailItem(String woodType, Item railItem, String railType) {
        throw new AssertionError();
    }

    @Unique
    private static Block registerPaleOakRailBlock() {
        Block railBlock = invokeRegisterRailBlock(WOOD_TYPE, new RailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL)));
        PaleOakRailHolder.setBlock(RAIL, railBlock);
        return railBlock;
    }

    @Unique
    private static Block registerPaleOakRailBlock(RailType railType) {
        Block railBlock = invokeRegisterRailBlock(WOOD_TYPE,
                railType.equals(DETECTOR_RAIL) ?
                        new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DETECTOR_RAIL))
                        : new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(
                                railType.equals(RailType.ACTIVATOR_RAIL) ?
                                Blocks.ACTIVATOR_RAIL : Blocks.POWERED_RAIL)),
                railType.name().toLowerCase().replace("_rail", ""));
        PaleOakRailHolder.setBlock(railType, railBlock);
        return railBlock;
    }

    @Unique
    private static Item registerPaleOakRailItem(Block railBlock) {
        RailType railType = PaleOakRailHolder.getRailType(railBlock);
        Item railItem = invokeRegisterRailItem(WOOD_TYPE, new BlockItem(railBlock, new Item.Properties()), railType.name().toLowerCase().replace("rail", "").replace("_", ""));
        PaleOakRailHolder.setItem(railType, railItem);
        return railItem;
    }
}
