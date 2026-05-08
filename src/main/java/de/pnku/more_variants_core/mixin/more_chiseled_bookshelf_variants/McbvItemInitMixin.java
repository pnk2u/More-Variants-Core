package de.pnku.more_variants_core.mixin.more_chiseled_bookshelf_variants;

import de.pnku.mcbv.init.McbvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(McbvItemInit.class)
public abstract class McbvItemInitMixin {
    @Shadow
    private static void registerItem(BlockItem chiseledBookshelf, Item chiseledBookshelfAfter) {}

    @Unique
    private static void registerChiseledBookshelfItemVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantHolder.MoreVariantType chiseledBookshelfType = MoreVariantType.CHISELED_BOOKSHELF;
        for (MoreVariantWoodType woodType : woodTypes) {
            BlockItem chiseledBookshelfItem = new BlockItem(MoreVariantHolder.getBlock(chiseledBookshelfType, woodType), new Item.Properties());
            registerItem(chiseledBookshelfItem, chiseledBookshelfType.getVanillaItem());
            MoreVariantHolder.setItem(chiseledBookshelfType, woodType, chiseledBookshelfItem);
        }
    }

        registerChiseledBookshelfItemVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    @Inject(method = "registerItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterBlocksAtHead(CallbackInfo ci) {
    }
}
