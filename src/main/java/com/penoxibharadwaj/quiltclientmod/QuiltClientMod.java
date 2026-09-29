package com.penoxibharadwaj.quiltclientmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public
class QuiltClientMod implements ClientModInitializer {
    public static final String MOD_ID = "quiltclientmod";
    public static final KeyBinding CLICK_GUI_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
    "key.quiltclientmod.clickgui",
    InputUtil.Type.KEYSYM,
    GLFW.GLFW_KEY_RIGHT_SHIFT,
    "category.quiltclientmod.keybinds"
    ));
    public static final KeyBinding MUSIC_PLAYER_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
    "key.quiltclientmod.musicplayer",
    InputUtil.Type.KEYSYM,
    GLFW.GLFW_KEY_M,
    "category.quiltclientmod.keybinds"
    ));
    public static final KeyBinding MOD_MENU_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
    "key.quiltclientmod.modmenu",
    InputUtil.Type.KEYSYM,
    GLFW.GLFW_KEY_F8,
    "category.quiltclientmod.keybinds"
    ));

    @Override
    public void onInitializeClient() {
        ModuleManager.initialize();
        ConfigManager.initialize();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (CLICK_GUI_KEY.wasPressed()) {
                ClickGUI.open();
            }
            if (MUSIC_PLAYER_KEY.wasPressed()) {
                MusicPlayerGUI.open();
            }
            if (MOD_MENU_KEY.wasPressed()) {
                ModMenuGUI.open();
            }
        });
    }
}
