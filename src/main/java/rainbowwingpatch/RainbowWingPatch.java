package rainbowwingpatch;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pixelmonmod.pixelmon.Pixelmon;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
        modid = RainbowWingPatch.MOD_ID,
        name = RainbowWingPatch.MOD_NAME,
        version = RainbowWingPatch.VERSION,
        acceptableRemoteVersions = "*",
        dependencies = "after:" + Pixelmon.MODID,
        acceptedMinecraftVersions = "[1.12.2]",
        useMetadata = true
    )

public class RainbowWingPatch {

    public static final String MOD_ID = "rainbowwingpatch";
    public static final String MOD_NAME = "RainbowWingPatch";
    public static final String VERSION = "1.0";
    public static Logger log = LogManager.getLogger(MOD_NAME);
    public static File configDir;
    public static File configFile;
    public static MinecraftEvents minecraftevents;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent e) {
        log.info("Booting up " + MOD_NAME + " - " + VERSION);
        configDir = new File(e.getModConfigurationDirectory() + "/" + MOD_ID);
        configDir.mkdir();
        configFile = new File(configDir, "config.json");
        FileHandler.readConfig();
        FileHandler.creationCheck();
        FileHandler.writeConfig();
        minecraftevents = new MinecraftEvents();
        log.info("Events registered");        
    }
    
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent e) {
        e.registerServerCommand(new ReloadCommand());
    }
}
