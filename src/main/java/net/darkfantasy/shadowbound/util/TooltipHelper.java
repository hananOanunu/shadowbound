package net.darkfantasy.shadowbound.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;

public class TooltipHelper {
    public static boolean isShiftDown() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            return ClientHelper.hasShiftDown();
        }
        return false;
    }

    private static class ClientHelper {
        private static boolean hasShiftDown() {
            return Screen.hasShiftDown();
        }
    }
}
