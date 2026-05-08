package de.pnku.more_variants_core.mixin.mstv.more_rail_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.RailType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mstv_mrailv.init.MrailvBlockInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DetectorRailBlock;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.RailBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static de.pnku.more_variants_core.util.MoreVariantHolder.RailType.DETECTOR_RAIL;
import static de.pnku.more_variants_core.util.MoreVariantHolder.RailType.RAIL;

@Mixin(MrailvBlockInit.class)
public abstract class MrailvBlockInitMixin {
    @Shadow
    private static Block registerRailBlock(String woodType, Block railBlock) {
        throw new AssertionError();
    }

    @Shadow
    private static Block registerRailBlock(String woodType, Block railBlock, String railType) {
        throw new AssertionError();
    }

    @Shadow
    private static Item registerRailItem(String woodType, Item railItem, String railType) {
        throw new AssertionError();
    }

    @Unique
    private static void registerRailBlockVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (RailType railType : RailType.values()) {
                Block inputRailBlock = createInputRailBlock(railType);
                Block railBlock = railType == RAIL
                        ? registerRailBlock(woodType.getName(), inputRailBlock)
                        : registerRailBlock(woodType.getName(), inputRailBlock, railType.registrationType());
                MoreVariantHolder.setBlock(railType, woodType, railBlock);
            }
        }
    }

    @Unique
    private static void registerRailItemVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (RailType railType : RailType.values()) {
                Item railItem = registerRailItem(woodType.getName(), new BlockItem(MoreVariantHolder.getBlock(railType, woodType), new Item.Properties()), railType.registrationType());
                MoreVariantHolder.setItem(railType, woodType, railItem);
            }
        }
    }

    @Unique
    private static Block createInputRailBlock(RailType railType) {
        Block vanillaRailBlock = railType.getVanillaBlock();
        if (railType == RAIL) {
            return new RailBlock(BlockBehaviour.Properties.ofFullCopy(vanillaRailBlock));
        }
        if (railType == DETECTOR_RAIL) {
            return new DetectorRailBlock(BlockBehaviour.Properties.ofFullCopy(vanillaRailBlock));
        }
        return new PoweredRailBlock(BlockBehaviour.Properties.ofFullCopy(vanillaRailBlock));
    }

    @Inject(method = "registerRail", at = @At("HEAD"), remap = false)
    private static void injectedRegisterRailAtHead(CallbackInfo ci) {
        List<MoreVariantWoodType> woodTypes = MoreVariantWoodTypeHolder.getWoodTypes();
        registerRailBlockVariants(woodTypes);
        registerRailItemVariants(woodTypes);
    }
}
