package de.pnku.more_variants_core.mixin.more_barrel_variants;

import de.pnku.more_barrel_variants.block.MoreBarrelBlock;
import de.pnku.more_barrel_variants.init.MoreBarrelBlocks;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;


@Mixin(MoreBarrelBlocks.class)
public abstract class MoreBarrelBlocksMixin {
    @Shadow
    @Mutable
    @Final
    public static List<Block> more_barrels;

    @Unique
    private static List<Block> registerBarrelBlockVariants(List<WoodType> woodTypes) {
        List<Block> barrelBlocks = new ArrayList<>();
        VariantType barrelType = VariantType.BARREL;
        for (WoodType woodType : woodTypes) {
            Block barrelBlock = new MoreBarrelBlock(woodType.getMapColor(), woodType.getName());
            MoreVariantHolder.setBlock(barrelType, woodType, barrelBlock);
            barrelBlocks.add(barrelBlock);
        }
        return barrelBlocks;
    }

    @Inject(method = "registerBlocks", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterBlocksAtHead(CallbackInfo ci) {
        more_barrels = new ArrayList<>(more_barrels);
        more_barrels.addAll(registerBarrelBlockVariants(WoodTypeHolder.getWoodTypes()));
    }
}
