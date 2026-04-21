package de.pnku.more_variants_pale_oak_backport.mixin.mstv.more_torch_variants;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakTorchHolder;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakTorchHolder.TorchType;
import de.pnku.mstv_mtv.init.MtvBlockInit;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

import static de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakTorchHolder.*;
import static de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakTorchHolder.TorchType.*;

@Mixin(MtvBlockInit.class)
public abstract class MtvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_TORCH = registerPaleOakTorchBlock(TORCH);
    @Unique
    private static final Block PALE_OAK_WALL_TORCH = registerPaleOakWallTorchBlock(TORCH, PALE_OAK_TORCH);
    @Unique
    private static final Block PALE_OAK_SOUL_TORCH = registerPaleOakTorchBlock(SOUL_TORCH);
    @Unique
    private static final Block PALE_OAK_SOUL_WALL_TORCH = registerPaleOakWallTorchBlock(SOUL_TORCH, PALE_OAK_SOUL_TORCH);
    @Unique
    private static final Block PALE_OAK_REDSTONE_TORCH = registerPaleOakTorchBlock(REDSTONE_TORCH);
    @Unique
    private static final Block PALE_OAK_REDSTONE_WALL_TORCH = registerPaleOakWallTorchBlock(REDSTONE_TORCH, PALE_OAK_REDSTONE_TORCH);
    @Unique
    private static final Item PALE_OAK_TORCH_ITEM = registerPaleOakTorchItem(TORCH);
    @Unique
    private static final Item PALE_OAK_SOUL_TORCH_ITEM = registerPaleOakTorchItem(SOUL_TORCH);
    @Unique
    private static final Item PALE_OAK_REDSTONE_TORCH_ITEM = registerPaleOakTorchItem(REDSTONE_TORCH);

    @Invoker("registerTorchBlock")
    public static Block invokeRegisterTorchBlock(String name, Block torchBlock) {
        throw new AssertionError();
    }

    @Invoker("registerFireTorchItem")
    public static Item invokeRegisterFireTorchItem(String name, Item torchItem) {
        throw new AssertionError();
    }

    @Invoker("registerSoulTorchItem")
    public static Item invokeRegisterSoulTorchItem(String name, Item torchItem) {
        throw new AssertionError();
    }

    @Invoker("registerRedstoneTorchItem")
    public static Item invokeRegisterRedstoneTorchItem(String name, Item torchItem) {
        throw new AssertionError();
    }

    @Unique
    private static Block registerPaleOakTorchBlock(TorchType torchType) {
        Block inputTorchBlock = torchType.equals(REDSTONE_TORCH) ?
                new RedstoneTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_TORCH)) :
                new TorchBlock(getParticleType(torchType), BlockBehaviour.Properties.ofFullCopy(getVanillaTorchBlock(torchType)));
        Block torchBlock = invokeRegisterTorchBlock(WOOD_TYPE.getName() + "_" + torchType.torchName(), inputTorchBlock);
        PaleOakTorchHolder.setBlock(WOOD_TYPE, torchType, torchBlock);
        return torchBlock;
    }

    @Unique
    private static Block registerPaleOakWallTorchBlock(TorchType torchType, Block torchBlock) {
        Block inputWallTorchBlock = torchType.equals(REDSTONE_TORCH) ?
                new RedstoneWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WALL_TORCH))
                : new WallTorchBlock(getParticleType(torchType), BlockBehaviour.Properties.ofFullCopy(getVanillaWallTorchBlock(torchType)).dropsLike(torchBlock));
        Block wallTorchBlock = invokeRegisterTorchBlock(WOOD_TYPE.getName()+ "_" + torchType.wallTorchName(), inputWallTorchBlock);
        PaleOakTorchHolder.setWallBlock(WOOD_TYPE, torchType, wallTorchBlock);
        return wallTorchBlock;
    }

    @Unique
    private static Item registerPaleOakTorchItem(TorchType torchType) {
        Block torchBlock = PaleOakTorchHolder.getBlock(WOOD_TYPE, torchType);
        Block wallTorchBlock = PaleOakTorchHolder.getWallBlock(WOOD_TYPE, torchType);
        StandingAndWallBlockItem inputItem = new StandingAndWallBlockItem(torchBlock, wallTorchBlock, new Item.Properties(), Direction.DOWN);
        String torchName = WOOD_TYPE.getName() + "_" + torchType.torchName();
        Item torchItem;
        if (torchType == REDSTONE_TORCH) {
            torchItem = invokeRegisterRedstoneTorchItem(torchName, inputItem);
        } else if (torchType == SOUL_TORCH) {
            torchItem = invokeRegisterSoulTorchItem(torchName, inputItem);
        } else {
            torchItem = invokeRegisterFireTorchItem(torchName, inputItem);
        }
        PaleOakTorchHolder.setItem(WOOD_TYPE, torchType, torchItem);
        return torchItem;
    }
}
