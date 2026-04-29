package de.pnku.more_variants_core.mixin.more_chiseled_bookshelf_variants;

import de.pnku.mcbv.init.McbvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
        for (WoodType woodType : woodTypes) {
            BlockItem chiseledBookshelfItem = new BlockItem(MoreVariantHolder.getBlock(VariantType.CHISELED_BOOKSHELF, woodType), new Item.Properties());
            registerItem(chiseledBookshelfItem, Items.CHISELED_BOOKSHELF);
            MoreVariantHolder.setItem(VariantType.CHISELED_BOOKSHELF, woodType, chiseledBookshelfItem);
        }
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBlocksAtTail(CallbackInfo ci) {
        registerChiseledBookshelfItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
