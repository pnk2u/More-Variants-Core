package de.pnku.more_variants_core.mixin.more_chiseled_bookshelf_variants;

import de.pnku.mcbv.init.McbvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
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
    private static void registerChiseledBookshelfItemVariants(List<WoodType> woodTypes) {
        VariantType chiseledBookshelfType = VariantType.CHISELED_BOOKSHELF;
        for (WoodType woodType : woodTypes) {
            BlockItem chiseledBookshelfItem = new BlockItem(MoreVariantHolder.getBlock(chiseledBookshelfType, woodType), new Item.Properties());
            registerItem(chiseledBookshelfItem, chiseledBookshelfType.getVanillaItem());
            MoreVariantHolder.setItem(chiseledBookshelfType, woodType, chiseledBookshelfItem);
        }
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBlocksAtTail(CallbackInfo ci) {
        registerChiseledBookshelfItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
