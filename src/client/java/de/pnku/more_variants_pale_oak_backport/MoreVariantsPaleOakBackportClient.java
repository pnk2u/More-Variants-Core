package de.pnku.more_variants_pale_oak_backport;

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
public class MoreVariantsPaleOakBackportClient implements ClientModInitializer {
	public static final String MOD_ID = "more_variants_pale_oak_backport";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID + " (Client)");


	@Override
	public void onInitializeClient() {
		ResourceManagerHelper.registerBuiltinResourcePack(
				withModId(MOD_ID),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
				Component.translatable("resourcePack." + MOD_ID + ".name"),
				ResourcePackActivationType.ALWAYS_ENABLED
		);
	}

	public static ResourceLocation withModId(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}


}
