package de.pnku.more_variants_core.mixin;

import de.pnku.more_variants_core.util.MoreVariantMod;
import net.fabricmc.loader.api.FabricLoader;

import java.util.HashMap;
import java.util.Map;

import static de.pnku.more_variants_core.MoreVariantsCore.found_mod_ids;
import static de.pnku.more_variants_core.MoreVariantsCore.LOGGER;

public final class MoreVariantsCoreMixinDependencyResolver {
    private MoreVariantsCoreMixinDependencyResolver() {}
    public static Map<String, String> MIXIN_PACKAGE_TO_MOD_ID = new HashMap<>();

    public static void init() {
        Map<String, String> map = new HashMap<>();
        for (MoreVariantMod mod : MoreVariantMod.values()) {
            map.put(mod.mixinSubPackage(), mod.modId());
        }
        map.put("compat", "everycomp");
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
        String packagePrefix = "de.pnku.more_variants_core." + (isClient ? "client." : "") + "mixin.";
        String normalizedPackageName = mixinClassName.replaceFirst(packagePrefix, "")
                .substring(0, mixinClassName
                        .lastIndexOf('.') - packagePrefix.length());
        return MIXIN_PACKAGE_TO_MOD_ID.get(normalizedPackageName);
    }
}