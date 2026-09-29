package com.penoxibharadwaj.quiltclientmod.gui;

import com.penoxibharadwaj.quiltclientmod.modules.Module;
import com.penoxibharadwaj.quiltclientmod.modules.ModuleManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import java.util.ArrayList;
import java.util.List;

public
class ClickGUI extends Screen {
    private static final List<Module.Category> categories = new ArrayList<>();
    private Module.Category currentCategory;

    static {
        categories.add(Module.Category.COMBAT);
        categories.add(Module.Category.MOVEMENT);
        categories.add(Module.Category.RENDER);
        categories.add(Module.Category.WORLD);
        categories.add(Module.Category.HUD);
        categories.add(Module.Category.PLAYER);
        categories.add(Module.Category.UTILITY);
        categories.add(Module.Category.PERFORMANCE);
        categories.add(Module.Category.VISUAL);
    }

    public ClickGUI() {
        super(Text.of("QuiltClient ClickGUI"));
        this.currentCategory = categories.get(0);
    }

    @Override
    protected void init() {
        super.init();
        int x = 10;
        int y = 10;
        for (Module.Category category : categories) {
            addDrawableChild(new ButtonWidget(x, y, 100, 20, Text.of(category.name()), button -> {
                currentCategory = category;
                refreshModules();
            }));
            y += 30;
        }
        refreshModules();
    }

    private void refreshModules() {
        clearChildren();
        int x = 120;
        int y = 10;
        for (Module module : ModuleManager.getModules()) {
            if (module.getCategory() == currentCategory) {
                addDrawableChild(new ButtonWidget(x, y, 100, 20, Text.of(module.getName()), button -> {
                    module.setEnabled(!module.isEnabled());
                }));
                y += 30;
            }
        }
    }

    public static void open() {
        MinecraftClient.getInstance().setScreen(new ClickGUI());
    }
}
