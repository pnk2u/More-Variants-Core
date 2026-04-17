package de.pnku.more_variants_pale_oak_backport;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MoreVariantsPaleOakBackport implements ModInitializer {
	public static final String MOD_ID = "more_variants_pale_oak_backport";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	
	@Override
	public void onInitialize() {

	}

	public static ResourceLocation withModId(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

}
