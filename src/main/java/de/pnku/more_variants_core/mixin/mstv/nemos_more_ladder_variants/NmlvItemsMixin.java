package de.pnku.more_variants_core.mixin.mstv.nemos_more_ladder_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.nemosmoreladdervariants.init.NmlvItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(NmlvItems.class)
public abstract class NmlvItemsMixin {
    @Shadow
    private static Item registerLadderBlockItem(String name, Block ladderBlock) {
        throw new AssertionError();
    }

    @Unique
    private static void registerLadderItemVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantHolder.MoreVariantType ladderType = MoreVariantHolder.MoreVariantType.LADDER;
        for (MoreVariantWoodType woodType : woodTypes) {
            Item ladderItem = registerLadderBlockItem(woodType.getName() + "_" + ladderType.registrationType(), MoreVariantHolder.getBlock(ladderType, woodType));
            MoreVariantHolder.setItem(ladderType, woodType, ladderItem);
        }
    }

    @Inject(method = "register", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterLadderItemsAtHead(CallbackInfo ci) {
        registerLadderItemVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    }
}
