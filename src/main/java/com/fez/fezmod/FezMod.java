package com.fez.fezmod;


import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = FezMod.MODID, name = FezMod.MODNAME, version = FezMod.VERSION, useMetadata = true)
public class FezMod {

    public static final String MODID = "fezmod";
    public static final String MODNAME = "Fez's First Mod";
    public static final String VERSION = "0.0.1 Pre-Beta";

    @SidedProxy(clientSide="com.fez.fezmod.ClientOnlyProxy", serverSide="com.fez.fezmod.DedicatedServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(FezMod.MODID)
    public static FezMod instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit();
    }


}
