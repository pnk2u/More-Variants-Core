package de.pnku.more_variants_core.mixin.mstv.more_torch_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.TorchType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mstv_mtv.init.MtvBlockInit;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static de.pnku.more_variants_core.util.MoreVariantHolder.TorchType.REDSTONE_TORCH;
import static de.pnku.more_variants_core.util.MoreVariantHolder.TorchType.SOUL_TORCH;

@Mixin(MtvBlockInit.class)
public abstract class MtvBlockInitMixin {
    @Shadow
    private static Block registerTorchBlock(String name, Block torchBlock) {
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
    private static void registerTorchBlockVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (TorchType torchType : TorchType.values()) {
                Block vanillaTorchBlock = torchType.getVanillaBlock();
                if (!torchType.isWallTorch()) {
                    Block inputTorchBlock = torchType.equals(REDSTONE_TORCH) ?
                            new RedstoneTorchBlock(BlockBehaviour.Properties.ofFullCopy(vanillaTorchBlock)) :
                            new TorchBlock(torchType.getParticleType(), BlockBehaviour.Properties.ofFullCopy(vanillaTorchBlock));
                    Block torchBlock = registerTorchBlock(woodType.getName() + "_" + torchType.torchName(), inputTorchBlock);
                    MoreVariantHolder.setBlock(torchType, woodType, torchBlock);
                } else {
                    Block baseTorchBlock = MoreVariantHolder.getBlock(torchType.getBaseTorchType(), woodType);
                    Block inputWallTorchBlock = torchType.getBaseTorchType().equals(REDSTONE_TORCH) ?
                            new RedstoneWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(vanillaTorchBlock).dropsLike(baseTorchBlock))
                            : new WallTorchBlock(torchType.getParticleType(), BlockBehaviour.Properties.ofFullCopy(vanillaTorchBlock).dropsLike(baseTorchBlock));
                    Block wallTorchBlock = registerTorchBlock(woodType.getName() + "_" + torchType.wallTorchName(), inputWallTorchBlock);
                    MoreVariantHolder.setBlock(torchType, woodType, wallTorchBlock);
                }
            }
        }
    }

    @Unique
    private static void registerTorchItemVariants(List<MoreVariantWoodType> woodTypes) {
        for (TorchType wallTorchType : TorchType.values()) {
            if (!wallTorchType.isWallTorch()) continue;
            TorchType torchType = wallTorchType.getBaseTorchType();
            for (MoreVariantWoodType woodType : woodTypes) {
                Block torchBlock = MoreVariantHolder.getBlock(torchType, woodType);
                Block wallTorchBlock = MoreVariantHolder.getBlock(wallTorchType, woodType);
                StandingAndWallBlockItem inputItem = new StandingAndWallBlockItem(torchBlock, wallTorchBlock, new Item.Properties(), Direction.DOWN);
                String torchName = woodType.getName() + "_" + torchType.torchName();
                Item torchItem;
                if (torchType == REDSTONE_TORCH) {
                    torchItem = registerRedstoneTorchItem(torchName, inputItem);
                } else if (torchType == SOUL_TORCH) {
                    torchItem = registerSoulTorchItem(torchName, inputItem);
                } else {
                    torchItem = registerFireTorchItem(torchName, inputItem);
                }
                MoreVariantHolder.setItem(torchType, woodType, torchItem);
            }
        }
    }

    @Inject(method = "register", at = @At("HEAD"), remap = false)
    private static void injectedRegisterAtHead(CallbackInfo ci) {
        List<MoreVariantWoodType> woodTypes = MoreVariantWoodTypeHolder.getMoreVariantWoodTypes();
        registerTorchBlockVariants(woodTypes);
        registerTorchItemVariants(woodTypes);
    }
}
