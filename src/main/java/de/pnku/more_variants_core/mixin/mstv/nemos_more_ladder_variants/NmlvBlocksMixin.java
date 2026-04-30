package de.pnku.more_variants_core.mixin.mstv.nemos_more_ladder_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import de.pnku.nemosmoreladdervariants.init.NmlvBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(NmlvBlocks.class)
public abstract class NmlvBlocksMixin {
    @Shadow
    private static Block registerLadderBlock(String name, Block ladderBlock) {
        throw new AssertionError();
    }

    @Unique
    private static void registerLadderBlockVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            Block ladderBlock = registerLadderBlock(woodType.getName() + "_" + VariantType.LADDER.registrationType(), new LadderBlock(Block.Properties.ofFullCopy(Blocks.LADDER)));
            MoreVariantHolder.setBlock(VariantType.LADDER, woodType, ladderBlock);
        }
    }

    @Inject(method = "register", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterLadderBlocksAtHead(CallbackInfo ci) {
        registerLadderBlockVariants(WoodTypeHolder.getWoodTypes());
    }
}
