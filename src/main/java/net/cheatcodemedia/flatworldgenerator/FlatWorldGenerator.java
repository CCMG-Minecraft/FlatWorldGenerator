package net.cheatcodemedia.flatworldgenerator;

import net.cheatcodemedia.flatworldgenerator.generator.Chunk;
import org.bstats.bukkit.Metrics;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlatWorldGenerator extends JavaPlugin {

    /**
     * The plugin instance.
     */
    public static FlatWorldGenerator instance;

    /**
     * The plugin's 'enable' method.
     */
    @Override
    public void onEnable() {
        // Set the plugin instance variable to allow usage by external plugins and other classes.
        instance = this;

        // Create BStats instance
        Metrics metrics = new Metrics(this, 7887);
    }

    /**
     * Get the plugin instance
     * @return {FlatWorldGenerator} The plugin instance
     */
    public static FlatWorldGenerator getInstance() {
        return instance;
    }

    /**
     * Register The World Generator
     * */
    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String template) {
        if (template == null) {
            template = "default";
        }
        return new Chunk(template);
    }
}
