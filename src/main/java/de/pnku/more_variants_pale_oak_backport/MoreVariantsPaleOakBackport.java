package de.pnku.more_variants_pale_oak_backport;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class MoreVariantsPaleOakBackport implements ModInitializer {
	public static final String MOD_ID = "more_variants_pale_oak_backport";
	public static final String MOD_NAME = "More Variants: Pale Oak Backport";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	public static List<String> found_mod_ids = new ArrayList<>();

	
	@Override
	public void onInitialize() {
		BuiltInRegistries.BLOCK.getOptional(ResourceLocation.withDefaultNamespace("pale_oak_planks"))
				.ifPresentOrElse(p -> LOGGER.info("Found Pale Oak Planks block in registry, adding Pale Oak variants for compatible Mods."),
						() -> {throw new IllegalStateException("Pale Oak Planks block not found in registry.\n" +
								"Please install a mod that backports Pale Oak to Minecraft and uses the vanilla namespace.");});
		if (!found_mod_ids.isEmpty()) {
			List<String> modNames = new ArrayList<>();
			for (String modId : found_mod_ids) {
				modNames.add(FabricLoader.getInstance().getModContainer(modId).map(modContainer -> modContainer.getMetadata().getName()).orElse(modId));
			}
			LOGGER.info("Added Pale Oak variants for the following Mods: {}", modNames);
		} else {
			LOGGER.warn("Did not find any compatible Mods to add Pale Oak variants to.");
		}
	}

	public static ResourceLocation withModId(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

}
