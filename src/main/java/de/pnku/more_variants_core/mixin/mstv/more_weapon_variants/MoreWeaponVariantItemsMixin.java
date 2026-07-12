package de.pnku.more_variants_core.mixin.mstv.more_weapon_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.WeaponType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mstv_mweaponv.item.MoreWeaponVariantItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

import static de.pnku.more_variants_core.util.MoreVariantHolder.getRegistrationId;
import static de.pnku.more_variants_core.util.MoreVariantHolder.getRegistrationIds;
import static de.pnku.more_variants_core.util.MoreVariantRegistryHelper.whenItemRegistered;

@Mixin(MoreWeaponVariantItems.class)
public abstract class MoreWeaponVariantItemsMixin {
    @Shadow static int swordAD;
    @Shadow static float swordAS;

    @Shadow
    private static void registerSwordItem(Item swordItem, Item stickIngredient, String materialPrefix) {}

    @Shadow
    private static Item.Properties swordProperties(Tier tier) {throw new AssertionError();}

    @Shadow
    private static void registerBowItem(Item bowItem, Item stickIngredient) {}

    @Shadow
    private static Item.Properties bowProperties() {throw new AssertionError();}

    @Shadow
    private static void registerCrossbowItem(Item crossbowItem, Item stickIngredient) {}

    @Shadow
    private static Item.Properties crossbowProperties() {throw new AssertionError();}

    @Shadow
    private static void registerArrowItem(Item arrowItem, Item stickIngredient, Item tippedArrowItem, int arrowId) {}

    @Shadow
    private static Item.Properties arrowProperties(boolean isTipped) {throw new AssertionError();}

    @Unique
    private static Item createSwordItemVariant(WeaponType weaponType) {
        return new SwordItem(weaponType.materialType().tier(), swordAD, swordAS, swordProperties(weaponType.materialType().tier()));
    }

    @Unique
    private static Item createBowItemVariant() {
        return new BowItem(bowProperties());
    }

    @Unique
    private static Item createCrossbowItemVariant() {
        return new CrossbowItem(crossbowProperties());
    }

    @Unique
    private static Item createArrowItemVariant(boolean isTipped) {
        return new ArrowItem(arrowProperties(isTipped));
    }

    @Unique
    private static void registerWeaponVariantForType(WeaponType[] swordTypes, MoreVariantWoodType woodType, Item stickItem) {
        for (WeaponType weaponType : swordTypes) {
            if (weaponType.isSword()) {
                Item swordItem = createSwordItemVariant(weaponType);
                registerSwordItem(swordItem, stickItem, weaponType.materialType().namePrefix());
                MoreVariantHolder.setItem(weaponType, woodType, swordItem);
            }
        }
        Item bowItem = createBowItemVariant();
        registerBowItem(bowItem, stickItem);
        MoreVariantHolder.setItem(WeaponType.BOW, woodType, bowItem);

        Item crossbowItem = createCrossbowItemVariant();
        registerCrossbowItem(crossbowItem, stickItem);
        MoreVariantHolder.setItem(WeaponType.CROSSBOW, woodType, crossbowItem);

        Item arrowItem = createArrowItemVariant(false);
        Item tippedArrowItem = createArrowItemVariant(true);
        registerArrowItem(arrowItem, stickItem, tippedArrowItem, woodType.getIntId());
        MoreVariantHolder.setItem(WeaponType.ARROW, woodType, arrowItem);
        MoreVariantHolder.setItem(WeaponType.TIPPED_ARROW, woodType, tippedArrowItem);
    }

    @Unique
    private static void registerWeaponItemVariants(List<MoreVariantWoodType> woodTypes) {
        WeaponType[] swordTypes = Arrays.stream(WeaponType.values()).filter(WeaponType::isSword).toArray(WeaponType[]::new);
        for (MoreVariantWoodType woodType : woodTypes) {
            ResourceLocation stickId = getRegistrationId(MoreVariantHolder.MoreVariantType.STICK, woodType);
            if (BuiltInRegistries.ITEM.containsKey(stickId)) {
                Item stickItem = MoreVariantHolder.getItem(MoreVariantHolder.MoreVariantType.STICK, woodType);
                registerWeaponVariantForType(swordTypes, woodType, stickItem);
            } else {
                whenItemRegistered(stickId,
                        stickItem -> registerWeaponVariantForType(swordTypes, woodType, stickItem),
                        getRegistrationIds(swordTypes, woodType));
            }
        }
    }

    @Inject(method = "registerWeaponItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterWeaponItemsAtTail(CallbackInfo ci) {
        registerWeaponItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
