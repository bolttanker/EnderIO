package crazypants.enderio;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import crazypants.enderio.enderface.BlockEnderIO;
import crazypants.enderio.enderface.ItemEnderface;
import crazypants.enderio.network.PacketPipeline;
import crazypants.enderio.network.PacketTileEntity;
import crazypants.enderio.teleport.BlockTravelAnchor;
import crazypants.enderio.teleport.ItemTravelStaff;
import crazypants.enderio.teleport.TeleportRecipes;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "EnderIO", name = "Ender IO",version = "1.1.0alpha", dependencies = "required-after:Forge@[7.0,);required-after:FML@[5.0.5,)")
public class EnderIO {

  @Instance("EnderIO")
  public static EnderIO instance;

  @SidedProxy(clientSide = "crazypants.enderio.ClientProxy", serverSide = "crazypants.enderio.CommonProxy")
  public static CommonProxy proxy;

  public static final PacketPipeline packetPipeline = new PacketPipeline();

  public static GuiHandler guiHandler = new GuiHandler();

  // Materials
//  public static ItemCapacitor itemBasicCapacitor;
//  public static ItemAlloy itemAlloy;
//  public static BlockFusedQuartz blockFusedQuartz;
//  public static ItemFusedQuartzFrame itemFusedQuartzFrame;
//  public static ItemMachinePart itemMachinePart;
//  public static ItemPowderIngot itemPowderIngot;
//  public static ItemMaterial itemMaterial;

  // Enderface
  public static BlockEnderIO blockEnderIo;
  public static ItemEnderface itemEnderface;

  //Teleporting
  public static BlockTravelAnchor blockTravelPlatform;
  public static ItemTravelStaff itemTravelStaff;

  // Painter
//  public static BlockPainter blockPainter;
//  public static BlockCustomFence blockCustomFence;
//  public static BlockCustomFenceGate blockCustomFenceGate;
//  public static BlockCustomWall blockCustomWall;
//  public static BlockCustomStair blockCustomStair;
//  public static BlockCustomSlab blockCustomSlab;
//  public static BlockCustomSlab blockCustomDoubleSlab;
//
//  // Conduits
//  public static BlockConduitBundle blockConduitBundle;
//  public static BlockConduitFacade blockConduitFacade;
//  public static ItemConduitFacade itemConduitFacade;
//  public static ItemRedstoneConduit itemRedstoneConduit;
//  public static ItemPowerConduit itemPowerConduit;
//  public static ItemLiquidConduit itemLiquidConduit;
//  public static ItemItemConduit itemItemConduit;
//  public static ItemMeConduit itemMeConduit;
//
//  // Machines
//  public static BlockStirlingGenerator blockStirlingGenerator;
//  public static BlockSolarPanel blockSolarPanel;
//  public static BlockReservoir blockReservoir;
//  public static BlockAlloySmelter blockAlloySmelter;
//  public static BlockCapacitorBank blockCapacitorBank;
//  public static BlockCrusher blockCrusher;
//  public static BlockHyperCube blockHyperCube;
//  public static BlockPowerMonitor blockPowerMonitor;
//
//  public static BlockElectricLight blockElectricLight;
//  public static BlockLightNode blockLightNode;
//
//  public static ItemYetaWrench itemYetaWench;
//  public static ItemMJReader itemMJReader;
//
//  public static ITrigger triggerNoEnergy;
//  public static ITrigger triggerHasEnergy;
//  public static ITrigger triggerFullEnergy;
//  public static ITrigger triggerIsCharging;
//  public static ITrigger triggerFinishedCharging;

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {

    Config.load(event);

//    ConduitGeometryUtil.setupBounds((float) Config.conduitScale);

//    itemBasicCapacitor = ItemCapacitor.create();
//    itemAlloy = ItemAlloy.create();
//    blockFusedQuartz = BlockFusedQuartz.create();
//    itemFusedQuartzFrame = ItemFusedQuartzFrame.create();
//    itemMachinePart = ItemMachinePart.create();
//    itemPowderIngot = ItemPowderIngot.create();
//    itemMaterial = ItemMaterial.create();


    blockEnderIo = BlockEnderIO.create();
    itemEnderface = ItemEnderface.create();

    blockTravelPlatform = BlockTravelAnchor.create();
    itemTravelStaff = ItemTravelStaff.create();

//    blockHyperCube = BlockHyperCube.create();
//
//    blockPainter = BlockPainter.create();
//    blockCustomFence = BlockCustomFence.create();
//    blockCustomFenceGate = BlockCustomFenceGate.create();
//    blockCustomWall = BlockCustomWall.create();
//    blockCustomStair = BlockCustomStair.create();
//    blockCustomSlab = new BlockCustomSlab(false);
//    blockCustomDoubleSlab = new BlockCustomSlab(true);
//    blockCustomSlab.init();
//    blockCustomDoubleSlab.init();
//
//    blockStirlingGenerator = BlockStirlingGenerator.create();
//    blockSolarPanel = BlockSolarPanel.create();
//    blockReservoir = BlockReservoir.create();
//    blockAlloySmelter = BlockAlloySmelter.create();
//    blockCapacitorBank = BlockCapacitorBank.create();
//    blockCrusher = BlockCrusher.create();
//    blockPowerMonitor = BlockPowerMonitor.create();
//
//    blockConduitBundle = BlockConduitBundle.create();
//    blockConduitFacade = BlockConduitFacade.create();
//    itemConduitFacade = ItemConduitFacade.create();
//
//    itemRedstoneConduit = ItemRedstoneConduit.create();
//    itemPowerConduit = ItemPowerConduit.create();
//    itemLiquidConduit = ItemLiquidConduit.create();
//    itemItemConduit = ItemItemConduit.create();
//    itemMeConduit = ItemMeConduit.create();
//
//    blockElectricLight = BlockElectricLight.create();
//    blockLightNode = BlockLightNode.create();
//
//    itemYetaWench = ItemYetaWrench.create();
//    itemMJReader = ItemMJReader.create();
//
//    MaterialRecipes.registerOresInDictionary();
  }

  @EventHandler
  public void load(FMLInitializationEvent event) {

    instance = this;

    packetPipeline.initalise();
    packetPipeline.registerPacket(PacketTileEntity.class);

    NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
    MinecraftForge.EVENT_BUS.register(this);

    //Register Custom Dungeon Loot here
//    ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(
//        new WeightedRandomChestContent(new ItemStack(EnderIO.itemAlloy, 1, Alloy.ELECTRICAL_STEEL.ordinal()), 1, 3, 60));
//    ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST)
//        .addItem(new WeightedRandomChestContent(new ItemStack(EnderIO.itemYetaWench, 1, 0), 1, 1, 15));
//    ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(EnderIO.itemMJReader, 1, 0), 1, 1, 1));
//
//    ItemStack staff = new ItemStack(EnderIO.itemTravelStaff, 1, 0);
//    itemTravelStaff.setFull(staff);
//    ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(staff, 1, 1, 30));
//    ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(Items.quartz), 3, 16, 40));
//    ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(Items.nether_wart), 1, 4, 30));
//    ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(Items.ender_pearl), 1, 2, 30));
//    ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(
//        new WeightedRandomChestContent(new ItemStack(EnderIO.itemAlloy, 1, Alloy.ELECTRICAL_STEEL.ordinal()), 5, 20, 50));
//    ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(
//        new WeightedRandomChestContent(new ItemStack(EnderIO.itemAlloy, 1, Alloy.REDSTONE_ALLOY.ordinal()), 3, 14, 35));
//    ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(
//        new WeightedRandomChestContent(new ItemStack(EnderIO.itemAlloy, 1, Alloy.PHASED_IRON.ordinal()), 2, 6, 20));
//    ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(
//        new WeightedRandomChestContent(new ItemStack(EnderIO.itemAlloy, 1, Alloy.PHASED_GOLD.ordinal()), 2, 6, 10));
//    ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(staff, 1, 1, 5));
//    ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(staff, 1, 1, 20));
//
//    PacketHandler.instance.addPacketProcessor(new RedstoneModePacketProcessor());

//    EnderfaceRecipes.addRecipes();
//    MaterialRecipes.addRecipes();
//    ConduitRecipes.addRecipes();
//    MachineRecipes.addRecipes();
//    ItemRecipes.addRecipes();
    TeleportRecipes.addRecipes();

//    triggerNoEnergy = new TriggerEnderIO("enderIO.trigger.noEnergy", 0);
//    triggerHasEnergy = new TriggerEnderIO("enderIO.trigger.hasEnergy", 1);
//    triggerFullEnergy = new TriggerEnderIO("enderIO.trigger.fullEnergy", 2);
//    triggerIsCharging = new TriggerEnderIO("enderIO.trigger.isCharging", 3);
//    triggerFinishedCharging = new TriggerEnderIO("enderIO.trigger.finishedCharging", 4);

//    ActionManager.registerTriggerProvider(new TriggerProviderEIO());

    proxy.load();

  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {

    packetPipeline.postInitialise();

//    CrusherRecipeManager.getInstance().loadRecipesFromConfig();
//    AlloyRecipeManager.getInstance().loadRecipesFromConfig();
//    MaterialRecipes.addOreDictionaryRecipes();
//    MachineRecipes.addOreDictionaryRecipes();
//    ConduitRecipes.addOreDictionaryRecipes();
  }

  @EventHandler
  public void serverStarted(FMLServerStartedEvent event) {
//    HyperCubeRegister.load();
  }

  @EventHandler
  public void serverStopped(FMLServerStoppedEvent event) {
//    HyperCubeRegister.unload();
  }
}