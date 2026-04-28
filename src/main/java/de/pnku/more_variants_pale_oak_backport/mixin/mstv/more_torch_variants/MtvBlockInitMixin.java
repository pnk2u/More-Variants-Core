package de.pnku.more_variants_pale_oak_backport.mixin.mstv.more_torch_variants;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.TorchType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.mstv_mtv.init.MtvBlockInit;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import static de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.*;
import static de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.TorchType.*;

@Mixin(MtvBlockInit.class)
public abstract class MtvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_TORCH = registerPaleOakTorchBlock(TORCH);
    @Unique
    private static final Block PALE_OAK_WALL_TORCH = registerPaleOakWallTorchBlock(WALL_TORCH, PALE_OAK_TORCH);
    @Unique
    private static final Block PALE_OAK_SOUL_TORCH = registerPaleOakTorchBlock(SOUL_TORCH);
    @Unique
    private static final Block PALE_OAK_SOUL_WALL_TORCH = registerPaleOakWallTorchBlock(SOUL_WALL_TORCH, PALE_OAK_SOUL_TORCH);
    @Unique
    private static final Block PALE_OAK_REDSTONE_TORCH = registerPaleOakTorchBlock(REDSTONE_TORCH);
    @Unique
    private static final Block PALE_OAK_REDSTONE_WALL_TORCH = registerPaleOakWallTorchBlock(REDSTONE_WALL_TORCH, PALE_OAK_REDSTONE_TORCH);
    @Unique
    private static final Item PALE_OAK_TORCH_ITEM = registerPaleOakTorchItem(WALL_TORCH);
    @Unique
    private static final Item PALE_OAK_SOUL_TORCH_ITEM = registerPaleOakTorchItem(SOUL_WALL_TORCH);
    @Unique
    private static final Item PALE_OAK_REDSTONE_TORCH_ITEM = registerPaleOakTorchItem(REDSTONE_WALL_TORCH);

    @Shadow
    public static Block registerTorchBlock(String name, Block torchBlock) {
        throw new AssertionError();
    }

    @Shadow
    public static Item registerFireTorchItem(String name, Item torchItem) {
        throw new AssertionError();
    }

    @Shadow
    public static Item registerSoulTorchItem(String name, Item torchItem) {
        throw new AssertionError();
    }

    @Shadow
    public static Item registerRedstoneTorchItem(String name, Item torchItem) {
        throw new AssertionError();
    }

    @Unique
    private static Block registerPaleOakTorchBlock(TorchType torchType) {
        Block inputTorchBlock = torchType.equals(REDSTONE_TORCH) ?
                new RedstoneTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_TORCH)) :
                new TorchBlock(torchType.getParticleType(), BlockBehaviour.Properties.ofFullCopy(getVanillaTorchBlock(torchType)));
        Block torchBlock = registerTorchBlock(WOOD_TYPE.getName() + "_" + torchType.torchName(), inputTorchBlock);
        PaleOakVariantHolder.setBlock(torchType, WOOD_TYPE, torchBlock);
        return torchBlock;
    }

    @Unique
    private static Block registerPaleOakWallTorchBlock(TorchType torchType, Block torchBlock) {
        Block inputWallTorchBlock = torchType.equals(REDSTONE_TORCH) ?
                new RedstoneWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WALL_TORCH))
                : new WallTorchBlock(torchType.getParticleType(), BlockBehaviour.Properties.ofFullCopy(getVanillaWallTorchBlock(torchType)).dropsLike(torchBlock));
        Block wallTorchBlock = registerTorchBlock(WOOD_TYPE.getName()+ "_" + torchType.wallTorchName(), inputWallTorchBlock);
        PaleOakVariantHolder.setBlock(torchType, WOOD_TYPE, wallTorchBlock);
        return wallTorchBlock;
    }

    @Unique
    private static Item registerPaleOakTorchItem(TorchType wallTorchType) {
        TorchType torchType = wallTorchType.getBaseTorchType();
        Block torchBlock = PaleOakVariantHolder.getBlock(torchType, WOOD_TYPE);
        Block wallTorchBlock = PaleOakVariantHolder.getBlock(wallTorchType, WOOD_TYPE);
        StandingAndWallBlockItem inputItem = new StandingAndWallBlockItem(torchBlock, wallTorchBlock, new Item.Properties(), Direction.DOWN);
        String torchName = WOOD_TYPE.getName() + "_" + torchType.torchName();
        Item torchItem;
        if (torchType == REDSTONE_TORCH) {
            torchItem = registerRedstoneTorchItem(torchName, inputItem);
        } else if (torchType == SOUL_TORCH) {
            torchItem = registerSoulTorchItem(torchName, inputItem);
        } else {
            torchItem = registerFireTorchItem(torchName, inputItem);
        }
        PaleOakVariantHolder.setItem(torchType, WOOD_TYPE, torchItem);
        return torchItem;
    }
}
