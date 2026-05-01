package de.pnku.more_variants_core.mixin.more_jukebox_noteblock_variants;

import de.pnku.mjnv.init.MjnvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.JukeboxNoteblockType;
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

@Mixin(MjnvItemInit.class)
public abstract class MjnvItemInitMixin {
    @Shadow
    private static void registerJukeboxItem(BlockItem jukebox, Item jukeboxAfter) {}

    @Shadow
    private static void registerNoteblockItem(BlockItem noteblock, Item noteblockAfter) {}

    @Unique
    private static void registerJukeboxNoteblockItemVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            for (JukeboxNoteblockType type : JukeboxNoteblockType.values()) {
                BlockItem item = new BlockItem(MoreVariantHolder.getBlock(type, woodType), new Item.Properties());
                Item vanillaItem = type.getVanillaItem();
                MoreVariantHolder.setItem(type.variantType(), woodType, item);
                if (JukeboxNoteblockType.JUKEBOX.equals(type)) {
                    registerJukeboxItem(item, vanillaItem);
                } else {
                    registerNoteblockItem(item, vanillaItem);
                }
            }
        }
    }

    @Inject(method = "registerJukeboxNoteblockItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterJukeboxNoteblockItemsAtTail(CallbackInfo ci) {
        registerJukeboxNoteblockItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
