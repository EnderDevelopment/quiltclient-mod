package com.penoxibharadwaj.quiltclientmod.modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.List;

public
class ModuleManager {
    private static final List<Module> modules = new ArrayList<>();

    public static void initialize() {
        registerModules();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            for (Module module : modules) {
                if (module.getKeyBinding() != null && module.getKeyBinding().wasPressed()) {
                    module.setEnabled(!module.isEnabled());
                }
            }
        });
    }

    private static void registerModules() {
        // Register all modules here
        modules.add(new FullbrightModule());
        modules.add(new ZoomModule());
        // Add more modules as needed
    }

    public static List<Module> getModules() {
        return modules;
    }

    public static Module getModuleByName(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }
}
