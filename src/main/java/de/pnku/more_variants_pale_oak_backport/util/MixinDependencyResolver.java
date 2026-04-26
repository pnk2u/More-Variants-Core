package de.pnku.more_variants_pale_oak_backport.util;

import net.fabricmc.loader.api.FabricLoader;

import java.util.HashMap;
import java.util.Map;

import static de.pnku.more_variants_pale_oak_backport.MoreVariantsPaleOakBackport.found_mod_ids;
import static de.pnku.more_variants_pale_oak_backport.MoreVariantsPaleOakBackport.LOGGER;

public final class MixinDependencyResolver {
    private MixinDependencyResolver() {}
    public static Map<String, String> MIXIN_PACKAGE_TO_MOD_ID = new HashMap<>();

    public static void init() {
        Map<String, String> map = new HashMap<>();
        map.put("more_barrel_variants", "more_barrel_variants");
        map.put("more_bed_variants", "quad-lolmbdv");
        map.put("more_beehive_variants", "quad-lolmbhv");
        map.put("more_bookshelf_variants", "lolmbv");
        map.put("more_cartography_tables", "lolmcgt");
        map.put("more_chest_variants", "lolmcv");
        map.put("more_chiseled_bookshelf_variants", "lolmcbv");
        map.put("more_composter_variants", "lolmcmv");
        map.put("more_crafter_variants", "quad-lolmcrv");
        map.put("more_crafting_tables", "lolmct");
        map.put("more_fletching_tables", "lolmft");
        map.put("more_grindstone_variants", "lolmgv");
        map.put("more_jukebox_noteblock_variants", "quad-lolmjnv");
        map.put("more_lectern_variants", "lolmlv");
        map.put("more_loom_variants", "lolmlmv");
        map.put("more_shield_variants", "lolmsv");
        map.put("more_smithing_tables", "lolmst");
        map.put("more_smoker_variants", "quad-lolmsmv");
        map.put("mstv.base", "mstv-base");
        map.put("mstv.more_armor_stand_variants", "mstv-masv");
        map.put("mstv.more_fishing_rod_variants", "mstv-mfrv");
        map.put("mstv.more_frame_variants", "mstv-mframev");
        map.put("mstv.more_rail_variants", "quad-mstv-mrailv");
        map.put("mstv.more_tool_variants", "mstv-mtoolv");
        map.put("mstv.more_torch_variants", "quad-mstv-mtv");
        map.put("mstv.more_weapon_variants", "mstv-mweaponv");
        map.put("mstv.nemos_more_ladder_variants", "nemos-moreladdervariants");
        MIXIN_PACKAGE_TO_MOD_ID = Map.copyOf(map);
    }

    public static boolean shouldApplyMixin(String mixinClassName, boolean isClient) {
        String requiredModId = getRequiredModId(mixinClassName, isClient);
        if (requiredModId == null) {
            LOGGER.warn("Could not determine required mod for mixin class: {}", mixinClassName);
            return false;
        } else if (FabricLoader.getInstance().isModLoaded(requiredModId)) {
            if (!found_mod_ids.contains(requiredModId)) found_mod_ids.add(requiredModId);
            return true;
        }
        return false;
    }

    public static String getRequiredModId(String mixinClassName, boolean isClient) {
        String packagePrefix = "de.pnku.more_variants_pale_oak_backport." + (isClient ? "client." : "") + "mixin.";
        String normalizedPackageName = mixinClassName.replaceFirst(packagePrefix, "")
                .substring(0, mixinClassName
                        .lastIndexOf('.') - packagePrefix.length());
        return MIXIN_PACKAGE_TO_MOD_ID.get(normalizedPackageName);
    }
}