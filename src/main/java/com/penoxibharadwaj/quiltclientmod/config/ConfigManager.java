package com.penoxibharadwaj.quiltclientmod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.penoxibharadwaj.quiltclientmod.modules.Module;
import com.penoxibharadwaj.quiltclientmod.modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public
class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(MinecraftClient.getInstance().runDirectory, "quiltclientmod.json");

    public static void initialize() {
        loadConfig();
    }

    public static void saveConfig() {
        Map<String, Boolean> moduleStates = new HashMap<>();
        for (Module module : ModuleManager.getModules()) {
            moduleStates.put(module.getName(), module.isEnabled());
        }
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(moduleStates, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadConfig() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                Map<String, Boolean> moduleStates = GSON.fromJson(reader, Map.class);
                for (Module module : ModuleManager.getModules()) {
                    if (moduleStates.containsKey(module.getName())) {
                        module.setEnabled(moduleStates.get(module.getName()));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
