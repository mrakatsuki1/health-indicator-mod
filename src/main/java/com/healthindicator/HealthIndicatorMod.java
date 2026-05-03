package com.healthindicator;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealthIndicatorMod implements ClientModInitializer {

    public static final String MOD_ID = "health_indicator";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Health Indicator Mod load ho gaya! :)");
        LOGGER.info("Ab enemy ki health ke hisab se nametag color change hoga.");
    }
}