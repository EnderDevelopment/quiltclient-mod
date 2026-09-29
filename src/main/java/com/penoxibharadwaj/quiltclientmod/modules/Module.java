package com.penoxibharadwaj.quiltclientmod.modules;

import net.minecraft.client.option.KeyBinding;

public abstract
class Module {
    private final String name;
    private final String description;
    private final Category category;
    private boolean enabled;
    private KeyBinding keyBinding;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public KeyBinding getKeyBinding() {
        return keyBinding;
    }

    public void setKeyBinding(KeyBinding keyBinding) {
        this.keyBinding = keyBinding;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public enum Category {
        COMBAT,
        MOVEMENT,
        RENDER,
        WORLD,
        HUD,
        PLAYER,
        UTILITY,
        PERFORMANCE,
        VISUAL
    }
}
