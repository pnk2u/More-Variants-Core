package de.pnku.more_variants_core;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class MoreVariantsCoreClient implements ClientModInitializer {
	public static final String MOD_ID = "more_variants_core";
	public static final String MOD_NAME = "More Variants Core";
	public static final String PACK_ID = "compatibility_assets";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME + " (Client)");

	@Override
	public void onInitializeClient() {
		ResourceManagerHelper.registerBuiltinResourcePack(
				withModId(PACK_ID),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
				Component.translatable(String.join(".","resourcePack", MOD_ID, PACK_ID, "name")),
				ResourcePackActivationType.ALWAYS_ENABLED
		);
	}

	public static ResourceLocation withModId(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
