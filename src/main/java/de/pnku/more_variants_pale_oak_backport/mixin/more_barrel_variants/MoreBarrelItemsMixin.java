package de.pnku.more_variants_pale_oak_backport.mixin.more_barrel_variants;

import de.pnku.more_barrel_variants.init.MoreBarrelItems;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBarrelHolder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;


@Mixin(MoreBarrelItems.class)
public abstract class MoreBarrelItemsMixin {
    @Shadow
    @Mutable
    @Final
    public static List<Item> more_barrels;
    @Unique
    private static final Item PALE_OAK_BARREL_ITEM = registerPaleOakBarrelItem();

    @Inject(method = "registerItems", at = @At(value = "HEAD", target = "Lde/pnku/mstv_base/item/MoreStickVariantItems;registerStickItem(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/Item;)V"), remap = false)
    private static void injectedRegisterSticksAtRegisterDarkOak(CallbackInfo ci) {
        more_barrels = new ArrayList<>(more_barrels);
        if (!more_barrels.contains(PALE_OAK_BARREL_ITEM)) {
            more_barrels.add(PALE_OAK_BARREL_ITEM);
        }
    }

    @Unique
    private static Item registerPaleOakBarrelItem() {
        String woodType = "pale_oak";
        Item stickItem = new BlockItem(PaleOakBarrelHolder.PALE_OAK_BARREL, new Item.Properties());
        PaleOakBarrelHolder.PALE_OAK_BARREL_ITEM = stickItem;
        return stickItem;
    }
}
