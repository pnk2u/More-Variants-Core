package de.pnku.more_variants_core.mixin.mstv.nemos_more_ladder_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.nemosmoreladdervariants.init.NmlvBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(NmlvBlocks.class)
public abstract class NmlvBlocksMixin {
    @Shadow
    private static Block registerLadderBlock(String name, Block ladderBlock) {
        throw new AssertionError();
    }

    @Unique
    private static void registerLadderBlockVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantType ladderType = MoreVariantHolder.MoreVariantType.LADDER;
        for (MoreVariantWoodType woodType : woodTypes) {
            Block ladderBlock = registerLadderBlock(woodType.getName() + "_" + ladderType.registrationType(), new LadderBlock(Block.Properties.ofFullCopy(Objects.requireNonNull(ladderType.getVanillaBlock()))));
            MoreVariantHolder.setBlock(ladderType, woodType, ladderBlock);
        }
    }

    @Inject(method = "register", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterLadderBlocksAtHead(CallbackInfo ci) {
        registerLadderBlockVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    }
}
