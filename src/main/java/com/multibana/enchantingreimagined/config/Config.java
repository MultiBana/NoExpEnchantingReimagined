package com.multibana.enchantingreimagined.config;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Config {
    public static final String FILE_NAME = "no-exp-enchanting-reimagined.properties";


    public static Map<String, Boolean> rules = new HashMap<>();


    public static void createIfAbsent()
    {
        File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), FILE_NAME);
        if (file.exists()) return;

        try
        {
            var writer = new PrintWriter(file);
            writer.println("trident_repairable:true");
            writer.println("remove_enchant_achievement:true");
            writer.println("dragon_immune_to_beds:true");
            writer.println("### Nerfs include both max level reductions and changes to effect");
            writer.println("nerf_sharpness:true");
            writer.println("enable_trident_sharpness:true");
            writer.println("nerf_efficiency:true");
            writer.println("nerf_fortune_and_looting:true");
            writer.println("nerf_power:true");
            writer.println("power_no_infinity:true");
            writer.println("nerf_riptide:true");
            writer.println("nerf_protections:true");
            writer.println("### ");
            writer.println("### If you choose not to remove any of the following, they will only be obtainable through librarian villagers! (or commands/creative)");
            writer.println("### Sorry, but dragon drops and chest loot are hardcoded in datapack-json type implementation. I'll change it when I learn how to..");
            writer.println("### You are very much welcome to change the loot tables yourself!");
            writer.println("remove_mending:true");
            writer.println("remove_protection:true");
            writer.println("remove_thorns:true");
            writer.println("remove_frost_walker:true");
            writer.println("remove_bane_of_arthropods:true");
            writer.println("remove_smite:true");
            writer.println("remove_sweeping_edge:true");
            writer.println("remove_quick_charge:true");
            writer.println("remove_impaling:true");
            writer.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void reload()
    {
        createIfAbsent();
        File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), FILE_NAME);

        try
        {
            var reader = new Scanner(file);
            rules.clear();
            while (reader.hasNextLine())
            {
                var line = reader.nextLine();
                if (line.length() == 0 || line.startsWith("#")) continue;
                var result = Arrays.stream(line.split(":")).map(s -> s.replace(" ", "")).toArray(String[]::new);

                if (result[1].equalsIgnoreCase("true") || result[1].equalsIgnoreCase("false")) {
                    rules.put(result[0], Boolean.valueOf(result[1]));
                } else {
                    rules.put(result[0], true);
                }
            }
            reader.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
