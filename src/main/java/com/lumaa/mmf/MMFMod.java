package com.lumaa.mmf;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MMFMod implements ClientModInitializer {
    public Logger logger = LoggerFactory.getLogger("mmf");
    public String ID = "mmf";

    @Override
    public void onInitializeClient() {
        print("MiniMinified F3 initialized");
    }

    public void print(String text) {
        logger.info("[MMF] " + text);
    }
}
