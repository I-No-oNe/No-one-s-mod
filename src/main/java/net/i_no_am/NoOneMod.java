package net.i_no_am;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoOneMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("I-No-oNe");
    public static final String PREFIX = "§8[§l§aI-No-oNe§r§8] §r";

    @Override
    public void onInitialize() {
        LOGGER.info("Successfully loaded No one's mod");
    }
}
