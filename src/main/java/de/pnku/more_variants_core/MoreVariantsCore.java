package de.pnku.more_variants_core;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class MoreVariantsCore implements ModInitializer {
	public static final String MOD_ID = "more_variants_core";
	public static final String MOD_NAME = "More Variants Core";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	public static List<String> found_mod_ids = new ArrayList<>();

	
	@Override
	public void onInitialize() {
		if (!found_mod_ids.isEmpty()) {
			List<String> modNames = new ArrayList<>();
			for (String modId : found_mod_ids) {
				modNames.add(FabricLoader.getInstance().getModContainer(modId).map(modContainer -> modContainer.getMetadata().getName()).orElse(modId));
			}
			LOGGER.info("Added More Variants for: {}", modNames);
		} else {
			LOGGER.warn("Did not find any compatible Mods to add More Variants to.");
		}
	}

	public static ResourceLocation withModId(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

}
