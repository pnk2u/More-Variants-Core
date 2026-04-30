package de.pnku.more_variants_core.mixin.mstv.nemos_more_ladder_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
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
    private static void registerLadderItemVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            Item ladderItem = registerLadderBlockItem(woodType.getName() + "_" + VariantType.LADDER.registrationType(), MoreVariantHolder.getBlock(VariantType.LADDER, woodType));
            MoreVariantHolder.setItem(VariantType.LADDER, woodType, ladderItem);
        }
    }

    @Inject(method = "register", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterLadderItemsAtHead(CallbackInfo ci) {
        registerLadderItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
